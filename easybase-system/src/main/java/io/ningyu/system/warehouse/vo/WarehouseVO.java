package io.ningyu.system.warehouse.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <功能描述>
 *
 * @author ningyu
 * @date 2020/4/4 21:58
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WarehouseVO implements Serializable {
    private static final long serialVersionUID = 6381663627668122699L;

    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 编号
     */
    private String code;

    /**
     * 1有效，0禁用
     */
    private String status;

    private String description;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    private Integer createUser;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 操作人
     */
    private Integer updateUser;

    /**
     * 1:删除 0:不删除
     */
    private String delFlag;
}
