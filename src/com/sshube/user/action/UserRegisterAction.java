package com.sshube.user.action;

import com.opensymphony.xwork2.ActionSupport;
import com.sshube.it.service.itf.UserADUIItf;
import com.sshube.spring.bean.User;

public class UserRegisterAction extends ActionSupport {

    private static final long serialVersionUID = -1254666722878277315L;

    private UserADUIItf userservice;

    private User user;

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserADUIItf getUserservice() {
        return this.userservice;
    }

    public void setUserservice(UserADUIItf userservice) {
        this.userservice = userservice;
    }

    public String update() {
        System.out.println("-----update-----");
        System.out.println(this.userservice);
        this.userservice.updateUserVO(this.user);
        return "index";
    }

    public String save() {
        System.out.println("-----save-----");
        System.out.println(this.userservice);
        return "index";
    }

    @Override
    public void addActionError(String anErrorMessage) {
        String s = anErrorMessage;
        System.out.println(s);
    }

    @Override
    public void addActionMessage(String aMessage) {
        String s = aMessage;
        System.out.println(s);

    }

    @Override
    public void addFieldError(String fieldName, String errorMessage) {
        String s = errorMessage;
        String f = fieldName;
        System.out.println(s);
        System.out.println(f);

    }
}
