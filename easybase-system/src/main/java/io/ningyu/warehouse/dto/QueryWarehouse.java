package io.ningyu.warehouse.dto;

import io.ningyu.warehouse.entity.WarehouseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * DTO
 * </p>
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
