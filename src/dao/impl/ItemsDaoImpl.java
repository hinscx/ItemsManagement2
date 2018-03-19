package dao.impl;

import dao.ItemsDao;
import domain.Items;
import domain.ItemsCustom;
import domain.ItemsQueryVo;
import domain.PageBean;
import exception.ItemsException;
import mapper.ItemsCustomMapper;
import mapper.ItemsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import utils.BeanUtils;

import java.util.List;

@Component("itemsDao1")
public class ItemsDaoImpl implements ItemsDao{

    //注入mapper
    @Autowired
    private ItemsCustomMapper itemsCustomMapper;

    //商品的查询列表
    @Override
    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception {

        return itemsCustomMapper.findItemsList(itemsQueryVo);
    }

    //注入依赖对象itemsMapper
    @Autowired
    private ItemsMapper itemsMapper;

    @Override
    public ItemsCustom findItemsById(int id) throws Exception {


        Items items=itemsMapper.selectByPrimaryKey(id);


        if (items==null)
        {
            throw new ItemsException("修改商品信息不存在");
        }

        //在这里以后随着需求的变化，需要查询商品的其它相关信息，返回到controller
        //所以这个时候用到扩展类更好，如下
        ItemsCustom itemsCustom=new ItemsCustom();
        //将items的属性拷贝到itemsCustom
        BeanUtils.copyProperties(items,itemsCustom);

        return itemsCustom;
    }

    @Override
    public void updateItems(ItemsCustom itemsCustom) throws Exception {



        //对于关键业务数据的非空校验
        if (itemsCustom.getId()==null)
        {
            //抛出异常，提示调用接口的用户，id不能唯恐
            //...
        }

        itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);
    }

    @Override
    public void deleteItems(Integer[] delete_id) throws Exception {
        for(Integer id : delete_id){
            itemsMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public void addItem(ItemsCustom itemsCustom) throws Exception {
        itemsMapper.insert(itemsCustom);
    }

    @Override
    public PageBean<Items> findItemsListPage(int pc, int pr) throws Exception {
        PageBean<Items> pb = new PageBean<>();

        pb.setPc(pc);
        pb.setPr(pr);

        int tr = itemsMapper.numberOfItems();

        pb.setTr(tr);

        int start = (pc-1)*pr;
        pb.setStart(start);

        List<Items> itemsList = itemsMapper.queryForPage(pb);

        pb.setItemsList(itemsList);

        return pb;
    }
}
