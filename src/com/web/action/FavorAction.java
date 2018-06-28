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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.beans.Book;
import com.beans.Favor;
import com.beans.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import com.service.IBookService;
import com.service.IFavorService;

/**
 * 对收藏的操作
 * @author 杨鑫荣
 *
 */

@Controller("favorAction")
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/")
@Results({
	@Result(name="listFavor",type="dispatcher",location="/book/booklist.jsp"),
	@Result(name="noUser",type="dispatcher",location="/loginfirst.jsp"),
	@Result(name="addFavor",type="dispatcher",location="/user/addfavor_suc.jsp"),
	@Result(name="cancelFavor",type="dispatcher",location="/user/cancelfavor_suc.jsp"),
	
})
public class FavorAction extends ActionSupport implements ModelDriven<Favor>{

	@Resource(name="favorService")
	private IFavorService favorService;
	
	@Resource(name="bookService")
	private IBookService bookService;
	
	private Favor favor = new Favor();
	
	@Override
	public Favor getModel() {
		// TODO Auto-generated method stub
		return favor;
	}
	
	/**
	 * 查询该用户的收藏列表
	 * @return
	 */
	@Action("user_listfavor")
	public String getFavorList() {
		// 1 获取用户Id 进行
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		List<Book> result = bookService.findByUserFavor(user);
		
		// 2 放入值栈
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		valueStack.set("title", user.getName() + "的收藏");
		valueStack.set("booklist", result);
		return "listFavor";
	}
	
	/**
	 * 添加收藏
	 * @return
	 */
	@Action("user_addfavor")
	public String addFavor() {
		// 1 首先判断是否有用户登录
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user == null) 
			return "noUser";
		
		Integer bookId = Integer.parseInt(request.getParameter("bookid"));
		Book book = new Book(bookId);
		Favor favor = new Favor(user, book);
		favorService.saveFavor(favor);
		
		return "addFavor";
	}
	
	/**
	 * 取消收藏
	 * @return
	 */
	@Action("user_cancelfavor")
	public String cancelFavor() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		Integer bookId = Integer.parseInt(request.getParameter("bookid"));
		Book book = new Book(bookId);
		Favor favor = new Favor(user, book);
		favorService.cancelFavor(favor);
		
		return "cancelFavor";
	}
	
}
