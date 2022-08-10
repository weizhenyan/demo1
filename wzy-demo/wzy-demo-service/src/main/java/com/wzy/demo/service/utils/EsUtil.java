package com.wzy.demo.service.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.core.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.*;

/**
 * EsUtil
 *
 * @author 飞燕
 * @date   2022/8/9 11:32
 */
@Slf4j
@Component
public class EsUtil {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 关键字
     */
    public static final String KEYWORD = ".keyword";

    /**
     * 创建索引
     *
     * @param index 索引
     * @return
     */
    public boolean createIndex(String index) throws IOException {
        if (isIndexExist(index)) {
            log.error("Index is exits!");
            return false;
        }
        //1.创建索引请求
        CreateIndexRequest request = new CreateIndexRequest(index);
        //2.执行客户端请求
        CreateIndexResponse response = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);

        log.info("创建索引{}成功", index);

        return response.isAcknowledged();
    }

    /**
     * 删除索引
     *
     * @param index
     * @return
     */
    public boolean deleteIndex(String index) throws IOException {
        if (!isIndexExist(index)) {
            log.error("Index is not exits!");
            return false;
        }
        //删除索引请求
        DeleteIndexRequest request = new DeleteIndexRequest(index);
        //执行客户端请求
        AcknowledgedResponse delete = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);

        log.info("删除索引{}成功", index);

        return delete.isAcknowledged();
    }


    /**
     * 判断索引是否存在
     *
     * @param index
     * @return
     */
    public boolean isIndexExist(String index) throws IOException {

        GetIndexRequest request = new GetIndexRequest(index);

        boolean exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);

        return exists;
    }


    /**
     * 数据添加，正定ID
     *
     * @param jsonObject 要增加的数据
     * @param index      索引，类似数据库
     * @param id         数据ID, 为null时es随机生成
     * @return
     */
    public String addData(JSONObject jsonObject, String index, String id) throws IOException {

        //创建请求
        IndexRequest request = new IndexRequest(index);
        //规则 put /test_index/_doc/1
        request.id(id);
        request.timeout(TimeValue.timeValueSeconds(1));
        //将数据放入请求 json
        IndexRequest source = request.source(jsonObject, XContentType.JSON);
        //客户端发送请求
        IndexResponse response = restHighLevelClient.index(request, RequestOptions.DEFAULT);
        log.info("添加数据成功 索引为: {}, response 状态: {}, id为: {}", index, response.status().getStatus(), response.getId());
        return response.getId();
    }


    /**
     * 数据添加 随机id
     *
     * @param jsonObject 要增加的数据
     * @param index      索引，类似数据库
     * @return
     */
    public String addData(JSONObject jsonObject, String index) throws IOException {
        return addData(jsonObject, index, UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());
    }

    /**
     * 批量新增文档
     *
     * @param indexName 索引（类似key）
     * @param list      存储的数据实体类对象
     */
    public boolean addData(String indexName, List<JSONObject> list) {
        try {
            BulkRequest request = new BulkRequest();
            list.forEach(crowdMessageDTO -> {
                String source = JSON.toJSONString(crowdMessageDTO);
                request.add(new IndexRequest(indexName).source(source, XContentType.JSON));
            });
            BulkResponse bulk = restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
            /**
             * 是否失败 ,返回false 代表成功
             */
            if (!bulk.hasFailures()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("系统错误,批量新增文档 失败");
        }
    }

    /**
     * 通过ID删除数据
     *
     * @param index 索引，类似数据库
     * @param id    数据ID
     */
    public void deleteDataById(String index, String id) throws IOException {
        //删除请求
        DeleteRequest request = new DeleteRequest(index, id);
        //执行客户端请求
        DeleteResponse delete = restHighLevelClient.delete(request, RequestOptions.DEFAULT);
        log.info("索引为: {}, id为: {}删除数据成功", index, id);
    }


    /**
     * 通过ID 更新数据
     *
     * @param object 要增加的数据
     * @param index  索引，类似数据库
     * @param id     数据ID
     * @return
     */
    public void updateDataById(Object object, String index, String id) throws IOException {
        //更新请求
        UpdateRequest update = new UpdateRequest(index, id);

        //保证数据实时更新
        //update.setRefreshPolicy("wait_for");

        update.timeout("1s");
        update.doc(JSON.toJSONString(object), XContentType.JSON);
        //执行更新请求
        UpdateResponse update1 = restHighLevelClient.update(update, RequestOptions.DEFAULT);
        log.info("索引为: {}, id为: {}, 更新数据成功", index, id);
    }


    /**
     * 通过ID 更新数据,保证实时性
     *
     * @param object 要增加的数据
     * @param index  索引，类似数据库
     * @param id     数据ID
     * @return
     */
    public void updateDataByIdNoRealTime(Object object, String index, String id) throws IOException {
        //更新请求
        UpdateRequest update = new UpdateRequest(index, id);

        //保证数据实时更新
        update.setRefreshPolicy("wait_for");

        update.timeout("1s");
        update.doc(JSON.toJSONString(object), XContentType.JSON);
        //执行更新请求
        UpdateResponse update1 = restHighLevelClient.update(update, RequestOptions.DEFAULT);
        log.info("索引为: {}, id为: {}, 更新数据成功", index, id);
    }


    /**
     * 通过ID获取数据
     *
     * @param index  索引，类似数据库
     * @param id     数据ID
     * @param fields 需要显示的字段，逗号分隔（缺省为全部字段）
     * @return
     */
    public Map<String, Object> searchDataById(String index, String id, String fields) throws IOException {
        GetRequest request = new GetRequest(index, id);
        if (StringUtils.isNotEmpty(fields)) {
            //只查询特定字段。如果需要查询所有字段则不设置该项。
            request.fetchSourceContext(new FetchSourceContext(true, fields.split(","), Strings.EMPTY_ARRAY));
        }
        GetResponse response = restHighLevelClient.get(request, RequestOptions.DEFAULT);
        Map<String, Object> map = response.getSource();
        //为返回的数据添加id
        map.put("id", response.getId());
        return map;
    }

    /**
     * 通过ID判断文档是否存在
     *
     * @param index 索引，类似数据库
     * @param id    数据ID
     * @return
     */
    public boolean existsById(String index, String id) throws IOException {
        GetRequest request = new GetRequest(index, id);
        //不获取返回的_source的上下文
        request.fetchSourceContext(new FetchSourceContext(false));
        request.storedFields("_none_");
        return restHighLevelClient.exists(request, RequestOptions.DEFAULT);
    }

    /**
     * 获取低水平客户端
     *
     * @return
     */
    public RestClient getLowLevelClient() {
        return restHighLevelClient.getLowLevelClient();
    }


    /**
     * 高亮结果集 特殊处理
     * map转对象 JSONObject.parseObject(JSONObject.toJSONString(map), Content.class)
     *
     * @param searchResponse
     * @param highlightField
     */
    public List<Map<String, Object>> setSearchResponse(SearchResponse searchResponse, String highlightField) {
        //解析结果
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            Map<String, HighlightField> high = hit.getHighlightFields();
            HighlightField title = high.get(highlightField);
            hit.getSourceAsMap().put("id", hit.getId());
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            //解析高亮字段,将原来的字段换为高亮字段
            if (title != null) {
                Text[] texts = title.fragments();
                String nTitle = "";
                for (Text text : texts) {
                    nTitle += text;
                }
                //替换
                sourceAsMap.put(highlightField, nTitle);
            }
            list.add(sourceAsMap);
        }
        return list;
    }


    /**
     * 查询并分页
     *
     * @param index          索引名称
     * @param query          查询条件
     * @param size           文档大小限制
     * @param from           从第几页开始
     * @param fields         需要显示的字段，逗号分隔（缺省为全部字段）
     * @param sortField      排序字段
     * @param highlightField 高亮字段
     * @return
     */
    public List<Map<String, Object>> searchListData(String index,
                                                    SearchSourceBuilder query,
                                                    Integer size,
                                                    Integer from,
                                                    String fields,
                                                    String sortField,
                                                    String highlightField) throws IOException {
        SearchRequest request = new SearchRequest(index);
        SearchSourceBuilder builder = query;
        if (StringUtils.isNotEmpty(fields)) {
            builder.fetchSource(new FetchSourceContext(true, fields.split(","), Strings.EMPTY_ARRAY));
        }
        //分页查询 from起始页
        from = from <= 0 ? 0 : from * size;
        builder.from(from);
        builder.size(size);
        if (StringUtils.isNotEmpty(sortField)) {
            builder.sort(sortField + ".keyword", SortOrder.ASC);
        }
        //高亮
        HighlightBuilder highlight = new HighlightBuilder();
        highlight.field(highlightField);
        //关闭多个高亮
        highlight.requireFieldMatch(false);
        highlight.preTags("<span style='color:red'>");
        highlight.postTags("</span>");
        builder.highlighter(highlight);
        request.source(builder);
        SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        log.error("==" + response.getHits().getTotalHits());
        if (response.status().getStatus() == 200) {
            // 解析对象
            return setSearchResponse(response, highlightField);
        }
        return null;
    }

    /**
     * 多条件查询
     *
     * @param map      精确查询参数map  参数and连接
     * @param orMap    精确查询参数map  参数or连接
     * @param dimap    模糊查询参数map 参数and连接
     * @param dimOrMap 模糊查询参数map 参数or连接
     * @return 多条件构造器
     */
    public BoolQueryBuilder moreCondition(Map<String, Object> map, Map<String, Object> dimap
            , Map<String, Object> orMap, Map<String, Object> dimOrMap) {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        //多条件设置  参数and连接
        //matchPhraseQuery是没有用分词起，matchQuery会使用分词器，将我们输入的值进行分割，如：“java动态”会分割成：“java”,“动态”
        if (!CollectionUtils.isEmpty(map)) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery(entry.getKey(), entry.getValue());
                boolQueryBuilder.must(matchQueryBuilder);
            }
        }
        //精确查询参数map  参数or连接
        if (!CollectionUtils.isEmpty(orMap)) {
            for (Map.Entry<String, Object> entry : orMap.entrySet()) {
                MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery(entry.getKey(), entry.getValue());
                boolQueryBuilder.should(matchQueryBuilder);
            }
        }
        //模糊查询  参数and连接
        if (!CollectionUtils.isEmpty(dimap)) {
            for (Map.Entry<String, Object> entry : dimap.entrySet()) {
                WildcardQueryBuilder wildcardQueryBuilder = QueryBuilders.wildcardQuery(entry.getKey(), "*" + entry.getValue() + "*");
                boolQueryBuilder.must(wildcardQueryBuilder);
            }
        }
        //模糊查询 参数or连接
        if (!CollectionUtils.isEmpty(dimOrMap)) {
            for (Map.Entry<String, Object> entry : dimOrMap.entrySet()) {
                WildcardQueryBuilder wildcardQueryBuilder = QueryBuilders.wildcardQuery(entry.getKey(), "*" + entry.getValue() + "*");
                boolQueryBuilder.should(wildcardQueryBuilder);
            }
        }
        return boolQueryBuilder;
    }

    /**
     * 构建分页
     *
     * @param sourceBuilder 条件构造器
     * @param pageNum       页码
     * @param pageSize      每页数据量
     */
    public void limit(SearchSourceBuilder sourceBuilder, Integer pageNum, Integer pageSize) {
        if (sourceBuilder != null && !Objects.isNull(pageNum) && !Objects.isNull(pageSize)) {
            //分页 开始位置
            sourceBuilder.from((pageNum - 1) * pageSize);
            //每页大小
            sourceBuilder.size(pageSize);
        }
    }

    /**
     * 处理结果方法
     *
     * @param pageNum       开始页码
     * @param pageSize      每页数据
     * @param highName      高亮字段
     * @param searchRequest 搜索请求
     * @return map
     * @throws IOException 操作异常
     */
    public Map<String, Object> manageResult(Integer pageNum, Integer pageSize, String highName, SearchRequest searchRequest) throws IOException {
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        JSONArray jsonArray = new JSONArray();
        for (SearchHit hit : hits) {
            //原来的结果
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            if (!StringUtils.isEmpty(highName)) {
                //解析高亮字段
                Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                HighlightField areaName = highlightFields.get(highName);
                if (areaName != null) {
                    Text[] fragments = areaName.fragments();
                    String new_areaName = "";
                    for (Text text : fragments) {
                        new_areaName += text;
                    }
                    //高亮字段替换之前字段
                    sourceAsMap.put(highName, new_areaName);
                }
            }
            JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(sourceAsMap));
            jsonArray.add(jsonObject);
        }
        //总数
        int total = (int) hits.getTotalHits().value;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", jsonArray);
        map.put("pageNum", pageNum);
        map.put("pageSize", pageSize);
        map.put("total", total);
        map.put("pages", total == 0 ? 0 : (total % pageSize == 0 ? total / pageSize : (total / pageSize) + 1));
        return map;
    }

    /**
     * 通用查询方法
     *
     * @param indexName 索引名称
     * @param pageNum   开始页码
     * @param pageSize  每页数据量
     * @param highName  查询高亮字段名
     * @param queryMap  精确查询参数map  参数and连接
     * @param orMap     精确查询参数map  参数or连接
     * @param dimap     模糊查询参数map 参数and连接
     * @param dimOrMap  模糊查询参数map 参数or连接
     * @return 查询结果
     * @throws Exception 操作错误
     */
    public Map<String, Object> search(String indexName, Integer pageNum, Integer pageSize,
                                      String highName, Map<String, Object> queryMap, Map<String, Object> dimap
            , Map<String, Object> orMap, Map<String, Object> dimOrMap) throws Exception {
        SearchRequest searchRequest = new SearchRequest(indexName);
        //构建搜索条件
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //构建多条件查询
        BoolQueryBuilder boolQuery = moreCondition(queryMap, dimap, orMap, dimOrMap);
        sourceBuilder.query(boolQuery);
        if (!StringUtils.isEmpty(highName)) {
            highlightBuilder(sourceBuilder, highName);
        }
        //构建分页
        limit(sourceBuilder, pageNum, pageSize);
        sourceBuilder.timeout(TimeValue.timeValueSeconds(60));
        searchRequest.source(sourceBuilder);
        return manageResult(pageNum, pageSize, highName, searchRequest);
    }

    /**
     * 构建高亮字段
     *
     * @param sourceBuilder 条件构造器
     * @param name          高亮字段
     */
    public void highlightBuilder(SearchSourceBuilder sourceBuilder, String name) {
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        //高亮展示字段
        highlightBuilder.field(name);
        //是否多字段高亮显示
        highlightBuilder.requireFieldMatch(false);
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");
        sourceBuilder.highlighter(highlightBuilder);
    }
}
