package com.taotao.search.controller;

import com.alibaba.druid.util.StringUtils;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.SearchResult;
import com.taotao.search.service.ItemSearchService;
import com.taotao.utils.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by geek on 2017/7/20.
 */
@Controller
@RequestMapping("/query")
public class SearchController {

    @Autowired
    private ItemSearchService itemSearchService;

    @RequestMapping(value="/{condition}/{page}/{rows}")
    @ResponseBody
    public TaotaoResult search(@PathVariable String condition,
                               @RequestParam(value = "rows",defaultValue = "60") Integer rows,
                               @RequestParam(value = "page",defaultValue = "1") Integer page)  {

        if (StringUtils.isEmpty(condition)){
            return TaotaoResult.build(400,"查询不能为空");
        }

        SearchResult searchResult = null;
        try {
//            condition = new String(condition.getBytes("iso8859-1"),"utf-8");
            searchResult = itemSearchService.itemSearch(condition, page,rows);
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return TaotaoResult.ok(searchResult);
    }

}