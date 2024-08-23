package Less07_TypeOfLinks_GarbageCollector._2_TypeOfLinks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

public class E_WeakRefsSimple {
	public static void main(String[] args) throws IOException {
		WeakHashMap<String, String> files = new WeakHashMap<>();
		
		List<String> fileNames = new ArrayList<>();
		
		for (int i = 1; i <= 3; i++) fileNames.add("file" + i);
		
		for (String fileName : fileNames)
			files.put(fileName, Files.readString(Path.of(fileName)));
		
		for (int i = 1; i <= 3; i++)
			System.out.println(files.get("file" + i));
		
		fileNames.remove(2);
		fileNames.remove(0);
		
		System.gc();
		
		System.out.println("После сборки мусора:");
		for (int i = 1; i <= 3; i++)
			System.out.println(files.get("file" + i));
	}
}
