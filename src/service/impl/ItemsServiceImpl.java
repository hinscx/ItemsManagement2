package service.impl;



import dao.ItemsDao;
import domain.PageBean;
import mapper.ItemsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import domain.Items;
import domain.ItemsCustom;
import mapper.ItemsCustomMapper;
import domain.ItemsQueryVo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import service.ItemsService;
import utils.BeanUtils;

import java.util.List;

@Component("itemsService1")
public class ItemsServiceImpl implements ItemsService {

    //注入dao
    @Autowired
    @Qualifier("itemsDao1")
    private ItemsDao itemsDao;

    //商品的查询列表
    @Override
    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception {

        return itemsDao.findItemsList(itemsQueryVo);
    }


    @Override
    public ItemsCustom findItemsById(int id) throws Exception {

        return itemsDao.findItemsById(id);

    }

    @Override
    public void updateItems(ItemsCustom itemsCustom) throws Exception {

        itemsDao.updateItems(itemsCustom);
    }
    @Override
    public void deleteItems(Integer[] delete_id) throws Exception {
        itemsDao.deleteItems(delete_id);
    }

    @Override
    public void addItem(ItemsCustom itemsCustom) throws Exception{

        itemsDao.addItem(itemsCustom);
    }

    @Override
    public PageBean<Items> findItemsListPage(int pc, int pr) throws Exception {

        return itemsDao.findItemsListPage(pc, pr);
    }
}