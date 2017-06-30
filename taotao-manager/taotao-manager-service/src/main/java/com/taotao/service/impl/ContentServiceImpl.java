package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.fabric.xmlrpc.base.Array;
import com.sun.tools.javac.util.ArrayUtils;
import com.sun.tools.javac.util.StringUtils;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.EUIDataGridResult;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by geek on 2017/6/28.
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;

    @Override
    public TaotaoResult saveContent(TbContent content) {

        //补全信息
        content.setCreated(new Date());
        content.setUpdated(new Date());
        contentMapper.insert(content);
        return TaotaoResult.ok();
    }

    @Override
    public EUIDataGridResult queryContentList(Long categoryId, Integer page, Integer rows) {

        //1.查询数据
        TbContentExample example =  new TbContentExample();
        Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<TbContent> list = contentMapper.selectByExample(example);

        //2.分页处理
        PageHelper.startPage(page,rows);
        PageInfo<TbContent> info = new PageInfo<TbContent>(list);

        //2.结果显示
        EUIDataGridResult result =  new EUIDataGridResult();
        result.setTotal(info.getTotal());
        result.setRows(list);

        return result;
    }

    @Override
    public TaotaoResult editContent(TbContent tbContent) {
        contentMapper.updateByPrimaryKey(tbContent);
        return TaotaoResult.ok();
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @Override
    public TaotaoResult deleteContent(String ids) {
        TbContentExample example =  new TbContentExample();
        Criteria criteria = example.createCriteria();

        //类型转换
        List<Long> inList = new ArrayList<>();
        String[] tmp =  ids.split(",");
        for(String t:tmp){
            inList.add(Long.valueOf(t));
        }
        criteria.andIdIn(inList);
        contentMapper.deleteByExample(example);
        return TaotaoResult.ok();
    }
}
