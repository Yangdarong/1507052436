package com.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 书本的实体类
 * @author 杨鑫荣
 *
 */
@Entity
@Table(name="book_info")
public class Book  implements java.io.Serializable {

	@Id
	@Column(name="book_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="book_name")
	private String name;
	
	@Column(name="book_author")
	private String author;
	
	@Column(name="book_price")
	private Float price;
	
	@Column(name="book_desc")
	private String desc;

	// 多对一关系映射，多个客户可以是同一个来源
	@ManyToOne(targetEntity=Category.class)
	@JoinColumn(name="book_category", referencedColumnName="category_id")
    private Category category;

    public Book() {
    }

    public Book(Integer bookId) {
    	this.id = bookId; 
	}
    
	public Book(String name, String author, Float price, String desc, Category category) {
		super();
		this.name = name;
		this.author = author;
		this.price = price;
		this.desc = desc;
		this.category = category;
	}

	public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Category getCategory() {
        return this.category;
    }
    
    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return this.author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }

    public Float getPrice() {
        return this.price;
    }
    
    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDesc() {
        return this.desc;
    }
    
    public void setDesc(String desc) {
        this.desc = desc;
    }

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", author=" + author + ", price=" + price + ", desc=" + desc + "]";
	}


}