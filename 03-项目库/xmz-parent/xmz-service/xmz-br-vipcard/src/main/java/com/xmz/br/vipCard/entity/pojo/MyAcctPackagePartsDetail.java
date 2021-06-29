package com.xmz.br.vipCard.entity.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class MyAcctPackagePartsDetail extends AcctPackagePartsDetail implements Serializable {
        private String name;
        private String showName;
        private String brand;
        private String model;
}
