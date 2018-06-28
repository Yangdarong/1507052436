package com.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 分类信息的实体类
 * @author 杨鑫荣
 *
 */
@Entity
@Table(name="category_info")
public class Category  implements java.io.Serializable {

	@Id
	@Column(name="category_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="category_name")
    private String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }
    // Property accessors

    public Category(int id) {
		// TODO Auto-generated constructor stub
    	this.id = id;
	}

	public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}


}