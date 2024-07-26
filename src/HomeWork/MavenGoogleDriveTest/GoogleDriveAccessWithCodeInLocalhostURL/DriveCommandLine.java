package HomeWork.MavenGoogleDriveTest.GoogleDriveAccessWithCodeInLocalhostURL;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import javax.swing.JOptionPane;

public class DriveCommandLine {
	
	private static final String CLIENT_ID = "568884029640-ljk5jgp02u6jnl3866hsfvg4ifkd1iaf.apps.googleusercontent.com";
	private static final String CLIENT_SECRET = "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";
	private static final String REDIRECT_URI = "http://localhost";//:63342/";
	private Drive service;
	
	public static void main(String[] args) throws IOException {
		HttpTransport httpTransport = new NetHttpTransport();
		JsonFactory jsonFactory = new JacksonFactory();
		
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, jsonFactory,
						CLIENT_ID, CLIENT_SECRET, Arrays.asList(DriveScopes.DRIVE)).setAccessType("online")
						                                   .setApprovalPrompt("auto").build();
		
		String url = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();
		System.out.println(
						"1. По ссылке ниже откройте окно в браузере, выберите аккаунт \"Vadim Kodochigov\", далее нажимайте кнопки \"Продолжить\",\n" +
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
		System.out.println(response.getAccessToken());
		
		GoogleCredential credential = new GoogleCredential().setFromTokenResponse(response);
	}
}