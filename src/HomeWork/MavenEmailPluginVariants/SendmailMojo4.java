//package HomeWork.MavenEmailPluginVariants;
//
//import org.apache.maven.plugin.AbstractMojo;
//import org.apache.maven.plugin.MojoExecutionException;
//import org.apache.maven.plugins.annotations.LifecyclePhase;
//import org.apache.maven.plugins.annotations.Mojo;
//import org.apache.maven.plugins.annotations.Parameter;
//
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;
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
//public class SendmailMojo4 extends AbstractMojo {
//
//	static String getJarFile(String directoryName) throws IOException {
//		final List<Path> jars = new LinkedList<>();
//		final PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/*.jar");
//		Set<FileVisitOption> set = new HashSet<>();
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
//		return ((LinkedList<Path>) jars).getLast().toString();
//	}
//
//	static String createMailReport(String jarFilePath) throws IOException {
//		String mf = "";
//		String report = "";
//		Date date = new Date();
//		String caption = "REPORT " + date.toString() + "\n";
//		String underline = "---------------------------------" + "\n";
//		String header = caption + underline;
//		String success = "Archive file successfully created \n\n";
//		String failure = "Archive file has not been created";
//
//		if (!jarFilePath.isEmpty()) {
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
//	@Parameter(defaultValue = "vkodoch@mail.ru", property = "emailTo", required = true)
//	private String emailTo;
//	@Parameter(defaultValue = "vkodoch@gmail.com", property = "emailFrom", required = true)
//	private String emailFrom;
//	@Parameter(defaultValue = "smtp.gmail.com", property = "emailHost", required = true)
//	private String emailHost;
//	@Parameter(defaultValue = "465", property = "emailPort", required = true)
//	private int emailPort;
//	@Parameter(defaultValue = "true", property = "emailAuth", required = true)
//	private boolean emailAuth;
//	@Parameter(defaultValue = "true", property = "emailSSL", required = true)
//	private boolean emailSSL;
//
//	public void execute() throws MojoExecutionException {
//		File target = outputDirectory;
//		String to = emailTo;
//		// Sender's email ID needs to be mentioned
//		String from = emailFrom;
//		// Assuming you are sending email from through gmails smtp
//		String host = emailHost;
//		int port = emailPort;
//		boolean auth = emailAuth;
//		boolean ssl = emailSSL;
//
//		final String username = "vkodoch@gmail.com";
//		final String password = "fnefzuokbwfgjcmi";
//
//
//		if (!target.exists()) {
//			target.mkdirs();
//		}
//
//		File file = new File(target, "sendMail.txt");
//
//		FileWriter w = null;
//		try {
//			w = new FileWriter(file);
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
//		properties.put("mail.smtp.port", port);
//		properties.put("mail.smtp.auth", auth);
//		properties.put("mail.smtp.ssl.enable", ssl);
//
//		// Get the Session object.// and pass username and password
//		Session session = Session.getInstance(properties,
//						new javax.mail.Authenticator() {
//							protected PasswordAuthentication getPasswordAuthentication() {
//								return new PasswordAuthentication(username, password);
//							}
//						});
//		// Used to debug SMTP issues
//		session.setDebug(true);
//		try {
//
//			// Create a default MimeMessage object.
//			MimeMessage message = new MimeMessage(session);
//
//			// Set From: header field of the header.
//			message.setFrom(new InternetAddress(from));
//
//			// Set To: header field of the header.
//			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//
//			// Set Subject: header field
//			message.setSubject("Create jar file");
//
//			Multipart multipart = new MimeMultipart();
//
//			MimeBodyPart attachmentPart = new MimeBodyPart();
//
//			MimeBodyPart textPart = new MimeBodyPart();
//
//			try {
//				String jarFile = SendmailMojo.getJarFile(target.getPath());
//				String report = SendmailMojo.createMailReport(jarFile);
//				File jar = new File("d:\\JAVAProgwards\\2.00_JavaAdvance0421A_lessons\\src\\HomeWork\\MavenCalculator\\MavenCalculator-1.0.rar");
//
//				attachmentPart.attachFile(jar);
//				textPart.setText(report);
//				multipart.addBodyPart(textPart);
//				multipart.addBodyPart(attachmentPart);
//
//			} catch (IOException e) {
//
//				e.printStackTrace();
//
//			}
//
//			message.setContent(multipart);
//
//			System.out.println("sending...");
//			// Send message
//			Transport.send(message);
//			System.out.println("Sent message successfully....");
//		} catch (MessagingException mex) {
//			mex.printStackTrace();
//		}
//	}
//}
