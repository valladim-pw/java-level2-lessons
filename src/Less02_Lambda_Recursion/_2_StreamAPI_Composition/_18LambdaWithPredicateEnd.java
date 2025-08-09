package Less02_Lambda_Recursion._2_StreamAPI_Composition;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class _18LambdaWithPredicateEnd {
	
	public static void main(String[] args) {
		List<Book> list = new ArrayList<>(List.of(
				new Book("Капитанская дочка", "Пушкин", 545),
				new Book("Игрок", "Достоевский", 571),
				new Book("Кавказский пленник", "Лермонтов", 597),
				new Book("Мертвые души", "Гоголь", 842),
				new Book("Облако в штанах", "Маяковский", 495)
		));
		Predicate<Book> greater500 = b -> b.price > 500;
		Predicate<Book> below600 = b -> b.price < 600;
		
		Predicate<Book> composed = below600.and(greater500);
		list.stream().filter(composed).collect(Collectors.toList()).forEach(System.out::println);
	}
}
