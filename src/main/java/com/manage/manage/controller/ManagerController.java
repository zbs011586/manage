package com.manage.manage.controller;

import com.manage.manage.commons.IgnoreAuth;
import com.manage.manage.param.ManageParam;
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
    public ResponseEntity login(@RequestBody ManageParam param){
        return ResponseEntity.ok(managerService.login(param.getUserName(),param.getPassword()));
    }

    @PostMapping("/notice/create")
    public ResponseEntity noticeCreate(@RequestBody ManageParam param){
        return ResponseEntity.ok(managerService.noticeCreate(param.getNotice()));
    }

    @PostMapping("/notice/list")
    public ResponseEntity noticeList(@RequestBody ManageParam param){
        return ResponseEntity.ok(managerService.noticeList(param.getPageNum(),param.getPageSize()));
    }

    @PostMapping("/notice/set")
    public ResponseEntity noticeSet(@RequestBody ManageParam param){
        return ResponseEntity.ok(managerService.noticeSet(param.getId()));
    }

    @PostMapping("/advice/list")
    public ResponseEntity adviceList(@RequestBody ManageParam param){
        return ResponseEntity.ok(managerService.adviceList(param.getPageNum(),param.getPageSize()));
    }

    @PostMapping("/image/list")
    public ResponseEntity imageList(@RequestBody ManageParam param){
        return ResponseEntity.ok(managerService.imageList(param.getPageNum(),param.getPageSize()));
    }

}
