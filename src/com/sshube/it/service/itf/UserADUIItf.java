package com.sshube.it.service.itf;

import com.sshube.spring.bean.User;

public interface UserADUIItf {
    /**
     * �����û�
     * 
     * @param user
     */
    public void addUserVO(User user);

    /**
     * ɾ���û�
     * 
     * @param user
     */
    public void delUserVO(User user);

    /**
     * �����û�
     * 
     * @param user
     */
    public void updateUserVO(User user);

}
