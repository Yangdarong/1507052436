package com.web.action.test;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.beans.Book;
import com.beans.Category;
import com.beans.User;
import com.service.IBookService;
import com.service.ICategoryService;
import com.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/applicationContext.xml"})
public class TestBookStore {

	@Autowired
	private IUserService us;
	
	@Autowired
	private ICategoryService cs;
	
	@Autowired
	private IBookService bs;

	/**
	 * 测试1 : 用户登录
	 */
	@Test
	public void test1() {
		List<User> result = us.userLogin("admin", "admin");
		for(Object o : result) {
			System.out.println(o);
		}
	}
	
	/**
	 * 测试2 : 获取分类信息
	 */
	@Test
	public void test2() {
		DetachedCriteria dCriteria = DetachedCriteria.forClass(Category.class);
		List<Category> categories = cs.findAllCategories(dCriteria);
		for(Object object : categories) {
			System.out.println(object);
		}
	}
	
	/**
	 * 测试3 : 获取所有图书信息
	 */
	@Test
	public void test3() {
		DetachedCriteria dCriteria = DetachedCriteria.forClass(Book.class);
		dCriteria.add(Restrictions.eq("category.id", 1));
		List<Book> books = bs.findAllBooks(dCriteria);
		for(Object object : books) {
			System.out.println(object);
		}
	}

	/**
	 * 测试4 : 获取该用户的收藏信息
	 */
	@Test
	public void test4() {
		User user = new User();
		user.setId(1);
		
		List<Book> books = bs.findByUserFavor(user);
		for(Object object : books) {
			System.out.println(object);
		}
	}
}
