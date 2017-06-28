package com.taotao.rest.service.impl;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.rest.pojo.ItemCatNode;
import com.taotao.rest.pojo.ItemCatsResult;
import com.taotao.rest.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geek on 2017/6/28.
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Override
    public ItemCatsResult getItemCatList() {

        ItemCatsResult result = new ItemCatsResult();
        result.setData(getCatList(0));
        return result;
    }

    /**
     * 递归调用函数获取ItemCat的所有节点
     * @param id
     * @return
     */
    private List getCatList(long id){
        //数据库查找
        TbItemCatExample example = new TbItemCatExample();
        Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(id);
        List<TbItemCat> list =itemCatMapper.selectByExample(example);

        int count =1;
        //递归查找
        List resultList = new ArrayList();
        for (TbItemCat item:list) {
            if(id == 0 && count++ >14)
                break;
            //判断是不是父节点
            if (item.getIsParent()){
                ItemCatNode node = new ItemCatNode();
                if(id == 0){
                   node.setName("<ahref='/products/"+item.getId()+".html'>"+item.getName()+"</a>");
                }else{
                    node.setName(item.getName());
                }
                node.setUrl("/products/"+item.getId()+".html");
                node.setItem(getCatList(item.getId()));
                resultList.add(node);
            }else{
                //叶子节点
                resultList.add("/products/"+item.getId()+".html|"+item.getName());
            }
        }
        return resultList;
    }
}
