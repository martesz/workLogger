package authentication;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;

import com.google.api.client.auth.openidconnect.IdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;

import entities.User;

@Stateless
public class GoogleLogin {
	public static final String ISSUER = "https://accounts.google.com";

	public static final String SERVICE_CLIENT_ID = "184243885869-enlbqgos6p3t9fqujkh89squibv04ncu.apps.googleusercontent.com";

	public User authenticateAndroid(String googleIdToken) {
        GoogleIdTokenVerifier verifier = createVerifier(SERVICE_CLIENT_ID, ISSUER);
        User user = authenticate(verifier, googleIdToken);
        return user;
    }
	
	private User authenticate(GoogleIdTokenVerifier verifier, String googleIdToken) {
		try {
			GoogleIdToken idToken = verifier.verify(googleIdToken);
			if (idToken != null) {
				Payload payload = idToken.getPayload();
				String googleId = payload.getSubject();
				String name = (String) payload.get("name");
				User user = new User();
				user.setGoogleId(googleId);
				user.setName(name);
				return user;
			} else {
				return null;
			}
		} catch (GeneralSecurityException | IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	private GoogleIdTokenVerifier createVerifier(String clientId, String issuer) {
		NetHttpTransport transport = new NetHttpTransport();
		
		JsonFactory jsonFactory = new GsonFactory();
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory )
				.setAudience(Arrays.asList(clientId)).setIssuer(issuer).build();
		return verifier;
	}
}
