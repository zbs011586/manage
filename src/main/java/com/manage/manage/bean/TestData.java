package com.manage.manage.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "t_test")
public class TestData {

    @Id
    private Integer id;

    private String cid;

    private String name;

    private String openId;

    private Integer status;

}
