package authentication;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;

import entities.User;

public class UserSecurityContext implements SecurityContext {

	private User user;

	public UserSecurityContext(User user) {
		super();
		this.user = user;
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
				// TODO Auto-generated method stub
				return user.getName();
			}
		};
	}

	@Override
	public boolean isSecure() {
		return false;
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
