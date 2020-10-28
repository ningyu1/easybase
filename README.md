Easybase is a rapid development framework

# 介绍
Easybase是一款前后端分离的开源开发框架，基于springboot+vue开发的后台管理系统，前端基于vue-element-admin。

# 软件结构
1. 使用Spring Boot + MyBatis + MyBatis-Plus + Shiro + Vue + ElementUI + Swagger2等开源框架。
2. 权限设计使用RBAC模型

# 功能说明
1. 前后端分离，菜单权限使用vue-element-admin原生方法和后台资源配置两种方案。
2. 支持权限控制到按钮，命名规则参考shiro权限标签。
3. 权限使用基于角色的权限控制RBAC模型，可以控制到菜单，按钮。
4. 支持数据权限控制。
5. 定时任务使用xxl-job。

# 目录说明
1. easebase-common 基础组件定义及工具类
2. easybase-core 基础核心功能实现，包含：配置、拦截器等
3. easybase-system Java后台服务代码，具体业务逻辑实现写在这里。
4. easybase-generator 代码生成器，可读取数据库表生成controller、dto、mapper、service、ui.vue

# 数据权限
在`Controller`层添加`@DataFilter`注解
在入参添加`DataPermissionCondition`（不分页时，select，delete，update使用）或者`DataPermissionPage`（分页时，select使用）字段

```
@DataFilter(orgIdAlias = "X.organization_id") 
public PageResult list(UserVO user, DataPermissionPage page) { 
    Page pageUser = userService.selectUserList(page, user); 
    PageResult pageResult = new PageResult(pageUser.getTotal(), pageUser.getRecords()); 
    return pageResult; 
}
```
