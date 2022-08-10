package com.wzy.demo.service.pojo.es;

import com.wzy.demo.service.base.BaseEsPojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.zxp.esclientrhl.annotation.ESID;
import org.zxp.esclientrhl.annotation.ESMapping;
import org.zxp.esclientrhl.annotation.ESMetaData;
import org.zxp.esclientrhl.enums.DataType;

/**
 * DemoES
 *
 * @author 飞燕
 * @date   2022/8/8 19:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@ESMetaData(indexName = "member_es", number_of_shards = 20, number_of_replicas = 5, printLog = true)
public class DemoES extends BaseEsPojo {
    /**
     * 主键
     */
    @ESID
    private Long id;

    /**
     * 姓名
     */
    @ESMapping(datatype = DataType.text_type)
    private String name;
}
