package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity implementation class for Entity: WorkingHour
 *
 */
@Entity

public class WorkingHour implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Temporal(value = TemporalType.DATE)
	private Date starting;
	private long duration;
	private User user;
	private Issue issue;

	private static final long serialVersionUID = 1L;

	public WorkingHour() {
		super();
	}

	public Date getStarting() {
		return starting;
	}

	public void setStarting(Date starting) {
		this.starting = starting;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "WorkingHour [id=" + id + ", starting=" + starting + ", duration=" + duration + ", user=" + user
				+ ", issue=" + issue + "]";
	}

}
