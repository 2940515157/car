package com.xmz.br.vipCard.config;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 获取 单号 工具类  格式  head + 时间 + back
 */
public class CodeNoGenerateUtil {

    /**
     * 获取 单纯
     * @return
     */
    public static String createNo() {
        return createNo(null, null);
    }

    /**
     * 提供 头部
     * @param head 头部
     * @return
     */
    public static String createNoToHead(String head) {
        return createNo(head, null);
    }

    /**
     * 提供 尾部
     * @param back 尾部
     * @return
     */
    public static String createNoToBack(String back) {
        return createNo(null, back);
    }

    /**
     * 主方法 获取单号
     *
     * @param head 同步
     * @param back 尾部
     * @return
     */
    public static synchronized String createNo(String head, String back) {
        //大写 HH 24小时  hh 12 小时
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssS");

        String date = format.format(new Date());
        if (head != null) {
            date = head + date;
        }

        if (back != null) {
            date = date + back;
        }

        //休息 一毫秒
        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return date;
    }


}
