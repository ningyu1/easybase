package io.ningyu.warehouse.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.ningyu.warehouse.dto.QueryWarehouse;
import io.ningyu.warehouse.entity.WarehouseEntity;
import io.ningyu.warehouse.mapper.WarehouseMapper;
import io.ningyu.warehouse.service.IWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现
 * </p>
 *
 * @author ningyu
 * @since 2020-04-04
 */
@Service
public class WarehouseServiceImpl extends ServiceImpl<WarehouseMapper, WarehouseEntity> implements IWarehouseService {

    @Autowired
    WarehouseMapper warehouseMapper;

    @Override
    public Page<WarehouseEntity> selectList(Page<WarehouseEntity> page, QueryWarehouse warehouse) {
        Page<WarehouseEntity> list = warehouseMapper.selectList(page, warehouse);
        return list;
    }
}
