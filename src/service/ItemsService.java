package service;


import domain.Items;
import domain.PageBean;
import org.springframework.stereotype.Service;
import domain.ItemsCustom;
import domain.ItemsQueryVo;

import java.util.List;

@Service
public interface ItemsService {

    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;

    public ItemsCustom findItemsById(int id) throws Exception;

    public void updateItems(ItemsCustom itemsCustom) throws Exception;

    public void deleteItems(Integer[] delete_id) throws Exception;

    public void addItem(ItemsCustom itemsCustom) throws Exception;

    public PageBean<Items> findItemsListPage(int pc, int pr) throws Exception;

}
