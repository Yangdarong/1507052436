package com.web.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.beans.Category;
import com.beans.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import com.service.ICategoryService;
import com.service.IUserService;

/**
 * 用户的操作类
 * @author 杨鑫荣
 *
 */
@Controller("userAction")
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/")
@Results({
	@Result(name="entry",type="dispatcher",location="/entry.jsp"),
	@Result(name="loginFail", type="dispatcher", location="/user/login_fail.jsp"),
	@Result(name="loginSuccess", type="dispatcher", location="/user/login_suc.jsp"),
	@Result(name="logout", type="dispatcher", location="/user/logout_suc.jsp"),
})
public class UserAction extends ActionSupport implements ModelDriven<User> {

	@Resource(name="categoryService")
	private ICategoryService categoryService;
	
	@Resource(name="userService")
	private IUserService userService;
	
	private User user = new User();
	
	@Override
	public User getModel() {
		return user;
	}
	
	/**
	 * 用户注销
	 * @return
	 */
	@Action("user_logout")
	public String userLogout() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		
		session.removeAttribute("user");
		
		return "logout";
	}
	
	/**
	 * 用户登录(用户和密码)
	 * @return
	 */
	@Action("user_login")
	public String userLogin() {
		// 1 获取表单信息
		HttpServletRequest request = ServletActionContext.getRequest();
		String userName = request.getParameter("name");
		String userPasswd = request.getParameter("passwd");
		
		// 2 连接数据库比较
		List<User> users = userService.userLogin(userName, userPasswd);
		if(users.isEmpty()) {
			return "loginFail";
		} else {
			User user = users.get(0);
			// 3 将结果放入session
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			
			return "loginSuccess";
		}
		
	}
	
	/**
	 * 进入主页(1.展示图书分类列表)
	 * @return
	 */
	@Action("entry")
	public String entry() {
		// 1 获取分类列表
		DetachedCriteria dCriteria = DetachedCriteria.forClass(Category.class);
		List<Category> categories = categoryService.findAllCategories(dCriteria);
		
		// 2 将分类列表存入值栈
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		valueStack.set("category_list", categories);
		
		return "entry";
	}
}
