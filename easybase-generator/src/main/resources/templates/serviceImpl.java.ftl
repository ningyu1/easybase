package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${cfg.DtoPackage}.Query${entity?replace("Entity", "")};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {


    @Autowired
    ${table.mapperName} mapper;

    @Override
    public Page<${entity}> selectList(Page<${entity}> page, Query${entity?replace("Entity", "")} param) {
        Page<${entity}> list = mapper.selectList(page, param);
        return list;
    }
}
</#if>