package de.hska.iwi.vs.lab.composite.user.entity;


public class Role implements java.io.Serializable {

	private int id;
	private String typ;
	private int level;
	public Role() {
	}

	public Role(String typ, int level) {
		this.typ = typ;
		this.level = level;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTyp() {
		return this.typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
