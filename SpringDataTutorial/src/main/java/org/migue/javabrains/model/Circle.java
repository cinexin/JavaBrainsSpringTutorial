/**
 * 
 */
package org.migue.javabrains.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author migue
 *
 */

@Entity
public class Circle {
	
	@Id
	private int id;
	
	public Circle() {
		
	}
	private String name;
	
	public Circle(int id, String name) {
		this.setId(id);
		this.setName(name);
	}
	
	@Id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
