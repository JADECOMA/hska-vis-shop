package de.hska.iwi.vs.lab.composite.product.entity;

public class Category implements java.io.Serializable {
	private int id;
	private String name;

	public Category(String name) {
		this.name = name;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
