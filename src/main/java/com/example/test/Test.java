package com.example.test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Random;
import java.util.StringJoiner;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.RandomStringUtils;

import com.example.util.DateFrequence;
import com.example.util.FrequenceEnum;
import com.example.util.OTPGenerator;

public class Test {

	public static void main(String[] args) {
		// 1
		System.out.println(100 + 100 + "sample");// 200sample
		System.out.println("sample" + 100 + 100); // sample100100
		System.out.println(100 + (100 + "sample"));// 100100sample
		System.out.println("sample" + (100 + 100)); // sample200

		// 2
		Stream<String> st = Stream.iterate("", (str) -> str + "x");
//		  System.out.println(st.limit(3).map(str -> str + "y"));// java.util.stream.ReferencePipeline$3@4f023edb
//		  System.out.println(st.collect(Collectors.toList()));// java.lang.OutOfMemoryError: Java heap space

		// 3
		Comparator<String> supplier = (s1, s2) -> s2.compareTo(s1);
		List<String> strList = Arrays.asList("A", "B", "C");
		Collections.sort(strList, supplier);
		System.out.println(strList);
		
		//4
		V.print();
		
		//5
		Random random = new Random();
		random.ints(4).forEach(i->System.out.println("ints: "+i));;
//		System.out.println(random.nextInt(10));
		
//		random.ints(9, 0, 9).forEach(System.out::println);;
		
		//6
		System.out.println("OTP: " +OTPGenerator.INSTANCE().otpWithSecureRandom(6));
//		System.out.println("OTP: " +OTPGenerator.INSTANCE().otpWithSplitableRandom(6));
//		System.out.println("OTP: " +OTPGenerator.INSTANCE().otpWithRandom(4));
//
//		System.out.println("OTP: " +OTPGenerator.INSTANCE().getOtpInDigits(6));
//		System.out.println("OTP: " +OTPGenerator.INSTANCE().getOtpInAlphabetic(6));
//		System.out.println("OTP: " +OTPGenerator.INSTANCE().getOtpInAlphanumeric(6));
//
//		System.out.println("randomNumeric: " + RandomStringUtils.randomGraph(6));
		
		//7
		List<String> strList1 = Arrays.asList("A", "B", "C", "");
		System.out.println(strList1.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList()));
		System.out.println(strList1.stream().filter(s -> !s.isEmpty()).collect(Collectors.joining(",")));
		;
		
		// 8
		List<Integer> intList1 = Arrays.asList(2, 3, 4, 5, 6);
		System.out.println(intList1.stream().mapToInt(i->i).summaryStatistics().getSum());
		;
		
		// 9 nxt occurance of week day.
		LocalDate today = LocalDate.now();
		LocalDate nxtWednesday = today.with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));
		System.out.println(nxtWednesday);
		
		// 10
		Supplier<String> supplier1 = () -> "Java";
		System.out.println(supplier1.get());
		
		Consumer<Integer> intCon = a -> {System.out.println(a); };
		intCon.accept(10);
		
		// 11
		V1 fun =(a, b) -> {System.out.println(a+b); return a+b;};
		Consumer<String> con = s ->  System.out.println(s);
		con.accept("asdfasdf");
		fun.add(1, 0);
		
		Supplier<Integer> sup = () ->  1+2;
		System.out.println(sup.get());
		
		// 12 :: nxt month 2nd week friday 
		LocalDate tday = LocalDate.now();
		LocalDateTime tddayDtTime = LocalDateTime.now();
		LocalDate nxtMonFr= tday.plusMonths(1).with(TemporalAdjusters.dayOfWeekInMonth(2, DayOfWeek.FRIDAY));
//		LocalDate nxtMonFr= tday.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY)).with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
		System.out.println("nxtMonFr: "+nxtMonFr);
		
		// this month 2 week friday
		LocalDate thisMonFri = tday.with(TemporalAdjusters.dayOfWeekInMonth(2, DayOfWeek.FRIDAY));
		System.out.println("thisMonFri: "+thisMonFri);

		LocalDateTime thisMonFriTime = tddayDtTime.with(TemporalAdjusters.dayOfWeekInMonth(2, DayOfWeek.FRIDAY));
		System.out.println("thisMonFriTime: "+thisMonFriTime);
		
		//13
		System.out.println(DateFrequence.getFrequence(FrequenceEnum.Bi_Weekly.value()));
		
		//14
		Runtime runtime = Runtime.getRuntime();
//		runtime.gc();
		System.out.println("processors available: "+ runtime.availableProcessors());
		
		// 15
//		StringJoiner 
		
		//16
		BiFunction<Integer, Integer, String> biFun = (a, b) ->  ""+(a+b);
		System.out.println(biFun.apply(10, 11));
		
		BinaryOperator<Integer> biOpe = (a, b) -> a+b;
		System.out.println(biOpe.apply(1, 2));
		
		UnaryOperator<Integer> unaryOpe = a -> a;
		System.out.println(unaryOpe.apply(4));
		
		// 17 highest number that exists on a list
		List<Integer> intList = Arrays.asList(1,2,3,4,5,6);
		int maxInt = intList.stream().mapToInt(i -> i).summaryStatistics().getMax();
		System.out.println("maxInt: "+maxInt);
		
		//18
		Stream.of(1,2,3,4).map(i -> {
			System.out.println("I: "+ i);
			return i;
		});

		Stream.of(1,2,3,4).mapToInt(i -> {
			System.out.println(" II:"+ i);
			return i;
		}).sum();
		
		// 19
		OptionalDouble optDouble =  
		Arrays.asList(10, 2, 200, 400, 5)
		.stream().mapToInt(n -> n*n).filter(n -> n>100).average();
		if (optDouble.isPresent())System.out.println("optDouble: "+optDouble.getAsDouble());
		
		// 20
		int arr[] = {1,2,3,6,7,9,8,4};
		Arrays.parallelSort(arr);
		
		Arrays.stream(arr).forEach(System.out::println);
		System.out.println("arr: "+arr);
		
		//21
		Random r1 = new Random();
		r1.ints(5, 100).limit(5).sorted().forEach(System.out::println);// asc
		r1.ints(5, 100).limit(5).boxed().sorted(Collections.reverseOrder()).forEach(System.out::println);// desc
		
		// 22
		System.out.println();
		Stream.iterate(1, num-> num+1).filter(num -> num%2 == 0).limit(5).forEachOrdered(System.out::println);
	}
	
	interface V{
		static void print() {
			System.out.println("This is printing...");
		}
	}

	interface V1{
		int add(int a, int b);
		static void print() {
			System.out.println("This is printing...");
		}
	}
}
