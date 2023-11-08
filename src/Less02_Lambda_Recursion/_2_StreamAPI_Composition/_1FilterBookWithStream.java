package Less02_Lambda_Recursion._2_StreamAPI_Composition;

import java.util.*;
import java.util.stream.Collectors;


public class _1FilterBookWithStream {
	
	
	public static void main(String[] args) {
		List<Book> list = new ArrayList<>(List.of(
				new Book("Капитанская дочка", "Пушкин", 545),
				new Book("Игрок", "Достоевский", 571),
				new Book("Кавказский пленник", "Лермонтов", 597),
				new Book("Мертвые души", "Гоголь", 842),
				new Book("Облако в штанах", "Маяковский", 495)
		));
		// традиционное программирование
		List<Book> filtered = new ArrayList<>();
		for(Book elem : list) {
			if(elem.name.contains("Ка")) {
				filtered.add(elem);
			}
		}
		filtered.forEach(System.out::println);
		System.out.println("-------------------------------");
		// обработка используя Stream API
		List<Book> filtered2 =
						list.stream().filter(x -> x.name.contains("о")).collect(Collectors.toList());
		filtered2.forEach(System.out::println);
	}
}
