package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.EUIDataGridResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by geek on 2017/6/11.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    TbItemMapper itemMapper;

    @Override
    public TbItem ItemQueryById(long id) {
        return itemMapper.selectByPrimaryKey(id);
    }

    @Override
    public EUIDataGridResult getPageList(int page, int rows) {

        TbItemExample example =  new TbItemExample();
        // 分页
        PageHelper.startPage(page,rows);

        List<TbItem> list = itemMapper.selectByExample(example);

        PageInfo<TbItem> info = new PageInfo<TbItem>(list);

        EUIDataGridResult result =  new EUIDataGridResult();
        result.setTotal(info.getTotal());
        result.setRows(list);

        return result;
    }
}
