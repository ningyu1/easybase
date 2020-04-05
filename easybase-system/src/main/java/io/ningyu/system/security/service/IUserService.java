package io.ningyu.system.security.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import io.ningyu.system.security.dto.CreateUser;
import io.ningyu.system.security.dto.QueryUser;
import io.ningyu.system.security.dto.UpdateUser;
import io.ningyu.system.security.dto.UserInfo;
import io.ningyu.system.security.entity.User;

/**
 * @ClassName: IUserService
 * @Description: 用户相关操接口
 */
public interface IUserService extends IService<User> {

    /**
     * 创建用户
     * 
     * @param user
     * @param
     * @return
     */
    boolean createUser(CreateUser user);

    /**
     * 更新用户
     * 
     * @param user
     * @param
     * @return
     */
    boolean updateUser(UpdateUser user);

    /**
     * 删除用户
     * @param userId
     * @return
     */
    boolean deleteUser(Integer userId);
    
    /**
     * 根据用户名查询用户
     * 
     * @param userAccount 用户账号
     * @return 用户
     */
    User queryUserByAccount(String userAccount);

    /**
     * 分页查询用户
     * @param page
     * @param user
     * @return
     */
    Page<UserInfo> selectUserList(Page<UserInfo> page, QueryUser user);
}
