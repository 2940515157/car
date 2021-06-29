package com.xmz.common.utils;

import org.junit.Test;

import java.util.ArrayList;



/**
 * Q：为什么需要有两个返回包？
 * A：因为在平常的除了查询页面之外，都不需要Map作为Data参数。
 *    比如查询一行数据，这边Result包装直接就是Result.getOKInstance(xxx),
 *    而如果是ResultMap包装则是ResultMap.getOKInstance().putData("Key1",xxx)。
 *    在前端获取的方式也不一样，Result直接是 response.data.data, ResultMap就是
 *    response.data.data.Key1
 */
public class ResultTest {



    @Test
    public void resultT(){
        // 默认OK数据
        // Result{flag=true, code=20000, message='OK', data=null}
        // 默认ERROR数据
        // Result{flag=false, code=30000, message='ERROR', data=null}

        // 直接返回,
        System.out.println(
                Result.getOKInstance()
        );
        // 直接返回 规定泛型,
        System.out.println(
                Result.getOKInstance(ArrayList.class)
        );
        // 带值返回 [注意会优先匹配字符串参数的方法，不能将字符串当值返回
        System.out.println(
                Result.getOKInstance(new ArrayList<Object>())
        );
        // 带消息返回 [注意会优先匹配字符串参数的方法，不能将字符串当值返回
        System.out.println(
                Result.getOKInstance("执行成功！！！")
        );
        // 带值和消息返回
        System.out.println(
                Result.getOKInstance("执行成功！！！", new ArrayList<Object>())
        );

        // 链式调用
        System.out.println(
                Result.getInstance()
                    .code(10086)
                    .flag(false)
                    .message("执行失败")
                    .data(new ArrayList<Object>())
        );

        // 返回错误包可以多几个方法
        // 设置状态码和消息
        System.out.println(
                Result.getErrorInstance(10086,"执行失败")
        );
        // 设置状态码和消息以及返回数据
        System.out.println(
                Result.getErrorInstance(10086,"执行失败",new ArrayList<Object>())
        );

    }

    @Test
    public void resultMapT(){
        // ResultMap 与 Result<T> 大多数方法参数都是一样的
        // ResultMap的data属性是Map，Result的data属性是泛型T

        // ResultMap多了一个putData的链式方法
        System.out.println(
                ResultMap.getOKInstance()
                        .putData("Foo","Value1")
                        .putData("Bar","DEMO2")
                        .putData("Key","HelloWorld")
        );


    }

}
