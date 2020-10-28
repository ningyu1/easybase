package ${cfg.DtoPackage};

import ${package.Entity}.${entity};
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

/**
 * <p>
 * ${table.comment!}
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Query${entity?replace("Entity", "")} extends ${entity} implements Serializable {

    private static final long serialVersionUID = 1L;
    private String startDate;
    private String endDate;
}
