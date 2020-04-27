package com.tankdev.hikari;

import com.tankdev.hikari.dao.UserDao;
import com.tankdev.hikari.entity.User;
import org.beetl.sql.core.DBRunner;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.query.LambdaQuery;
import org.junit.jupiter.api.AssertionsKt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class HikariDemoApplicationTests {

	@Autowired
	private SQLManager sqlManager;

	@Autowired
	private UserDao userDao;

	@Test
	void contextLoads() throws Exception {
		System.out.println(sqlManager.getDbStyle());

		sqlManager.genPojoCodeToConsole("user");
	}

	@Test
	public void testSaveData() {
		List<User> userList = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			User u = new User();
			u.setAge(102);
			u.setName("一个程序猿的异常-master-" + i);
			u.setEmail("tankdev@163.com");
			userList.add(u);
		}
		userDao.insertBatch(userList);
	}


	@Test
	public void testFindSecond() {
		List<User> all = userDao.all();
		System.out.println(all.size());
	}

	@Test
	public void testFindSecondPage() {
		LambdaQuery<User> query = userDao.createLambdaQuery();
		PageQuery<User> page = query.page(2, 10);
		System.out.println("总记录数：" + page.getTotalRow());
		System.out.println("总页数：" + page.getTotalPage());
		System.out.println("当前页码：" + page.getPageSize());
		System.out.println("返回记录数据：" + page.getPageSize());
	}


	@Test
	public void testFind() {
		List<User> userList = userDao.all();
		System.out.println(userList);
		LambdaQuery<User> query = userDao.createLambdaQuery();
		PageQuery<User> page = query.page(1, 2);
		System.out.println(page.getTotalRow());
		System.out.println(page.getList());
	}
}
