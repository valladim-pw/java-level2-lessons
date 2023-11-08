package Less02_Lambda_Recursion._1_IntroToLambdaExpressions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Book {
	String name;
	String author;
	double price;
	
	public Book(String name, String author, double price) {
		this.name = name;
		this.author = author;
		this.price = price;
	}
	
	@Override
	public String toString() {
		return 	author + ", " + name + ", " + price;
	}
	
	public static void main(String[] args) {
		List<Book> list = new ArrayList<>(List.of(
				new Book("Капитанская дочка", "Пушкин", 545),
				new Book("Игрок", "Достоевский", 571),
				new Book("Кавказский пленник", "Лермонтов", 597),
				new Book("Мертвые души", "Гоголь", 842),
				new Book("Облако в штанах", "Маяковский", 495)
		));
		System.out.println("\nСортировка по имени автора\n ----------------------------");
		list.sort((a, b) -> a.author.compareTo(b.author));
		list.forEach(element -> System.out.println(element));
		System.out.println("\nСортировка по названию книги\n ----------------------------");
		list.sort(Comparator.comparing(a -> a.name));
		list.forEach(element -> System.out.println(element));
		System.out.println("\nСортировка по цене книги\n ----------------------------");
		list.sort((a, b) -> Double.compare(a.price, b.price));
		list.forEach(element -> System.out.println(element));
		System.out.println("\nПример с \"эффективно финальной переменной\"\n ----------------------------");
		list.sort(Comparator.comparing(a -> a.name));
		String msg = "Книга: ";
		list.forEach(element -> System.out.println(msg + element));
		System.out.println("\nОшибка при подстановке переменной\n ----------------------------");
		list.sort(Comparator.comparing(a -> a.name));
		String msg1 = "Книга: ";
		//msg1 += " ";
		list.forEach(element -> System.out.println(msg1 + element));
	}
}
