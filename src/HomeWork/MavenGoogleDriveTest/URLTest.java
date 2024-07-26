package HomeWork.MavenGoogleDriveTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class URLTest {
	public static void main(String[] args) throws IOException {
//		Path dir = Paths.get("");
//		System.out.println(dir.toString());
//
		String html = "<!DOCTYPE html>\n" +
						              "<html lang=\"en\">\n" +
						              "<head>\n" +
						              "\t<meta charset=\"UTF-8\">\n" +
						              "\t<title>Title</title>\n" +
						              "</head>\n" +
						              "<body><h1>Redirect</h1>\n" +
						              "</body>\n" +
						              "</html>";
		//try {;
		File file = new File("redirect.html");
		file.createNewFile();
		
		FileOutputStream outputStream = new FileOutputStream(file);
		byte[] buffer = html.getBytes();
		outputStream.write(buffer);
		outputStream.close();
		Path dir = Paths.get(file.toURI());
		System.out.println(dir);
		int count = dir.getNameCount();
		System.out.println(count);
		System.out.println(dir.subpath(count - 2, count));
		
		//} catch (IOException e) {
			//e.printStackTrace();
		//}
	}
}
