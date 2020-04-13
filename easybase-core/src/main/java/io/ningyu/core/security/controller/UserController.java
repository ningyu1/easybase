package io.ningyu.core.security.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.ningyu.common.annotation.auth.CurrentUser;
import io.ningyu.common.annotation.log.AroundLog;
import io.ningyu.common.base.PageResult;
import io.ningyu.common.base.Result;
import io.ningyu.core.security.dto.CreateUser;
import io.ningyu.core.security.dto.QueryUser;
import io.ningyu.core.security.dto.UpdateDataPermission;
import io.ningyu.core.security.dto.UpdateUser;
import io.ningyu.core.security.dto.UserInfo;
import io.ningyu.core.security.entity.User;
import io.ningyu.core.security.service.IDataPermissionService;
import io.ningyu.core.security.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

/**
 * @ClassName: UserController
 * @Description: User前端控制器
 */
@RestController
@RequestMapping("/user")
@Api(value = "UserController|用户相关的前端控制器")
public class UserController {

    @Autowired
    IUserService userService;

    @Autowired
    IDataPermissionService dataPermissionService;

    @Value("${system.defaultPwd}")
    private String defaultPwd;

    /**
     * 查询所有用户
     */
    @GetMapping("/list")
    @RequiresRoles("SYSADMIN")
    @ApiOperation(value = "查询用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "userMobile", value = "手机号", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "userEmail", value = "邮箱", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "roleId", value = "角色", required = false, dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "userStatus", value = "用户状态", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "每页条数", required = false, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "current", value = "当前页", required = false, dataType = "Integer", paramType = "query") })
    public PageResult<UserInfo> list(@ApiIgnore QueryUser user, @ApiIgnore Page<UserInfo> page) {
        Page<UserInfo> pageUser = userService.selectUserList(page, user);
        PageResult<UserInfo> pageResult = new PageResult<UserInfo>(pageUser.getTotal(), pageUser.getRecords());
        return pageResult;
    }

    /**
     * 添加用户
     */
    @PostMapping("/create")
    @RequiresRoles("SYSADMIN")
    @ApiOperation(value = "添加用户")
    @AroundLog(name = "添加用户")
    public Result<?> create(@RequestBody @Valid CreateUser user) {
        boolean result = userService.createUser(user);
        if (result) {
            return new Result<>().success("添加成功");
        } else {
            return new Result<>().error("添加失败，请重试");
        }
    }

    /**
     * 修改用户
     */
    @PostMapping("/update")
    @RequiresRoles("SYSADMIN")
    @ApiOperation(value = "更新用户信息")
    @AroundLog(name = "更新用户信息")
    public Result<?> update(@RequestBody UpdateUser user) {
        boolean result = userService.updateUser(user);
        if (result) {
            return new Result<>().success("修改成功");
        } else {
            return new Result<>().error("修改失败");
        }
    }

    /**
     * 修改自己密码
     */
    @PostMapping("/pwd")
    @RequiresAuthentication
    @ApiOperation(value = "用户修改密码")
    @AroundLog(name = "用户修改密码")
    public Result<?> updatePassword(@RequestBody UpdateUser useru, @ApiIgnore @CurrentUser User tempUser) {
        String newPwd = useru.getNewPwd();
        String oldPwd = useru.getOldPwd();
        if (StringUtils.isEmpty(newPwd) || StringUtils.isEmpty(oldPwd)) {
            return new Result<>().error("密码不能为空");
        }
        if (tempUser == null || !BCrypt.checkpw(tempUser.getUserAccount() + oldPwd, tempUser.getUserPassword())) {
            return new Result<>().error("原密码错误");
        }
        UpdateUser user = new UpdateUser();
        user.setId(tempUser.getId());
        user.setUserAccount(tempUser.getUserAccount());
        user.setUserNickName(tempUser.getUserNickName());
        user.setUserEmail(tempUser.getUserEmail());
        user.setUserMobile(tempUser.getUserMobile());
        user.setUserPassword(newPwd);
        boolean result = userService.updateUser(user);
        if (result) {
            return new Result<>().success("修改成功");
        } else {
            return new Result<>().error("修改失败");
        }
    }

    /**
     * 删除用户
     */
    @PostMapping("/delete/{userId}")
    @RequiresRoles("SYSADMIN")
    @ApiOperation(value = "删除用户")
    @AroundLog(name = "删除用户")
    @ApiImplicitParam(paramType = "path", name = "userId", value = "用户ID", required = true, dataType = "Integer")
    public Result<?> delete(@PathVariable("userId") Integer userId) {
        if (null == userId) {
            return new Result<>().error("用户ID不能为空");
        }
        boolean result = userService.deleteUser(userId);
        if (result) {
            return new Result<>().success("删除成功");
        } else {
            return new Result<>().error("删除失败");
        }
    }

    /**
     * 重置密码
     */
    @RequiresRoles("SYSADMIN")
    @PostMapping("password/{userId}")
    @ApiOperation(value = "管理员重置用户密码")
    @AroundLog(name = "管理员重置用户密码")
    @ApiImplicitParam(paramType = "path", name = "userId", value = "用户ID", required = true, dataType = "Integer")
    public Result<?> resetPassword(@PathVariable("userId") Integer userId) {
        if (null == userId) {
            return new Result<>().error("用户ID不能为空");
        }
        UpdateUser user = new UpdateUser();
        user.setId(userId);
        user.setUserPassword(defaultPwd);
        boolean result = userService.updateUser(user);
        if (result) {
            return new Result<>().success("重置成功");
        } else {
            return new Result<>().error("重置失败");
        }
    }

    /**
     * 修改用户状态
     */
    @PostMapping("/status/{userId}/{status}")
    @RequiresRoles("SYSADMIN")
    @ApiOperation(value = "管理员修改用户状态")
    @AroundLog(name = "管理员修改用户状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "integer", paramType = "path"),
            @ApiImplicitParam(name = "status", value = "用户状态", required = true, dataType = "String", paramType = "path") })
    public Result<?> updateStatus(@PathVariable("userId") Integer userId, @PathVariable("status") String status) {
        if (null == userId || StringUtils.isEmpty(status)) {
            return new Result<>().error("ID和状态不能为空");
        }
        UpdateUser user = new UpdateUser();
        user.setId(userId);
        user.setUserStatus(status);
        boolean result = userService.updateUser(user);
        if (result) {
            return new Result<>().success("修改成功");
        } else {
            return new Result<>().error("修改失败");
        }
    }

    /**
     * 个人修改用户信息，限制修改字段
     */
    @PostMapping("/update/info")
    @RequiresAuthentication
    @ApiOperation(value = "用户修改个人信息")
    public Result<?> updateInfo(@RequestBody UpdateUser user, @ApiIgnore @CurrentUser User tempUser) {
        UpdateUser upUser = new UpdateUser();
        upUser.setHeadImgUrl(user.getHeadImgUrl());
        upUser.setUserName(user.getUserName());
        upUser.setUserNickName(user.getUserNickName());
        upUser.setAreas(user.getAreas());
        upUser.setId(tempUser.getId());
        boolean result = userService.updateUser(user);
        if (result) {
            return new Result<>().success("修改成功");
        } else {
            return new Result<>().error("修改失败");
        }
    }

    /**
     * 修改用户
     */
    @PostMapping("/update/data/permission")
    @RequiresRoles("SYSADMIN")
    @ApiOperation(value = "更新用户数据权限")
    @AroundLog(name = "更新用户数据权限")
    public Result<?> updateUserDataPermission(@RequestBody UpdateDataPermission updateDataPermission) {
        boolean result = dataPermissionService.updateUserDataPermission(updateDataPermission);
        if (result) {
            return new Result<>().success("修改成功");
        } else {
            return new Result<>().error("修改失败");
        }
    }

    @PostMapping(value = "/account/check")
    @RequiresRoles("SYSADMIN")
    @ApiOperation(value = "校验用户账号是否存在", notes = "校验用户账号是否存在")
    public Result<Boolean> checkUserAccount(CreateUser user) {
        String userAccount = user.getUserAccount();
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_account", userAccount);
        if(null != user.getId()) {
            userQueryWrapper.ne("id", user.getId());
        }
        int count = userService.count(userQueryWrapper);

        if (count == 0) {
            return new Result<Boolean>().success().put(true);
        } else {
            return new Result<Boolean>().success().put(false);
        }
    }

    @PostMapping(value = "/nickname/check")
    @RequiresRoles("SYSADMIN")
    @ApiOperation(value = "校验用户昵称是否存在", notes = "校验用户昵称是否存在")
    public Result<Boolean> checkUserNickname(CreateUser user) {
        String userNickName = user.getUserNickName();
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_nick_name", userNickName);
        if(null != user.getId()) {
            userQueryWrapper.ne("id", user.getId());
        }
        int count = userService.count(userQueryWrapper);
        if (count == 0) {
            return new Result<Boolean>().success().put(true);
        } else {
            return new Result<Boolean>().success().put(false);
        }
    }

    @PostMapping(value = "/mobile/check")
    @RequiresRoles("SYSADMIN")
    @ApiOperation(value = "校验用户手机号是否存在", notes = "校验用户手机号是否存在")
    public Result<Boolean> checkUserMobile(CreateUser user) {
        String userMobile = user.getUserMobile();
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_mobile", userMobile);
        if(null != user.getId()) {
            userQueryWrapper.ne("id", user.getId());
        }
        int count = userService.count(userQueryWrapper);
        if (count == 0) {
            return new Result<Boolean>().success().put(true);
        } else {
            return new Result<Boolean>().success().put(false);
        }
    }

    @PostMapping(value = "/email/check")
    @RequiresRoles("SYSADMIN")
    @ApiOperation(value = "校验用户电子邮箱是否存在", notes = "校验用户电子邮箱是否存在")
    public Result<Boolean> checkUserEmail(CreateUser user) {
        String userEmail = user.getUserEmail();
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_email", userEmail);
        if(null != user.getId()) {
            userQueryWrapper.ne("id", user.getId());
        }
        int count = userService.count(userQueryWrapper);
        if (count == 0) {
            return new Result<Boolean>().success().put(true);
        } else {
            return new Result<Boolean>().success().put(false);
        }
    }
}
