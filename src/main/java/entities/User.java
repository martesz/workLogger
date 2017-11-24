package entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity
@Table(name = "USERS")
public class User implements Serializable {

	private static final long serialVersionUID = 2L;

	@Id
	private String googleId;
	private String name;

	@Enumerated(EnumType.STRING)
	private Level level;
	
	public enum Level {
		EMPLOYEE, PROJECT_LEADER, ADMIN
	}
	
	public User() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGoogleId() {
		return googleId;
	}

	public void setGoogleId(String googleId) {
		this.googleId = googleId;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public void setLevelFromString(String level) {
		this.level = Level.valueOf(level);
	}

	@Override
	public String toString() {
		return "User [googleId=" + googleId + ", name=" + name + ", level=" + level + "]";
	}

}
