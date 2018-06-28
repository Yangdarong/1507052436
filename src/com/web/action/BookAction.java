package com.web.action;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder.Case;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.beans.Book;
import com.beans.Category;
import com.beans.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import com.service.IBookService;
import com.service.IFavorService;

/**
 * 对书本的操作
 * @author 杨鑫荣
 *
 */
@Controller("bookAction")
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/")
@Results({
	@Result(name="getBooks",type="dispatcher",location="/book/booklist.jsp"),
	@Result(name="getBook",type="dispatcher",location="/book/info_suc.jsp"),
	@Result(name="searchBooks",type="dispatcher",location="/book/booklist.jsp"),
	@Result(name="addBook",type="dispatcher",location="/book/add_suc.jsp")
	
	
})
public class BookAction extends ActionSupport implements ModelDriven<Book> {

	@Resource(name="bookService")
	private IBookService bookService;
	
	@Resource(name="favorService")
	private IFavorService favorService;
	
	private boolean is_addfavor = false;
	
	private Book book = new Book();

	@Override
	public Book getModel() {
		// TODO Auto-generated method stub
		return book;
	}
	
	/**
	 * 通过关键字搜索图书
	 * @return
	 */
	@Action("book_search")
	public String searchBooks() {
		// 获取选中标签 和关键字
		HttpServletRequest request = ServletActionContext.getRequest();
		DetachedCriteria dCriteria = DetachedCriteria.forClass(Book.class);
		
		Integer key = Integer.parseInt(request.getParameter("type"));
		String kw = request.getParameter("keyword");
		switch (key) {
		case 1:
			dCriteria.add(Restrictions.like("name", "%" + kw + "%"));
			break;
		case 2:
			dCriteria.add(Restrictions.like("author", "%" + kw + "%"));
		default:
			break;
		}
		
		// 查询结果放入值栈
		List<Book> result = bookService.findAllBooks(dCriteria);
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		valueStack.set("booklist", result);
		valueStack.set("title", kw);
		
		return "searchBooks";
	}
	
	/**
	 * 获取这个类的图书信息
	 * @return
	 */
	@Action("category_getbooks")
	public String getBooksByCategory() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Integer categoryId = Integer.parseInt(request.getParameter("id"));
		DetachedCriteria dCriteria = DetachedCriteria.forClass(Book.class);
		dCriteria.add(Restrictions.eq("category.id", categoryId));
		List<Book> result = bookService.findAllBooks(dCriteria);
		
		// 存入值栈
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		valueStack.set("booklist", result);
		valueStack.set("title", result.get(0).getCategory().getName());
		return "getBooks";
	}
	
	/**
	 * 获取这本图书的信息
	 * @return
	 */
	@Action("book_info")
	public String getBookInfo() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Integer bookId = Integer.parseInt(request.getParameter("bookid"));
		DetachedCriteria dCriteria = DetachedCriteria.forClass(Book.class);
		dCriteria.add(Restrictions.eq("id", bookId));
		List<Book> result = bookService.findAllBooks(dCriteria);
		// 判断用户是否收藏了这本书
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user != null) {
			if(favorService.isFavor(user, bookId)) 
				setIs_addfavor(true);
			else 
				setIs_addfavor(false);
		} 
		
		// 存入值栈
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		valueStack.set("book", result.get(0));
		return "getBook";
	}
	
	/**
	 * 添加书本
	 * @return
	 */
	@Action("book_add")
	public String addBook() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String name = request.getParameter("name");
		Category category = new Category(Integer.parseInt(request.getParameter("categoryid")));
		String author = request.getParameter("author");
		Float price = Float.parseFloat(request.getParameter("price"));
		String desc = request.getParameter("desc");
		Book book = new Book(name, author, price, desc, category);
		
		bookService.saveBook(book);
		return "addBook";
	}

	public boolean isIs_addfavor() {
		return is_addfavor;
	}

	public void setIs_addfavor(boolean is_addfavor) {
		this.is_addfavor = is_addfavor;
	}
	
	
}
