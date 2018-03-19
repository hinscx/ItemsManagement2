package utils;

import dao.impl.ItemsDaoImpl;
import domain.ItemsCustom;

import java.util.Date;

public class FillDemo {
    public static void main(String[] args) throws  Exception{

        ItemsDaoImpl itemsDao = new ItemsDaoImpl();

        for (int i=0;i<50;i++)
        {
            ItemsCustom itemsCustom=new ItemsCustom();
            itemsCustom.setName("itemsCustom"+i);
            itemsCustom.setPrice(8.8F);
            itemsCustom.setCreatetime(new Date());
            itemsCustom.setDetail("自动添加");

            itemsDao.addItem(itemsCustom);
        }
        
    }
}
