package Less03_Generics._1_GenericClasses;

import java.io.Serializable;
import java.util.Collections;

//После extends можем добавить один!!! класс и сколько угодно интерфейсов
public class _4GenericComparable<T extends /*People & */Comparable /*& Serializable*/> {
	public T max(T...items) {
		T tmp = items[0];
		for(int i = 1; i < items.length; i++) {
			if(tmp.compareTo(items[i]) == 1)
				tmp = items[i];
		}
		return tmp;
	}
	
	public static void main(String[] args) {
		People p1 = new People("Maks", 49);
		People p2 = new People("Tim", 55);
		People p3 = new People("Alex", 65);
		People p4 = new People("Oleg", 45);
		_4GenericComparable<People> pl = new _4GenericComparable<People>();
		System.out.println(pl.max(p1, p2, p3));
		Car c1= new Car("Audi", 20000.0);
		Car c2 = new Car("BMW", 30000.0);
		Car c3 = new Car("Mercedes", 35000.0);
		Car c4 = new Car("Volkswagen", 15000.0);
		_4GenericComparable<Car> pl2 = new _4GenericComparable<Car>();
		System.out.println(pl2.max(c1, c2, c3, c4));
	}
}
class People implements Comparable<People> {
	private int age;
	private String name;

	public People(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "age=" + age +	", name=" + name;
	}

	@Override
	public int compareTo(People p) {
		int compare = Integer.compare(p.age, this.age);
		return compare;
	}
}

class Car implements Comparable<Car> {
	private double price;
	private String model;
	
	public Car(String model, double price) {
		this.model = model;
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "model=" + model + ", price=" + price;
	}
	
	@Override
	public int compareTo(Car c) {
		return Double.compare(c.price, this.price);
	}
}

