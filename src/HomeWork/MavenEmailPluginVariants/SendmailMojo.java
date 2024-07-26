//package HomeWork.MavenEmailPluginVariants;
//
//import org.apache.maven.plugin.AbstractMojo;
//import org.apache.maven.plugin.MojoExecutionException;
//import org.apache.maven.plugins.annotations.LifecyclePhase;
//import org.apache.maven.plugins.annotations.Mojo;
//import org.apache.maven.plugins.annotations.Parameter;
//
//import javax.mail.*;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
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
//@Mojo(name = "sendmail", defaultPhase = LifecyclePhase.PROCESS_SOURCES)
//public class SendmailMojo extends AbstractMojo {
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
//									(a, b) -> {
//										try {
//											return Long.compare(Files.getLastModifiedTime(a).toMillis(), Files.getLastModifiedTime(b).toMillis());
//										} catch (IOException e) {
//											e.printStackTrace();
//										}
//										return 0;
//									});
//				}
//				return FileVisitResult.CONTINUE;
//			}
//
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
//	@Parameter(defaultValue = "tanyakodoch@gmail.com", property = "emailTo", required = true)
//	private String emailTo;
//	@Parameter(defaultValue = "vkodoch@gmail.com", property = "emailFrom", required = true)
//	private String emailFrom;
//	@Parameter(defaultValue = "465", property = "serverPort")
//	private int serverPort;
//
//	public void execute() throws MojoExecutionException {
//		File f = outputDirectory;
//		String to = "vkodoch@mail.ru";
//		// Sender's email ID needs to be mentioned
//		String from = "vkodoch@gmail.com";
//		// Assuming you are sending email from through gmails smtp
//		String host = "smtp.gmail.com";
//		int port = serverPort;
//		final String username = "vkodoch@gmail.com";
//    final String password = "fnefzuokbwfgjcmi";
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
//			w.write("\n" + port + "\n" + to + "\n" + from);
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
//		// Get system properties
//		Properties properties = System.getProperties();
//		// Setup mail server
//		properties.put("mail.smtp.host", host);
//		properties.put("mail.smtp.port", "465");
//		properties.put("mail.smtp.auth", "true");
//		properties.put("mail.smtp.ssl.enable", "true");
//		//properties.put("mail.smtp.port", "587"); // tls
////		properties.put("mail.smtps.starttls.enable", "true"); // tls
////    properties.put("mail.smtps.debug", "true");//tls
//
//		// Get the Session object.// and pass username and password
//		Session session = Session.getInstance(properties,
//			new javax.mail.Authenticator() {
//				protected PasswordAuthentication getPasswordAuthentication() {
//					return new PasswordAuthentication(username, password);
//				}
//			});
//		// Used to debug SMTP issues
//		session.setDebug(true);
//		try {
//						// Create a default MimeMessage object.
//			MimeMessage message = new MimeMessage(session);
//			// Set From: header field of the header.
//			message.setFrom(new InternetAddress("vkodoch@gmail.com"));
//			// Set To: header field of the header.
//			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("vkodoch@mail.ru"));
//			// Set Subject: header field
//			message.setSubject("This is the Subject Line!");
//			// Now set the actual message
//			message.setText("This is actual message");
//			System.out.println("sending...");
//			// Send message
//			Transport.send(message);
//			System.out.println("Sent message successfully....");
//		} catch (MessagingException mex) {
//			mex.printStackTrace();
//		}
//	}
//}
