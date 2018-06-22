package de.hska.iwi.vs.lab.composite.product.entity;

public class Category {
	private int id;
	private String name;

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

	@Override
	public String toString() {
		return "{" +
				"\"id\":\"" + this.id + "\"," +
				"\"name\":\"" + this.name + "\"" +
				"}";
	}
}