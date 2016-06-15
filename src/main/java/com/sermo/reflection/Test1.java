package com.sermo.reflection;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import com.sermo.reflection.annotation.MyAnnotation;
import com.sermo.reflection.model.Person;
import com.thoughtworks.xstream.core.util.Base64Encoder;

public class Test1 {
	public static void main(String[] args) {
		/*Method[] methods = Object.class.getMethods();
		for (Method method : methods) {
			System.out.println("method = " + method.getName());
		}*/
		
		/*Class cls = Object.class;
		System.out.println(cls.getName()); // java.lang.Object
		System.out.println(cls.getSimpleName()); // Object
		int modifiers = cls.getModifiers();
		System.out.println(Modifier.isPublic(modifiers));
		System.out.println(Modifier.isAbstract(modifiers));*/
//		Class cls = Person.class;
		try {
			/*Constructor constructor = cls.getConstructor(int.class, String.class);
			Person person = (Person) constructor.newInstance(1,"sermo");
			System.out.println(person);
			Field field = cls.getField("age");
			Class type = field.getType();
			System.out.println(type);
			Object value = field.get(person);
			System.out.println(value);
			field.set(person, 2);
			System.out.println(person);*/
			Person person = new Person(1, "sermo");
			Method method = person.getClass().getMethod("getName", null);
			Object return_value = method.invoke(person, null);
			System.out.println(return_value);
		}catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	@Test
	public void test2() throws Exception{
		Person person = new Person(2, "li");
		Field field = person.getClass().getDeclaredField("name");
		field.setAccessible(true);
		String name = (String) field.get(person);
		Assert.assertEquals(name, "li");
	}
	@Test
	public void test3() throws Exception{
		Class cls = Person.class;
		Annotation annotation = cls.getAnnotation(MyAnnotation.class);
		if (annotation instanceof MyAnnotation) {
			MyAnnotation myAnnotation = (MyAnnotation) annotation;
			System.out.println("name= " + myAnnotation.name());
			System.out.println("value= " + myAnnotation.value());
		}
	}
	
	@Test
	public void test4() throws Exception{
		Method method = Person.class.getMethod("getName", null);
		Annotation annotation = method.getAnnotation(MyAnnotation.class);
		if (annotation instanceof MyAnnotation) {
			MyAnnotation annotation2 = (MyAnnotation) annotation;
			System.out.println("name= " + annotation2.name());
			System.out.println("value= " + annotation2.value());
		}
	}
	
	@Test
	public void test5() throws Exception{
		Method method = Person.class.getMethod("doSomething", String.class);
		Annotation[][] parameterAnnotations  = method.getParameterAnnotations();
		Class[] parameterTypes  = method.getParameterTypes();
		int i = 0;
		for (Annotation[] annotations : parameterAnnotations) {
			Class parameterType = parameterTypes[i++];
			for (Annotation annotation : annotations) {
				if (annotation instanceof MyAnnotation) {
					MyAnnotation myAnnotation = (MyAnnotation)annotation;
					System.out.println("name= " + myAnnotation.name());
					System.out.println("value= " + myAnnotation.value());
				}
			}
		}
	}
	
	@Test
	public void test6() throws Exception{
		Field field = Person.class.getField("myField");
		Annotation annotation = field.getAnnotation(MyAnnotation.class);
		if (annotation instanceof MyAnnotation) {
			MyAnnotation myAnnotation = (MyAnnotation)annotation;
			System.out.println("name= " + myAnnotation.name());
			System.out.println("value= " + myAnnotation.value());
		}
	}
	
	@Test
	// 泛型方法返回类型
	public void test7() throws Exception{
		Method method = Person.class.getMethod("getStringList", null);
		Type returnType = method.getGenericReturnType();
		if (returnType instanceof ParameterizedType) {
			ParameterizedType type = (ParameterizedType)returnType;
			Type[] typeArguments = type.getActualTypeArguments();
			for (Type typeArgument : typeArguments) {
				Class typeClass = (Class)typeArgument;
				System.out.println(typeClass);
			}
		}
	}
	
	@Test
	// 泛型方法参数类型
	public void test8() throws Exception{
		Method method = Person.class.getMethod("setStringList", List.class);
		Type[] genericParameterTypes = method.getGenericParameterTypes();
		for (Type genericParameterType : genericParameterTypes) {
			if (genericParameterType instanceof ParameterizedType) {
				ParameterizedType aType = (ParameterizedType) genericParameterType;
				Type[] parameterArgTypes = aType.getActualTypeArguments();
				for (Type parameterArgType : parameterArgTypes) {
					Class parameterArgClass = (Class)parameterArgType;
					System.out.println(parameterArgClass);
				}
			}
		}
	}
	
	@Test
	// 泛型变量类型
	public void test9() throws Exception{
		Field field = Person.class.getField("stringList");
		Type genericFieldType = field.getGenericType();
		if (genericFieldType instanceof ParameterizedType) {
			ParameterizedType type = (ParameterizedType)genericFieldType;
			Type[] fieldArgTypes = type.getActualTypeArguments();
			for (Type fieldArgType  : fieldArgTypes) {
				Class fieldArgClass = (Class)fieldArgType;
				System.out.println(fieldArgClass);
			}
		}
	}
	
	@Test
	public void tt1(){
		int[] array = (int[]) Array.newInstance(int.class, 3);
		Array.set(array, 0, 123);
		Array.set(array, 1, 456);
		Array.set(array, 2, 789);
		for (int i : array) {
			System.out.println(i);
		}
	}
	public static String getMD5(String pwd) throws NoSuchAlgorithmException {
	  MessageDigest md = MessageDigest.getInstance("SHA");//SHA 或者 MD5
	  Base64Encoder  base = new Base64Encoder();
	  String pwdAfter = base.encode(md.digest(pwd.getBytes()));
	  return pwdAfter;
	}
	
	@Test
	public void tt2() throws Exception{
		System.out.println(getMD5("com.sermo.b"));
		System.out.println(getMD5("com.sermo.a"));
		String str = null;
		System.out.println(StringUtils.isBlank(str));
	}
}
