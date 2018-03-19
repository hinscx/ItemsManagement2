package mapper;

import org.apache.ibatis.annotations.Param;
import domain.Items;
import domain.ItemsCustom;
import domain.ItemsExample;
import domain.ItemsQueryVo;

import java.util.List;

public interface ItemsCustomMapper {

        // 商品查询列表
        List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo)
                throws Exception;

        int countByExample(ItemsExample example);

        int deleteByExample(ItemsExample example);

        int deleteByPrimaryKey(Integer id);

        int insert(Items record);

        int insertSelective(Items record);

        List<Items> selectByExampleWithBLOBs(ItemsExample example);

        List<Items> selectByExample(ItemsExample example);

        Items selectByPrimaryKey(Integer id);

        int updateByExampleSelective(@Param("record") Items record, @Param("example") ItemsExample example);

        int updateByExampleWithBLOBs(@Param("record") Items record, @Param("example") ItemsExample example);

        int updateByExample(@Param("record") Items record, @Param("example") ItemsExample example);

        int updateByPrimaryKeySelective(Items record);

        int updateByPrimaryKeyWithBLOBs(Items record);

        int updateByPrimaryKey(Items record);

}
