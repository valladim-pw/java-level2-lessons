package HomeWork.MavenEmailPluginVariants;

//import com.google.api.client.util.store.FileDataStoreFactory;
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
//import java.io.*;
//import java.nio.file.*;
//import java.nio.file.attribute.BasicFileAttributes;
//import java.util.*;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipFile;
//import java.util.zip.ZipInputStream;
//import java.util.zip.ZipOutputStream;
//
//@Mojo(name = "sendmail", defaultPhase = LifecyclePhase.PROCESS_SOURCES)
//public class SendmailMojo5 extends AbstractMojo {
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
//	// Свойство buggyLoger - временное решение для бага в com.google.api.client.util.store.FileDataStoreFactory
//	// setPermissionsToOwnerOnly под Windows
//	//https://github.com/googleapis/google-http-java-client/issues/315
//	final java.util.logging.Logger buggyLogger = java.util.logging.Logger.getLogger(FileDataStoreFactory.class.getName());
//
//	// Обходим директорию target и получаем путь к jar-файлу c зависимостями, созданному с помощью assembly-plugin,
//	// который в случае создания двух jar-файлов (с помощью jar-plugin и assembly-plugin) будет более позним по времени создания
//	// и соответственно будет последним в списке jars, отсортированном по времени.
//	// В случае отсутствия jar-файла получаем пустую строку.
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
//	// Создаем отчет для отправки по почте.
//	//  Для этого путем обхода ищем в архиве, путь к которому получен в методе getJarFile, файл MAINIFEST
//	// считываем его содержимое и на его основе возвращаем строку с отчетом.
//	// Если путь к архиву пуст, создаем отчет о неудаче.
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
//	/*---------------------------------------------------------------------------------------------------------
//	* !!! Политика безопасности Google запрещает прикреплять к письмам отправляемым с сервера gmail.com
//	* файлы с рядом расширений, в том числе архивы с расширениями .zip, .gZip, .jar
//	* Но(!!!) как ни странно в эту группу не входят архивы с раширением .rar, эти файлы отправляются!
//	* Поэтому методы ниже нужны для копирования jar-архива в rar-архив с тем же названием
//	*----------------------------------------------------------------------------------------------------------
//	*/
//
//	// Вспомогательный метод newFile() необходим для защиты от записи файлов в файловую систему за пределами целевой папки
//	// возможной из-за уязвимости Zip Slip
//
//	public static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
//		File destFile = new File(destinationDir, zipEntry.getName());
//
//		String destDirPath = destinationDir.getCanonicalPath();
//		String destFilePath = destFile.getCanonicalPath();
//
//		if (!destFilePath.startsWith(destDirPath + File.separator)) {
//			throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
//		}
//		return destFile;
//	}
//
//	// Вспомогательный метод для записи папок и файлов в архив из определенной директории
//
//	static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
//		if (fileToZip.isHidden()) {
//			return;
//		}
//		if (fileToZip.isDirectory()) {
//			if (fileName.endsWith("/")) {
//				zipOut.putNextEntry(new ZipEntry(fileName));
//				zipOut.closeEntry();
//			} else {
//				zipOut.putNextEntry(new ZipEntry(fileName + "/"));
//				zipOut.closeEntry();
//			}
//			File[] children = fileToZip.listFiles();
//			for (File childFile : children) {
//				zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
//			}
//			return;
//		}
//		FileInputStream fis = new FileInputStream(fileToZip);
//		ZipEntry zipEntry = new ZipEntry(fileName);
//		zipOut.putNextEntry(zipEntry);
//		byte[] bytes = new byte[1024];
//		int length;
//		while ((length = fis.read(bytes)) >= 0) {
//			zipOut.write(bytes, 0, length);
//		}
//		fis.close();
//	}
//
//// В этом методе в директории target создается файл архива с раширением .rar, с названием как у архива jar,
//// создается временная директория,
//// в эту директорию  переносятся папки и файлы из архива jar,
//// потом уже эта директория архивируется в файл с расширением .rar
//// этот файл возвращается для отправки по почте
//
//	static File createRarFile(File target, String jarFilePath) throws IOException {
//		// выделяем имя jar-файла без расширения
//		File zip = new File(jarFilePath);
//		String zipName = zip.getName();
//		zipName = zipName.substring(0, zipName.length() - 3);
//		//Создаем rar-файл в директории target
//		File rarFile = new File(target, zipName + "rar");
//		rarFile.createNewFile();
//		// Создаем временную директорию для папок и файлов из архива jar
//		File destDir = new File(target, zipName);
//		if (!destDir.exists())
//			destDir.mkdirs();
//		// Во временной директории создаем папки и файлы jar-архива
//		byte[] buffer = new byte[1024];
//		ZipInputStream zis = new ZipInputStream(new FileInputStream(jarFilePath));
//		ZipEntry zipEntry = zis.getNextEntry();
//		while (zipEntry != null) {
//			File newFile = newFile(destDir, zipEntry);
//			if (zipEntry.isDirectory()) {
//				if (!newFile.isDirectory() && !newFile.mkdirs()) {
//					throw new IOException("Failed to create directory " + newFile);
//				}
//			} else {
//				// учитываем особенности создания архивов в Windows
//				File parent = newFile.getParentFile();
//				if (!parent.isDirectory() && !parent.mkdirs()) {
//					throw new IOException("Failed to create directory " + parent);
//				}
//				// записываем контент файлов
//				FileOutputStream fos = new FileOutputStream(newFile);
//				int len;
//				while ((len = zis.read(buffer)) > 0) {
//					fos.write(buffer, 0, len);
//				}
//				fos.close();
//			}
//			zipEntry = zis.getNextEntry();
//		}
//
//		// Архивируем временную директорию в файл архива с расширением .rar
//
//		String sourceFile = destDir.getPath();
//		FileOutputStream fos = new FileOutputStream(rarFile);
//		ZipOutputStream zipOut = new ZipOutputStream(fos);
//
//		File fileToZip = new File(sourceFile);
//		zipFile(fileToZip, fileToZip.getName(), zipOut);
//		zipOut.close();
//		fos.close();
//		//После завершения архивации удаляем временную директорию
//
//		//FileUtils.deleteDirectory(new File(destDir.getPath()));
//		return rarFile;
//	}
//
//	public void execute() throws MojoExecutionException {
//		// Свойство buggyLoger - временное решение для бага в com.google.api.client.util.store.FileDataStoreFactory
//		// setPermissionsToOwnerOnly под Windows
//		//https://github.com/googleapis/google-http-java-client/issues/315
//		buggyLogger.setLevel(java.util.logging.Level.SEVERE);
//		//Устанавливаем  свойства определенные с помощью параметров Mojo-класса и в конфигурации плагина в pom.xl
//		File target = outputDirectory;
//		String to = emailTo;
//		String from = emailFrom;
//		String host = emailHost;
//		int port = emailPort;
//		boolean auth = emailAuth;
//		boolean ssl = emailSSL;
//
//		final String username = "vkodoch@gmail.com";
//		// Для отправки почты програмным путем в своем Google Account нужно создавть 16-символьный пароль для приложений
//		//!!!Это НЕ пароль для почты
//		/*
//		- Перейдите в свой аккаунт Google.
//		- Выберите раздел "Безопасность".
//		- В разделе "Вход в Google" нажмите "Двухэтапная проверка".
//		- Внизу страницы найдите и нажмите "Пароли для приложений".
//		- Укажите имя, которое поможет вам запомнить, где вы собираетесь использовать пароль приложения.
//		- Нажмите "Сгенерировать".
//		Чтобы использовать пароль приложения, следуйте инструкциям на экране.
//		Пароль приложения - это 16-значный код, сгенерированный на вашем устройстве.
//		Нажмите "Готово".
//		*/
//		final String password = "fnefzuokbwfgjcmi";
//
//		if (!target.exists()) {
//			target.mkdirs();
//		}
//		// Устанавливаем системные свойства почтового сервера
//		Properties properties = System.getProperties();
//
//		properties.put("mail.smtp.host", host);
//		properties.put("mail.smtp.port", port);
//		properties.put("mail.smtp.auth", auth);
//		properties.put("mail.smtp.ssl.enable", ssl);
//
//		// Получаем объект Session с именем пользователя и паролем  и передаем
//		Session session = Session.getInstance(properties,
//						new javax.mail.Authenticator() {
//							protected PasswordAuthentication getPasswordAuthentication() {
//								return new PasswordAuthentication(username, password);
//							}
//						});
//		// Логирование отправки почты
//		session.setDebug(true);
//		try {
//			// Создаем объект письма, где устанавливаем заголовки
//			MimeMessage message = new MimeMessage(session);
//			//От кого: почтовый адрес
//			message.setFrom(new InternetAddress(from));
//			//Кому:
//			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//			//Тема письма
//			message.setSubject("Create jar file");
//			// Создаем объекты для отправки письма с прикреплением файла
//			Multipart multipart = new MimeMultipart();
//			MimeBodyPart attachmentPart = new MimeBodyPart();
//			MimeBodyPart textPart = new MimeBodyPart();
//
//			try {
//				// С помощью методов класса получаем объекты отчета и прикрепляемого файла rar-архива
//				String jarFile = SendmailMojo.getJarFile(target.getPath());
//				String report = SendmailMojo.createMailReport(jarFile);
//				//File rarFile = SendmailMojo.createRarFile(target, jarFile);
//				File rar = new File("d:\\JAVAProgwards\\2.00_JavaAdvance0421A_lessons\\src\\HomeWork\\MavenCalculator\\MavenCalculator.rar");
//
//				attachmentPart.attachFile(rar);
//				textPart.setText(report);
//				multipart.addBodyPart(textPart);
//				multipart.addBodyPart(attachmentPart);
//
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			// Отправляем письмо
//			message.setContent(multipart);
//			System.out.println("sending...");
//			Transport.send(message);
//			System.out.println("Sent message successfully....");
//
//		} catch (MessagingException mex) {
//			mex.printStackTrace();
//		}
//	}
//}
