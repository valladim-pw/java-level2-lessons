package HomeWork.MavenGoogleDriveTest.GoogleDriveAccessWithCodeOnPage;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.*;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets.Details;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential.Builder;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public final class GoogleDriveAccess {
	private static final String CLIENT_ID = "568884029640-ljk5jgp02u6jnl3866hsfvg4ifkd1iaf.apps.googleusercontent.com";
	private static final String CLIENT_SECRET = "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";
	private static final String CALLBACK_URL = "urn:ietf:wg:oauth:2.0:oob";
	private static HttpTransport httpTransport;
	private static JsonFactory jsonFactory;
	private static Drive service;
	
	// INIT
	GoogleDriveAccess() {
		httpTransport = new NetHttpTransport();
		jsonFactory = new JacksonFactory();
	}
	
	private static GoogleClientSecrets createClientSecrets() {
		Details webSecrets = new Details();
		webSecrets.setClientId(CLIENT_ID);
		webSecrets.setClientSecret(CLIENT_SECRET);
		
		GoogleClientSecrets clientSecrets = new GoogleClientSecrets();
		clientSecrets.setWeb(webSecrets);
		
		return clientSecrets;
	}
	
	private static GoogleCredential buildCredential(GoogleClientSecrets clientSecrets,
	                                                GoogleTokenResponse tokenResponse) {
		GoogleCredential credential = new Builder().setTransport(httpTransport)
						                              .setJsonFactory(jsonFactory).setClientSecrets(clientSecrets).build();
		
		credential.setFromTokenResponse(tokenResponse);
		return credential;
	}
	
	static String readInputLine(String prompt) {
		System.out.println(prompt);
		return new Scanner(System.in).nextLine();
	}
	
	public static Credential getCredentials() throws IOException {
		
		GoogleClientSecrets clientSecrets = createClientSecrets();
		GoogleAuthorizationCodeFlow authorizationFlow = new GoogleAuthorizationCodeFlow.Builder(
						httpTransport, jsonFactory, clientSecrets, Arrays.asList(DriveScopes.DRIVE)).setAccessType("offline")
						                                                .build();
		
		String authorizeUrl = authorizationFlow.newAuthorizationUrl().setRedirectUri(CALLBACK_URL).build();
		System.out.println("Open this url in your browser: \n" + authorizeUrl + "\n");
		String authorizationCode = readInputLine("Enter the code you received:");
		
		GoogleAuthorizationCodeTokenRequest tokenRequest = authorizationFlow.newTokenRequest(authorizationCode);
		tokenRequest.setRedirectUri(CALLBACK_URL);
		GoogleTokenResponse tokenResponse = tokenRequest.execute();
		
		GoogleCredential credential = buildCredential(clientSecrets, tokenResponse);
		System.out.println("Your refresh token is:\n" + credential.getRefreshToken());
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
