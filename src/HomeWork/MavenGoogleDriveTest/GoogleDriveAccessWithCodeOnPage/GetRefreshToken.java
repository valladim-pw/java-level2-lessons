package HomeWork.MavenGoogleDriveTest.GoogleDriveAccessWithCodeOnPage;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets.Details;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential.Builder;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.DriveScopes;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public final class GetRefreshToken {
	
	// SETTINGS/*  w w  w  .  d   e  m o   2 s  . c  o   m */
	private static final String CLIENT_ID = "568884029640-ljk5jgp02u6jnl3866hsfvg4ifkd1iaf.apps.googleusercontent.com";
	private static final String CLIENT_SECRET = "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";
	
	// CONSTANTS
	private static final String CALLBACK_URL = "urn:ietf:wg:oauth:2.0:oob";
	
	// INIT
	private GetRefreshToken() {
	
	}
	
	// RUN
	public static void main(String[] args) throws IOException {
		GoogleClientSecrets clientSecrets = createClientSecrets();
		GoogleAuthorizationCodeFlow authorizationFlow = new GoogleAuthorizationCodeFlow.Builder(
						new NetHttpTransport(), new JacksonFactory(), clientSecrets, Arrays.asList(DriveScopes.DRIVE)).setAccessType("offline")
						                                                .build();
		
		String authorizeUrl = authorizationFlow.newAuthorizationUrl().setRedirectUri(CALLBACK_URL).build();
		System.out.println("Open this url in your browser: \n" + authorizeUrl + "\n");
		String authorizationCode = readInputLine("Enter the code you received:");
		
		GoogleAuthorizationCodeTokenRequest tokenRequest = authorizationFlow.newTokenRequest(authorizationCode);
		tokenRequest.setRedirectUri(CALLBACK_URL);
		GoogleTokenResponse tokenResponse = tokenRequest.execute();
		
		GoogleCredential credential = buildCredential(clientSecrets, tokenResponse);
		System.out.println("Your refresh token is:\n" + credential.getRefreshToken());
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
		GoogleCredential credential = new Builder().setTransport(new NetHttpTransport())
						                              .setJsonFactory(new JacksonFactory()).setClientSecrets(clientSecrets).build();
		
		credential.setFromTokenResponse(tokenResponse);
		return credential;
	}
	
	private static String readInputLine(String prompt) {
		System.out.println(prompt);
		return new Scanner(System.in).nextLine();
	}
	
}
