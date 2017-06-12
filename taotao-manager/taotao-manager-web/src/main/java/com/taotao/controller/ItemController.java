package com.taotao.controller;

import com.taotao.pojo.EUIDataGridResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by geek on 2017/6/11.
 */
@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/{id}")
    @ResponseBody
    TbItem ItemQuery(@PathVariable Long id){
        return itemService.ItemQueryById(id);
    }

    @RequestMapping("/list")
    @ResponseBody
    EUIDataGridResult showItemList(@RequestParam(defaultValue="1")Integer page,
                                   @RequestParam(defaultValue="30")Integer rows){
        return itemService.getPageList(page,rows);
    }
}
