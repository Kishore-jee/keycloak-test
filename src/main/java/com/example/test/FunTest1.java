package com.example.test;

@FunctionalInterface
 interface FunTest1 extends FunTest{

	int addB();
	
//	default int add() {
//		return 1+3;
//	}
}

@FunctionalInterface
 interface Function2<T, U, V> {
public V apply(T t, U u);
default void count() {
    // increment counter
}
}