package com.me.jackson.generics;

public class Bear {

	private String name;
	private int furColor;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFurColor() {
		return furColor;
	}

	public void setFurColor(int furColor) {
		this.furColor = furColor;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Bear [name=" + name + ", furColor=" + furColor + ", age=" + age + "]";
	}
}
