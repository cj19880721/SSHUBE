package com.sshube.test.ssh;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sshube.it.service.itf.UserADUIItf;
import com.sshube.spring.bean.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class HibernateAndSpringTest {
    @Resource
    private UserADUIItf userADUService;

    @Test
    // 测试Hibernate和Spring整合后
    public void hibernateAndSpring() {
        User user = new User("testHibernate", "1", "1", new Long(2));
        this.userADUService.addUserVO(user);
    }
}
