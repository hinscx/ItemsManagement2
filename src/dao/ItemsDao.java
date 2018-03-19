package dao;

import domain.Items;
import domain.ItemsCustom;
import domain.ItemsQueryVo;
import domain.PageBean;
import mapper.ItemsCustomMapper;
import mapper.ItemsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import utils.BeanUtils;

import java.util.List;

public interface ItemsDao {

    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception ;

    public ItemsCustom findItemsById(int id) throws Exception ;

    public void updateItems(ItemsCustom itemsCustom) throws Exception;

    public void deleteItems(Integer[] delete_id) throws Exception;

    public void addItem(ItemsCustom itemsCustom)throws Exception;

    public PageBean<Items> findItemsListPage(int pc, int pr) throws Exception;
}
