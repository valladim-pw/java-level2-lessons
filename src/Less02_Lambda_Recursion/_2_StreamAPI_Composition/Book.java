package Less02_Lambda_Recursion._2_StreamAPI_Composition;

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
	
}
