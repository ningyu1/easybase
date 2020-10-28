package ${package.Service};

import ${package.Entity}.${entity};
import ${cfg.DtoPackage}.Query${entity?replace("Entity", "")};
import ${superServiceClassPackage};
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

    Page<${entity}> selectList(Page<${entity}> page, Query${entity?replace("Entity", "")} param);

}
</#if>