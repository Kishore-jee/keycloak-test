package com.example.util;

import java.time.LocalDate;

public class DateFrequence {

	public static LocalDate getFrequence(String recurrence) {
//		LocalDate date = LocalDate.of(2019, Month.DECEMBER, 25);
		LocalDate date = LocalDate.now();

		switch (recurrence) {
			case "Weekly": {
				date = date.plusWeeks(1);
				break;
			}
			case "Bi-Weekly": {
				date = date.plusWeeks(2);
				break;
			}
			case "Monthly": {
				date = date.plusMonths(1);
				break;
			}
			case "Quarterly": {
				date = date.plusMonths(3);
				break;
			}
			case "Half Yearly": {
				date = date.plusMonths(6);
				break;
			}
			case "Yearly": {
				date = date.plusYears(1);
				break;
			}
	
			default:
				System.err.println("Unrecognized recurrence: " + recurrence);
				break;
		}

		System.out.println("Added for " + recurrence + " gave: " + date);
		return date;
	}
}
