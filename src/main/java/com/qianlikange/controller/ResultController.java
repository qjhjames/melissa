package com.qianlikange.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by qiujunhong on 2017/9/22.
 */
@Controller
public class ResultController {
    @RequestMapping(value="/mysuccess")
    public String success(Model model){
        model.addAttribute("res","恭喜你！");
        model.addAttribute("detail","成功添加一条野山参");
        return "success";
    }

    @RequestMapping(value="/myerror")
    public String error(Model model){

        model.addAttribute("res","出错了！");
        model.addAttribute("detail","添加失败");

        return "error";
    }
}
