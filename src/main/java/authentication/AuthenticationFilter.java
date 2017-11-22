package authentication;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ejb.EJB;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;

import entities.User;

/**
 *
 * @author martin
 *
 *         Filters requests coming to the REST end points annotated
 *         with @Secured If any problems happen during the token validation, a
 *         response with status 401 UNAUTHORIZED will be returned. Otherwise,
 *         the request will proceed to an endpoint.
 */
@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

	@EJB
	GoogleLogin googleLogin;

	@Override
	public void filter(ContainerRequestContext crc) throws IOException {
		String googleToken = crc.getHeaderString(HttpHeaders.AUTHORIZATION);
		if (googleToken == null) {
			throw new NotAuthorizedException("Authorization header must be provided");
		}

		final User user = googleLogin.authenticateAndroid(googleToken);
		if(user == null) {
			throw new NotAuthorizedException("Google authentication failed.");
		}
		crc.setSecurityContext(new UserSecurityContext(user, crc.getUriInfo()));
	}

}