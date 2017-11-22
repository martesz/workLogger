package authentication;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import entities.User;

public class UserSecurityContext implements SecurityContext {

	private User user;
	private UriInfo uriInfo;

	public UserSecurityContext(User user, UriInfo uriInfo) {
		this.user = user;
		this.uriInfo = uriInfo;
	}

	@Override
	public String getAuthenticationScheme() {
		return null;
	}

	@Override
	public Principal getUserPrincipal() {
		return new Principal() {
			@Override
			public String getName() {
				return user.getName();
			}
		};
	}

	@Override
	public boolean isSecure() {
		return uriInfo.getAbsolutePath().toString().startsWith("https");
	}

	@Override
	public boolean isUserInRole(String arg0) {
		return false;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
