package utils;

import domain.Items;
import domain.ItemsCustom;


public class BeanUtils {

    public static void copyProperties(Items items, ItemsCustom itemsCustom){

        itemsCustom.setCreatetime(items.getCreatetime());
        itemsCustom.setDetail(items.getDetail());
        itemsCustom.setId(items.getId());
        itemsCustom.setPic(items.getPic());
        itemsCustom.setName(items.getName());
        itemsCustom.setPrice(items.getPrice());

    }

}
