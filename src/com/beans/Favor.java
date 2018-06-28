package com.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 记录收藏关系的实体类
 * @author 杨鑫荣
 *
 */
@Entity
@Table(name="favor_books")
public class Favor implements Serializable {

	@Id
	@ManyToOne(targetEntity=User.class)
	@JoinColumn(name="user_id", referencedColumnName="user_id")
	private User user;
	
	@Id
	@ManyToOne(targetEntity=Book.class)
	@JoinColumn(name="book_id", referencedColumnName="book_id")
	private Book book;

	public Favor(User user, Book book) {
		// TODO Auto-generated constructor stub
		this.user = user;
		this.book = book;
	}

	public Favor() {
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@Override
	public String toString() {
		return "Favor [user=" + user + ", book=" + book + "]";
	}
	
	
}
