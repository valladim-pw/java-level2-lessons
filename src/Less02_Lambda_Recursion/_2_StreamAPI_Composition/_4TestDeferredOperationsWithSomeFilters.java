package Less02_Lambda_Recursion._2_StreamAPI_Composition;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;


public class _4TestDeferredOperationsWithSomeFilters {
	
	
	public static void main(String[] args) {
		List<Book> list = new ArrayList<>(List.of(
				new Book("Капитанская дочка", "Пушкин", 545),
				new Book("Игрок", "Достоевский", 571),
				new Book("Кавказский пленник", "Лермонтов", 597),
				new Book("Мертвые души", "Гоголь", 842),
				new Book("Облако в штанах", "Маяковский", 495)
		));
		Predicate<Book> preLambda = x -> {
			System.out.println(x);
			return x.author.contains("о");
		};
		System.out.println("До stream().filter");
		Stream<Book> test = list.stream().filter(preLambda).filter(x -> x.price < 590);
		System.out.println("После stream().filter");
		
		long num = test.count();
		System.out.println("Отобрано книг: " + num);
	}
}
