package io.ningyu.warehouse.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.ningyu.warehouse.dto.QueryWarehouse;
import io.ningyu.warehouse.entity.WarehouseEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务接口
 * </p>
 *
 * @author ningyu
 * @since 2020-04-04
 */
public interface IWarehouseService extends IService<WarehouseEntity> {

    Page<WarehouseEntity> selectList(Page<WarehouseEntity> page, QueryWarehouse warehouse);

}
