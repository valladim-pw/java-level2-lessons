package Less02_Lambda_Recursion._1_Test;

import java.util.ArrayList;
import java.util.List;

class Person {
	private String name;
	private int age;
	
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public String toString() {
		return name + " " + age;
	}
	static void sortAndPrint(List<Person> list) {
		list.sort((a, b) -> Integer.compare(a.age, b.age));
		list.forEach(System.out::println);
	}
	
	public static void main(String[] args) {
		List<Person> list = new ArrayList<>(List.of(
				new Person("Ivan", 122),
				new Person("Petr", 33),
				new Person("Andrew", 52)
		));
		sortAndPrint(list);
	}
}
