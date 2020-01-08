package com.manage.manage.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ManageParam {

    private String userName;

    private String password;

    private String notice;

    private int pageNum;

    private int pageSize;

    private int id;

    private int sort;

    private int messageId;

    private String token;

    private String url;
}
