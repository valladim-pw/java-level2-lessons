package Less03_Generics._1_GenericClasses;

public class MyComparableObject<T extends Comparable> {
	public T max(T...items) {
		T tmp = items[0];
		for(int i = 1; i < items.length; i++) {
			if(tmp.compareTo(items[i]) == 1)
				tmp = items[i];
		}
		return tmp;
	}
	
	public static void main(String[] args) {
		MyComparableObject<People> pl = new MyComparableObject<People>();
		
		People p1 = new People("Maks", 49);
		People p2 = new People("Tim", 55);
		People p3 = new People("Alex", 65);
		System.out.println(pl.max(p1, p2, p3));
		
		MyComparableObject<Car> pl2 = new MyComparableObject<Car>();
		
		Car c1= new Car("Audi", 20000.0);
		Car c2 = new Car("BMW", 30000.0);
		Car c3 = new Car("Mercedes", 35000.0);
		Car c4 = new Car("Volkswagen", 15000.0);
		System.out.println(pl2.max(c1, c2, c3, c4));
	}
}
