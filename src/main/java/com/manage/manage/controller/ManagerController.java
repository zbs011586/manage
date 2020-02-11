package com.manage.manage.controller;

import com.manage.manage.bean.Question;
import com.manage.manage.bean.UserParam;
import com.manage.manage.commons.IgnoreAuth;
import com.manage.manage.param.ManageParam;
import com.manage.manage.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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

    @PostMapping("/notice/del")
    public ResponseEntity noticeDel(@RequestBody ManageParam param){
        return ResponseEntity.ok(managerService.noticeDel(param.getId()));
    }

    @PostMapping("/advice/list")
    public ResponseEntity adviceList(@RequestBody ManageParam param){
        return ResponseEntity.ok(managerService.adviceList(param.getPageNum(),param.getPageSize()));
    }

    @PostMapping("/image/list")
    public ResponseEntity imageList(@RequestBody ManageParam param){
        return ResponseEntity.ok(managerService.imageList(param.getPageNum(),param.getPageSize()));
    }

    @PostMapping("/image/set")
    public ResponseEntity imageSet(@RequestBody ManageParam param){
        return ResponseEntity.ok(managerService.imageSet(param.getIds()));
    }

    @PostMapping("/image/del")
    public ResponseEntity imageDel(@RequestBody ManageParam param){
        return ResponseEntity.ok(managerService.imageDel(param.getId()));
    }

    @PostMapping("/image/preview")
    public ResponseEntity imagePreview(){
        return ResponseEntity.ok(managerService.imagePreview());
    }

    @PostMapping("/message/list")
    public ResponseEntity messageList(@RequestBody ManageParam param){
        return ResponseEntity.ok(managerService.messageList(param.getPageNum(),param.getPageSize()));
    }

    @PostMapping("/message/del")
    public ResponseEntity messageDel(@RequestBody ManageParam param, HttpServletRequest request){
        String token = request.getHeader("token");
        return ResponseEntity.ok(managerService.messageDel(param.getMessageId(),token));
    }

    @IgnoreAuth
    @PostMapping("/token/check")
    public ResponseEntity tokenCheck(@RequestBody ManageParam param){
        return ResponseEntity.ok(managerService.tokenCheck(param.getToken()));
    }


    @PostMapping("/question/save")
    public ResponseEntity questionSave(@RequestBody Question param){
        return ResponseEntity.ok(managerService.questionSave(param));
    }

    @PostMapping("/question/list")
    public ResponseEntity questionList(){
        return ResponseEntity.ok(managerService.questionList());
    }

    @PostMapping("/question/update")
    public ResponseEntity questionUpdate(@RequestBody Question param){
        return ResponseEntity.ok(managerService.questionUpdate(param));
    }

    @PostMapping("/question/del")
    public ResponseEntity questionDelete(@RequestBody Question param){
        return ResponseEntity.ok(managerService.questionDelete(param));
    }

    @IgnoreAuth
    @PostMapping("/test/data")
    public ResponseEntity testData(@RequestBody UserParam param){
        return ResponseEntity.ok(managerService.testData(param));
    }

}