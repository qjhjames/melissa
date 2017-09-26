package com.qianlikange.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by qiujunhong on 2017/5/8.
 */
@Controller
public class IndexController {
    @RequestMapping("/index")
    public String helloHtml(Model model){

        return"index";
    }
}
