package HomeWork.MavenGoogleDriveTest.GoogleDriveAccessWithCodeInLocalhostURL;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;

import java.io.*;
import java.util.Arrays;

public class GoogleDriveAccess {
	
	private static final String CLIENT_ID = "568884029640-ljk5jgp02u6jnl3866hsfvg4ifkd1iaf.apps.googleusercontent.com";
	private static final String CLIENT_SECRET = "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";
	private static final String REDIRECT_URI = "http://localhost:63342/2.00_JavaAdvance0421A_lessons/src/A_Visual-Materials/LecturesHTML";
	private static HttpTransport httpTransport;
	private static JsonFactory jsonFactory;
	private static Drive service;
	
	public GoogleDriveAccess() {
		httpTransport = new NetHttpTransport();
		jsonFactory = new JacksonFactory();
	}
	
	public static Credential getCredentials() throws IOException {
		
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, jsonFactory,
						CLIENT_ID, CLIENT_SECRET, Arrays.asList(DriveScopes.DRIVE)).setAccessType("online")
						                                   .setApprovalPrompt("auto").build();
		
		String url = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();
		System.out.println("ВНИМАНИЕ! Для доступа к Google Диску Вам нужно выполнить следующие действия:\n" +
						"1. По ссылке ниже откройте окно в браузере, выберите аккаунт \"Vadim Kodochigov\", далее в окнах нажимайте кнопки \"Продолжить\",\n" +
										"2. Дойдя до окна с ошибкой соединения (\"404 Not Found\" или \"Не удается получить доступ к сайту\")\n" +
										"выделите и скопируйте все символы кода авторизации из адресной строки браузера\n" +
										"(начиная первого символа после \"code=\" до последнего символа перед \"&scope\"),\n" +
										"3. В консоли кликните строку ниже ссылки и вставьте (Ctrl + V) код авторизации,\n" +
										"4. Нажмите Enter."
		);
		System.out.println("  " + url);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String code = br.readLine();
		
		GoogleTokenResponse response = flow.newTokenRequest(code).setRedirectUri(REDIRECT_URI).execute();
		System.out.println("Токен доступа Google Диска успешно получен!\n" + response.getAccessToken());
		System.out.println("Файл jar проекта будет загружен на Google Диск и ссылка на него будет отправлена по почте");
		GoogleCredential credential = new GoogleCredential().setFromTokenResponse(response);
		return credential;
	}
	
	public static Drive getDriveService() throws IOException {
		
		if (service != null) {
			return service;
		}
		Credential credential = getCredentials();
		
		service = new Drive.Builder(httpTransport, jsonFactory, credential)
						                .setApplicationName("Email Maven Plugin").build();
		return service;
	}
}