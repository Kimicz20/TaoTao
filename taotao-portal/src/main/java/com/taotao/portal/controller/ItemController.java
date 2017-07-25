package com.taotao.portal.controller;

import com.taotao.pojo.Item;
import com.taotao.portal.pojo.ItemInfo;
import com.taotao.portal.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by geek on 2017/7/24.
 */
@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/{id}")
    public String itemShow(@PathVariable Long id, Model model){
        ItemInfo item = itemService.getItemById(id);
        model.addAttribute("item",item);
        return "item";
    }

    @RequestMapping("/desc/{id}")
    public String itemDesc(@PathVariable("id") String id,Model model){
        Item item = null;
        model.addAttribute("item",item);
        return "item";
    }

    @RequestMapping("/param/{id}")
    public String itemParam(@PathVariable("id") String id,Model model){
        Item item = null;
        model.addAttribute("item",item);
        return "item";
    }
}
