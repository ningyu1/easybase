package io.ningyu.system.warehouse.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.ningyu.system.warehouse.dto.QueryWarehouse;
import io.ningyu.system.warehouse.entity.WarehouseEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 测试表 服务类
 * </p>
 *
 * @author ningyu
 * @since 2020-04-04
 */
public interface IWarehouseService extends IService<WarehouseEntity> {

    Page<WarehouseEntity> selectList(Page<WarehouseEntity> page, QueryWarehouse warehouse);

}
