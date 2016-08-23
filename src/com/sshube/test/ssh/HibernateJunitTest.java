package com.sshube.test.ssh;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sshube.hibernate.test.impl.HibernateTestImpl;
import com.sshube.hibernate.test.itf.HibernateTestItf;
import com.sshube.spring.bean.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class HibernateJunitTest {
    /**
     * ≤‚ ‘hiberaneª∑æ≥
     */
    @Test
    public void hibernate() {
        HibernateTestItf service = new HibernateTestImpl();
        User user = new User();
        service.save(user);
    }

}
