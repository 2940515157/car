package com.xmz.br.basedata.pojo.entity;

import lombok.Data;

import java.io.Serializable;
/**
 * 角色授权表格的表头数据
 */
@Data
public class ResourceHead implements Serializable{

    public String label;//列属性名

    public String prop;//列值

    public String name;//列头名字

}