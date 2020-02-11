package com.manage.manage.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserParam {
    private String cid; //身份证

    private String name; //用户姓名

    private String openId;

}
