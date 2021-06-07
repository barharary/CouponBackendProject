package com.bh.CouponProject3.clr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestNoSpring {

	public static void main(String[] args) throws JsonProcessingException {
// cannot use Object[] becouse primitive

		Employee employee = new Employee("David",
				new ArrayList<>(Arrays.asList(new Book("The Dove", 282), new Book("Karpada", 411))));

		{
		}
		String result = new ObjectMapper().writeValueAsString(employee);
		System.out.println("Pogo: " + employee);
		System.out.println("Json: " + result);
	}

}

class Person {
	private String name;

	public Person(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "name = " + name;
	}

}

@JsonPropertyOrder( )
class Employee extends Person {
	private List<Book> Books;

	public Employee(String name, List<Book> books) {
		super(name);
		this.Books = books;
	}

	@JsonGetter()
	public List<Book> getBookName() {
		return Books;
	}

	public void setBookName(List<Book> books) {
		Books = books;
	}

	@Override
	public String toString() {
		return super.toString() + ", BookName=" + Books + "]";
	}

}

class Book {

	private String name;
	private int pages;

	public Book(String name, int pages) {
		this.name = name;
		this.pages = pages;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	@Override
	public String toString() {
		return "Book [name=" + name + ", pages=" + pages + "]";
	}

}
