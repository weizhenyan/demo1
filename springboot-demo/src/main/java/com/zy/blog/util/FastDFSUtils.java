package com.zy.blog.util;

public class FastDFSUtils {
    //上传图片
    public static String uploadPic(byte[] pic,String name,long size) throws Exception{
        //准备工作  读取配置文件
//        ClassPathResource resource = new ClassPathResource("fdfs_client.conf");
//        //流  读取文件 绝对 路径  相对路径不行的
//        ClientGlobal.init(resource.getClassLoader().getResource("fdfs_client.conf").getPath());
//        //1：连接Tracker 上传图片
//        TrackerClient trackerClient = new TrackerClient();
//        //获取保存图片的Stoage的位置
//        TrackerServer trackerServer = trackerClient.getConnection();
//        //连接Stoager
//        StorageServer storageServer = null;
//
//        StorageClient1 storageClient1 = new StorageClient1(trackerServer,storageServer);
//        //上传图片
//        String ext = FilenameUtils.getExtension(name);
//        NameValuePair[] meta_list = new NameValuePair[3];
//        meta_list[0] = new NameValuePair("filename",name);
//        meta_list[1] = new NameValuePair("fileext",ext);
//        meta_list[2] = new NameValuePair("filesize",String.valueOf(size));
////		http://192.168.200.128/
//        //    group1/M00/00/01/wKjIgFWOYc6APpjAAAD-qk29i78248.jpg
//        String path = storageClient1.upload_file1(pic, ext, meta_list);
        return null;
    }
}
