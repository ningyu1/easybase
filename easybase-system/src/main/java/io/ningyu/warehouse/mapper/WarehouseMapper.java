package io.ningyu.warehouse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.ningyu.warehouse.entity.WarehouseEntity;
import io.ningyu.warehouse.dto.QueryWarehouse;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author ningyu
 * @since 2020-04-04
 */
public interface WarehouseMapper extends BaseMapper<WarehouseEntity> {

    Page<WarehouseEntity> selectList(Page<WarehouseEntity> page, @Param("warehouse") QueryWarehouse warehouse);

}
