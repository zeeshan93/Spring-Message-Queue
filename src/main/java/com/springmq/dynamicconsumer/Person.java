package com.springmq.dynamicconsumer;

import java.io.Serializable;

public class Person implements Serializable{
	private String name;
	private Integer age;
	public Person(String name, Integer age) {
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public Integer getAge() {
		return age;
	}
	public String toString() {
		return "Person: name(" + name + "), age(" + age + ")";
	}
}
