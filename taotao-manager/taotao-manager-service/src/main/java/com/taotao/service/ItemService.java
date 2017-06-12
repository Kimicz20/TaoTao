package com.taotao.service;

import com.taotao.pojo.EUIDataGridResult;
import com.taotao.pojo.TbItem;

/**
 * Created by geek on 2017/6/11.
 */
public interface ItemService {
    TbItem ItemQueryById(long id);

    EUIDataGridResult getPageList(int page,int rows);

}
