package HomeWork.MavenEmailPluginVariants;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Test {
	static void createSendMailFile(String directoryName) throws IOException {
		List<Path> jars = new LinkedList<>();
		String mf = "";
		Date date = new Date();
		String caption = "REPORT " + date.toString() + "\n";
		String underline = "---------------------------------" + "\n";
		String header = caption + underline;
		String success = "Archive file successfully created \n\n";
		String failure = "Archive file has not been created";
		String sendFile = directoryName + "sendMail.txt";
		PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/*.jar");
		Files.walkFileTree(Paths.get(directoryName), Collections.emptySet(), 1, new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
				if (pathMatcher.matches(path) && !Files.isDirectory(path)) {
					jars.add(path);
					jars.sort(
					(a, b) -> {
						try {
							return Long.compare(Files.getLastModifiedTime(a).toMillis(), Files.getLastModifiedTime(b).toMillis());
						} catch (IOException e) {
							e.printStackTrace();
						}
						return 0;
					});
				}
				return FileVisitResult.CONTINUE;
			}
			
			@Override
			public FileVisitResult visitFileFailed(Path file, IOException e) {
				return FileVisitResult.CONTINUE;
			}
		});
		if (!Files.exists(Paths.get(sendFile)))
			Files.createFile(Paths.get(sendFile));
		if (!jars.isEmpty()) {
			String jarFilePath = ((LinkedList<Path>) jars).getLast().toString();
			try (ZipFile zipFile = new ZipFile(jarFilePath)) {
				Enumeration<? extends ZipEntry> entries = zipFile.entries();
				while (entries.hasMoreElements()) {
					ZipEntry entry = entries.nextElement();
					if (!entry.isDirectory() && entry.getName().toLowerCase().contains("manifest")) {
						try (InputStream inputStream = zipFile.getInputStream(entry);
						     Scanner scanner = new Scanner(inputStream)) {
							
							while (scanner.hasNextLine()) {
								String line = scanner.nextLine();
								mf += line + "\n";
							}
						}
					}
				}
			}
			Files.write(Paths.get(sendFile), Collections.singleton(header + success + mf));
		} else {
			Files.write(Paths.get(sendFile), Collections.singleton(header + failure));
		}
	}
	
	public static void main(String[] args) throws IOException {
		String dir = "d:\\JAVAProgwards\\2.00_JavaAdvance0421A_lessons\\src\\HomeWork\\MavenCalculator\\target\\";
		
		File f = new File(dir);
		String directory = f.getPath();
		//createSendMailFile(dir);
		System.out.println(directory);
		Path path = Paths.get("");
		Path d = path.toAbsolutePath();
		System.out.println(d);
		//ZipFile zipFile = new ZipFile(jarFilePath)

//		String s1 = path1.getFileName().toString();
//		int i1 = path1.getNameCount();
//		String s2  = path1.getName(i1 - 2).toString();
//		System.out.println(s1);
//		System.out.println(i1);
//		System.out.println(s2);
	}
}
