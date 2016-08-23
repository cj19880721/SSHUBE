package com.sshube.test.ssh;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

	/* 
	 * Spring3.1后多了个spring-test-4.2.4.RELEASE.jar包，这个jar包专门用来支持JUnit基于注解的测试的，该jar包在spring-4.2.4-core�?
	 * 该jar包里有个SpringJUnit4ClassRunner.class，用@RunWith注解加进来即�?
	 * 
	 * 注解@ContextConfiguration表示将ApplicationContext对象注入进来，就不用像以�?��样在测试程序里先new了，直接使用 
	 */
	@RunWith(SpringJUnit4ClassRunner.class) 
	@ContextConfiguration(locations="classpath:beans.xml") 
	public class TestSpring {
		@Resource
		private Date date; 
		@Test //测试Spring IOC的开发环�?
		public void springIoc() { 
			System.out.println(date); 
		} 
	} 

