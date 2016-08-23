package com.sshube.it.service.itf;

import com.sshube.spring.bean.User;

public interface UserADUIItf {
    /**
     * 新增用户
     * 
     * @param user
     */
    public void addUserVO(User user);

    /**
     * 删除用户
     * 
     * @param user
     */
    public void delUserVO(User user);

    /**
     * 更新用户
     * 
     * @param user
     */
    public void updateUserVO(User user);

}
