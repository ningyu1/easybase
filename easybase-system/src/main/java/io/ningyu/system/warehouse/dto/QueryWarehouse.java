package io.ningyu.system.warehouse.dto;

import io.ningyu.system.warehouse.entity.WarehouseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <功能描述>
 *
 * @author ningyu
 * @date 2020/4/4 21:58
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QueryWarehouse extends WarehouseEntity implements Serializable {
    private static final long serialVersionUID = 6381663627668122699L;

    private String startDate;

    private String endDate;
}
