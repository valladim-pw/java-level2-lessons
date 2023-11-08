package Less02_Lambda_Recursion._1_IntroToLambdaExpressions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

public class _10OperatorDoubleColon {
	static boolean isEven(Integer i1) {
		return i1 % 2 == 0;
	}
	public static void main(String[] args) {
		Predicate<Integer> predicate = _10OperatorDoubleColon::isEven;
		//аналогично лямбда-выражению
		//Predicate<Integer> predicate = x -> isEven(x);
		System.out.println(predicate.test(1000));
		
		Consumer<Book> b = System.out::println;
		b.accept(new Book("Чемодан", "Довлатов", 535));
		
		List<Book> list = new ArrayList<>(List.of(
						new Book("Капитанская дочка", "Пушкин", 545),
						new Book("Игрок", "Достоевский", 571),
						new Book("Кавказский пленник", "Лермонтов", 597),
						new Book("Мертвые души", "Гоголь", 842),
						new Book("Облако в штанах", "Маяковский", 495)
		));
		list.sort(Comparator.comparing(a -> a.author));
		//list.forEach(element -> System.out.println(element));
		list.forEach(System.out::println);
	}
}
