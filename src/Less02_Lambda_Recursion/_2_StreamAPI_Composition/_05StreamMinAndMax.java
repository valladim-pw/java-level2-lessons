package Less02_Lambda_Recursion._2_StreamAPI_Composition;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;


public class _05StreamMinAndMax {
	
	
	public static void main(String[] args) {
		List<Book> list = new ArrayList<>(List.of(
				new Book("Капитанская дочка", "Пушкин", 545),
				new Book("Игрок", "Достоевский", 571),
				new Book("Кавказский пленник", "Лермонтов", 597),
				new Book("Мертвые души", "Гоголь", 842),
				new Book("Облако в штанах", "Маяковский", 495)
		));
		Book min = list.stream().min((x, y) -> Double.compare(x.price, y.price)).get();
		Book max = list.stream().max(Comparator.comparingDouble(x -> x.price)).get();
		
		System.out.println(min);
		System.out.println(max);
	}
}
