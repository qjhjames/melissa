package com.qianlikange.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by qiujunhong on 2017/5/8.
 */
@RestController
public class MainController {
    @RequestMapping(value = "/haha",method = RequestMethod.GET)
    public String Hello(){
        return "haha";
    }
}
