package com.lang.blog.utils;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.csource.common.NameValuePair;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;

public class FastDfsUtil {
    static {
        //读取配置文件:得到类路径下的文件的全路径
        String path = new ClassPathResource("fdfs_client.conf").getPath();
        try {
            ClientGlobal.init(path);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    private static StorageClient getStorageClient() throws IOException {
        //创建tracker客户端
        TrackerClient trackerClient = new TrackerClient();
        //通过trackerClient获取服务
        TrackerServer service = trackerClient.getConnection();
        //获取storage
        StorageClient storageClient = new StorageClient(service, null);
        return storageClient;
    }

    /**
     * @Description: 文件上传
     * @Param: 文件
     * @return: 文件名
     */
    public static String uploadFile(byte[] file, String file_ext) throws MyException, IOException {
        StorageClient storageClient = getStorageClient();
        //创建文件上传的描述信息
        NameValuePair[] pairs = new NameValuePair[1];
        pairs[0] = new NameValuePair("上传时间", new Date().toString());
        //通过storageClient来操作流对象
        String[] fileInfo = storageClient.upload_file(file, file_ext, pairs);
        //返回文件上传后的路径拼接[group1, M00/00/00/wKgAa15ln02AQA37AAAADBTKRrU165.txt]
        //group1/M00/00/00/wKgAa15ln02AQA37AAAADBTKRrU165.txt
        String searchPath = fileInfo[0] + "/" + fileInfo[1];

        return searchPath;
    }

    /**
     * @Description: 文件删除
     * @Param:
     * @return:
     */
    public static int deleteFile(String groupName, String remoteName) throws IOException, MyException {
        StorageClient storageClient = getStorageClient();
        int count = storageClient.delete_file(groupName, remoteName);
        return count;
    }
}
