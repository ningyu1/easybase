package io.ningyu.position.dto;

import io.ningyu.position.entity.PositionEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ningyu
 * @since 2020-04-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QueryPosition extends PositionEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    private String startDate;
    private String endDate;
}
