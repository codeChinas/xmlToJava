package com.sermo.reflection.model;


import java.util.List;

import com.sermo.reflection.annotation.MyAnnotation;

@MyAnnotation(name="a", value="b")
public class Person {
	
	public int age;
	
	private String name;
	
	public List<String> stringList;
	
	@MyAnnotation(name="someName", value="Hello World")
	public String myField = null;
	
	public Person() {
	}
	
	public Person(int age) {
		this.age = age;
	}

	public Person(String name) {
		this.name = name;
	}

	public Person(int age, String name) {
		this.age = age;
		this.name = name;
	}

	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@MyAnnotation(name="ss", value = "11")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static void doSomething(@MyAnnotation(name="aname", value="avalue") String pam){
		System.out.println("11");
	}
	public List<String> getStringList() {
		return stringList;
	}

	public void setStringList(List<String> stringList) {
		this.stringList = stringList;
	}

	@Override
	public String toString() {
		return "Person [age=" + age + ", name=" + name + "]";
	}
}
