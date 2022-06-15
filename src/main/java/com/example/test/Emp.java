package com.example.test;

import java.util.Comparator;

class Emp implements Comparator<Emp>{
	private String name;
	private int age;

	public Emp(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public int compare(Emp o1, Emp o2) {
		return o1.age - o2.age;
	}

	@Override
	public String toString() {
		return "Emp [name=" + name + ", age=" + age + "]";
	}
}