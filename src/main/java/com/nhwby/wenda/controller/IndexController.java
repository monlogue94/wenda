package com.nhwby.wenda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {
    //给了两个路径
    @RequestMapping(path = {"/", "/index"},method = {RequestMethod.GET})
    @ResponseBody
    public String index() {
        return "123";
    }

    @RequestMapping(path = {"/profile/{userId} "})
    @ResponseBody
    //路径传一个userid 我们要解析出来所以在方法中加@path
    public String profile(@PathVariable("userId") int userId,
                          @RequestParam("type") int type) {
        return "321";
    }


    @RequestMapping(path = {"/profile "})
    @ResponseBody
//路径传一个userid 我们要解析出来所以在方法中加@path
    public String profile() {
        return "333";
    }
}
