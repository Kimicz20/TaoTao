package com.taotao.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    String index(){
        return "index";
    }
}
