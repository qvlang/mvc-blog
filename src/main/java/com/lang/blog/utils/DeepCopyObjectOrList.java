package com.lang.blog.utils;

import java.io.*;
import java.util.List;

/**
 * 深复制的两种方式 一重写clone方法 二使用序列化方式
 * 以下使用序列化方式实现对象集合的深复制
 */
public class DeepCopyObjectOrList {
    //私有化构造器 不许外部创建对象 纯工具类
    private DeepCopyObjectOrList() {
    }


    //深复制对象含集合
    public static <T> T DeepCopyObject(T t) {
        T dest = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            //从程序中输出集合 用os将集合输出 在用ins将集合读入到程序中
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(t);
            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);

            dest = (T) ois.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            //先关闭外层流
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return dest;
    }
}
