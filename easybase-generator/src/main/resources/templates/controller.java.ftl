package ${package.Controller};


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.ningyu.common.annotation.log.AroundLog;
import io.ningyu.common.base.PageResult;
import io.ningyu.common.base.Result;
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import ${cfg.DtoPackage}.Query${entity?replace("Entity", "")};
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    @Autowired
    private ${table.serviceName} ${table.serviceImplName};

    /**
     * 添加
     */
    @PostMapping("/create")
    @ApiOperation(value = "添加")
    @AroundLog(name = "添加")
    public Result<Query${entity?replace("Entity", "")}> create(@RequestBody Query${entity?replace("Entity", "")} vo) {
        ${entity} entity = new ${entity}();
        BeanCopier.create(Query${entity?replace("Entity", "")}.class, ${entity}.class, false).copy(vo, entity, null);
        boolean result = ${table.serviceImplName}.save(entity);
        if (result) {
            vo.setId(entity.getId());
            return new Result<Query${entity?replace("Entity", "")}>().success("添加成功").put(vo);
        } else {
            return new Result<Query${entity?replace("Entity", "")}>().error("添加失败，请重试");
        }
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "更新")
    @AroundLog(name = "更新")
    public Result<Query${entity?replace("Entity", "")}> update(@RequestBody Query${entity?replace("Entity", "")} vo) {
    ${entity} entity = new ${entity}();
        BeanCopier.create(Query${entity?replace("Entity", "")}.class, ${entity}.class, false).copy(vo, entity, null);
        boolean result = ${table.serviceImplName}.updateById(entity);
        if (result) {
            return new Result<Query${entity?replace("Entity", "")}>().success("修改成功").put(vo);
        } else {
            return new Result<Query${entity?replace("Entity", "")}>().error("修改失败");
        }
    }

    /**
     * 删除
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除")
    @AroundLog(name = "删除")
    @ApiImplicitParam(paramType = "path", name = "id", value = "id", required = true, dataType = "Integer")
    public Result<?> delete(@PathVariable("id") Integer id) {
        QueryWrapper<${entity}> qw = new QueryWrapper<${entity}>();
        qw.and(e -> e.eq("id", id));
        boolean result = ${table.serviceImplName}.remove(qw);
        if (result) {
            return new Result<>().success("删除成功").put(Boolean.TRUE);
        } else {
            return new Result<>().error("删除失败").put(Boolean.FALSE);
        }
    }

    /**
     * 批量删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "批量删除")
    @AroundLog(name = "批量删除")
    public Result<?> delete(@RequestBody List<Integer> ids) {
        boolean result = ${table.serviceImplName}.removeByIds(ids);
        if (result) {
            return new Result<>().success("删除成功").put(Boolean.TRUE);
        } else {
            return new Result<>().error("删除失败").put(Boolean.FALSE);
        }
    }

    /**
     * 通过id获取详情
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "查询详情", notes = "查询详情")
    @ApiImplicitParam(paramType = "query", name = "id", value = "id", required = true, dataType = "Integer")
    public Result<Query${entity?replace("Entity", "")}> get(@PathVariable("id") Integer id) {
        ${entity} entity = ${table.serviceImplName}.getById(id);
        Query${entity?replace("Entity", "")} vo = new Query${entity?replace("Entity", "")}();
        if (entity != null) {
            BeanCopier.create(${entity}.class, Query${entity?replace("Entity", "")}.class, false).copy(entity, vo, null);
        }
        return new Result<Query${entity?replace("Entity", "")}>().success().put(vo);
    }

    /**
     * 查询列表
     *
     * @param vo
     * @param page
     * @return
     */
    @GetMapping(value = "/list")
    @ApiOperation(value = "查询列表", notes = "查询列表")
    public PageResult<${entity}> list(Query${entity?replace("Entity", "")} vo, Page<${entity}> page) {
        Page<${entity}> pageList = ${table.serviceImplName}.selectList(page, vo);
        PageResult<${entity}> pageResult = new PageResult<${entity}>(pageList.getTotal(), pageList.getRecords());
        return pageResult;
    }

}
</#if>