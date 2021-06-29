package com.xmz.br.basedata.pojo.vo;

import java.util.UUID;

/**
 * UUID生成器
 */
public class UUIDUtil {

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {
        System.out.println("格式化后的UUID ：" + getUUID());
    }
}
