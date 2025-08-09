package Less02_Lambda_Recursion._2_StreamAPI_Composition;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class _02DeferredAndTerminalOperations {
	
	
	public static void main(String[] args) {
		List<Book> list = new ArrayList<>(List.of(
				new Book("Капитанская дочка", "Пушкин", 545),
				new Book("Игрок", "Достоевский", 571),
				new Book("Кавказский пленник", "Лермонтов", 597),
				new Book("Мертвые души", "Гоголь", 842),
				new Book("Облако в штанах", "Маяковский", 495)
		));
		long num = list.stream().filter(x -> x.author.contains("а")).count();
		System.out.println("Отобрано книг: " + num);
	}
}
