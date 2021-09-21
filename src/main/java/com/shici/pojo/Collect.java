package com.shici.pojo;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * FileName: Collect
 * Description:
 * Author: CSH
 * Date: 2021/1/9 16:36
 * Version: 1.0
 */
public class Collect {
    private Map<Integer,CollectItem> items = new LinkedHashMap<Integer,CollectItem>();

    public void addItem(CollectItem collectItem){
        //先查看收藏夹中是否已经添加过此作品，如果未添加，则加入收藏夹，如果已经添加，则不做处理；
        CollectItem item = items.get(collectItem.getId());

        if (item == null){
            //之前没有添加过此作品
            items.put(collectItem.getId(),collectItem);
        }
    }

    public void deleteItem(Integer id){
        items.remove(id);
    }

    public void clear(){
        items.clear();
    }

    public Map<Integer, CollectItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CollectItem> items) {
        this.items = items;
    }

    public Integer getTotalCount(){
        Integer totalCount = 0;
        for (Map.Entry<Integer,CollectItem> entry : items.entrySet()){
            totalCount += 1;
        }
        return totalCount;
    }

    @Override
    public String toString() {
        return "Collect{" +
                "totalCount=" + getTotalCount() +
                "items=" + items +
                '}';
    }
}
