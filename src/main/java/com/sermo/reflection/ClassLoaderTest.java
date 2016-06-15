package com.sermo.reflection;

public class ClassLoaderTest {
	public static void main(String[] args) {
		ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
		try {
			Class cls = classLoader.loadClass("com.sermo.reflection.ClassLoaderTest");
			System.out.println("cls.name= " + cls.getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
