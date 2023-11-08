package Less03_Generics._1_GenericClasses;

import java.util.List;

public class _6WildcardGenerics {
	
	public static void print(List<String> items) {
		System.out.println(items);
	}
	
	public static void wildPrint(List<?> items) {
		System.out.println(items);
	}
	
	public static void limitPrint(List<? extends Comparable> items) {
		System.out.println(items);
	}
	
	public static void main(String[] args) {
		List<String> strings = List.of("A", "B", "C", "D");
		List<Integer> ints = List.of(4, 3, 7, 11);
		print(strings);
	//	print(ints); ошибка компиляции
		
		Town t1 = new Town("Kaluga", 350000);
		Town t2 = new Town("Moscow", 15000000);
		Town t3 = new Town("S-Petersburg", 5000000);
		List<Town> towns = List.of(t1, t2, t3);
		Specialist s1 = new Specialist("Java Developer", 150000.0);
		Specialist s2 = new Specialist("PHP Developer", 100000.0);
		Specialist s3 = new Specialist("Designer", 80000.0);
		List<Specialist> specs = List.of(s1, s2, s3);
		wildPrint(strings);
		wildPrint(ints);
		wildPrint(towns);
		wildPrint(specs);
		System.out.println("--------------------------------------");
		limitPrint(strings);
		limitPrint(ints);
		limitPrint(towns);
		//limitPrint(specs); ошибка компиляции, класс Specialist не Comparable
	}
}

class Town implements Comparable<Town> {
	private int inhabitants;
	private String name;
	
	public Town(String name, int inhabitants) {
		this.name = name;
		this.inhabitants = inhabitants;
	}
	
	@Override
	public String toString() {
		return "name=" + name + ", inhabitants=" + inhabitants;
	}
	
	@Override
	public int compareTo(Town t) {
		return Double.compare(t.inhabitants, this.inhabitants);
	}
}

class Specialist {
	private double salary;
	private String speciality;
	
	public Specialist(String speciality, double salary) {
		this.speciality = speciality;
		this.salary = salary;
	}
	
	@Override
	public String toString() {
		return "speciality=" + speciality + ", salary=" + salary;
	}
	
}