package com.manage.manage.controller;

import com.manage.manage.bean.Manager;
import com.manage.manage.commons.IgnoreAuth;
import com.manage.manage.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/manage")
@RestController
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @IgnoreAuth
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Manager param){
        return ResponseEntity.ok(managerService.login(param.getUserName(),param.getPassword()));
    }

}
