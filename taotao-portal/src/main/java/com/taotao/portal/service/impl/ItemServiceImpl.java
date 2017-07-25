package com.taotao.portal.service.impl;

import com.taotao.pojo.TaotaoResult;
import com.taotao.portal.pojo.ItemInfo;
import com.taotao.portal.service.ItemService;
import com.taotao.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by geek on 2017/7/24.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;

    @Value("${ITEM_PATH_URL}")
    private String ITEM_PATH_URL;

    @Override
    public ItemInfo getItemById(Long id) {
        ItemInfo result = null;
        String URL = REST_BASE_URL + ITEM_PATH_URL + "/info/" + id;
        try {
            String jSon = HttpClientUtil.doGet(URL);

            if(!StringUtils.isEmpty(jSon)){
                TaotaoResult taotaoResult = TaotaoResult.formatToPojo(jSon, ItemInfo.class);
                if (taotaoResult.getStatus() == 200) {
                    result = (ItemInfo) taotaoResult.getData();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
    }
        return result;
    }
}
