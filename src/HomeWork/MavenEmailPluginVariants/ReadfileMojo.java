//package MavenEmailPlugin;

//import org.apache.maven.plugin.AbstractMojo;
//import org.apache.maven.plugin.MojoExecutionException;
//
//import org.apache.maven.plugins.annotations.LifecyclePhase;
//import org.apache.maven.plugins.annotations.Mojo;
//import org.apache.maven.plugins.annotations.Parameter;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.file.*;
//import java.nio.file.attribute.BasicFileAttributes;
//import java.util.*;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipFile;
//
//@Mojo(name = "readfile", defaultPhase = LifecyclePhase.PROCESS_SOURCES)
//
//public class ReadfileMojo extends AbstractMojo {
//
//	static String createMailReport(String directoryName) throws IOException {
//		final List<Path> jars = new LinkedList<>();
//		String mf = "";
//		String report = "";
//		Date date = new Date();
//		String caption = "REPORT " + date.toString() + "\n";
//		String underline = "---------------------------------" + "\n";
//		String header = caption + underline;
//		String success = "Archive file successfully created \n\n";
//		String failure = "Archive file has not been created";
//		Set<FileVisitOption> set = new HashSet<>();
//		final PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/*.jar");
//		Files.walkFileTree(Paths.get(directoryName), set, 1, new SimpleFileVisitor<Path>() {
//			@Override
//			public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
//				if (pathMatcher.matches(path) && !Files.isDirectory(path)) {
//					jars.add(path);
//					jars.sort(
//					(a, b) -> {
//						try {
//							return Long.compare(Files.getLastModifiedTime(a).toMillis(), Files.getLastModifiedTime(b).toMillis());
//						} catch (IOException e) {
//							e.printStackTrace();
//						}
//						return 0;
//					});
//				}
//				return FileVisitResult.CONTINUE;
//			}
//			@Override
//			public FileVisitResult visitFileFailed(Path file, IOException e) {
//				return FileVisitResult.CONTINUE;
//			}
//		});
//
//		if (!jars.isEmpty()) {
//			String jarFilePath = ((LinkedList<Path>) jars).getLast().toString();
//
//			try (ZipFile zipFile = new ZipFile(jarFilePath)) {
//				Enumeration<? extends ZipEntry> entries = zipFile.entries();
//				while (entries.hasMoreElements()) {
//					ZipEntry entry = entries.nextElement();
//					if (!entry.isDirectory() && entry.getName().toLowerCase().contains("manifest")) {
//						try (InputStream inputStream = zipFile.getInputStream(entry);
//						     Scanner scanner = new Scanner(inputStream)) {
//
//							while (scanner.hasNextLine()) {
//								String line = scanner.nextLine();
//								mf += line + "\n";
//							}
//						}
//					}
//				}
//			}
//			report = header + success + mf;
//		} else {
//			report = header + failure;
//		}
//		return report;
//	}
//
//	@Parameter(defaultValue = "${project.build.directory}", property = "outputDir", required = true)
//	private File outputDirectory;
//
//	public void execute() throws MojoExecutionException {
//		File f = outputDirectory;
//
//		if (!f.exists()) {
//			f.mkdirs();
//		}
//
//		File file = new File(f, "sendMail.txt");
//
//		FileWriter w = null;
//		try {
//			String report = createMailReport(outputDirectory.getPath());
//			w = new FileWriter(file);
//
//			w.write(report);
//		} catch (IOException e) {
//			throw new MojoExecutionException("Error creating file " + file, e);
//		} finally {
//			if (w != null) {
//				try {
//					w.close();
//				} catch (IOException e) {
//					// ignore
//				}
//			}
//		}
//	}
//}
