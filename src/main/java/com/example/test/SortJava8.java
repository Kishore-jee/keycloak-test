package com.example.test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortJava8 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Comparator<Emp> empComByAge = (Emp e1, Emp e2) -> e1.compare(e1, e2);
		Comparator<Emp> empComByName = (Emp e1, Emp e2) -> e2.getName().compareTo(e1.getName());
		List<Emp> empList = Arrays.asList(new Emp("A", 2), new Emp("B", 1), new Emp("C", 3));
		Collections.sort(empList, empComByAge);
		System.out.println(empList);
	}
}
