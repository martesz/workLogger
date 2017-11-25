package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity implementation class for Entity: Report
 *
 */
@Entity
public class Report implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private User owner;
	private String googleId;
	@Enumerated(EnumType.STRING)
	private ReportType reportType;
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date startDate;

	public enum ReportType {
		DAILY(1), WEEKLY(7), MONTHLY(30);

		private int lengthDays;

		private ReportType(int lengthDays) {
			this.lengthDays = lengthDays;
		}

		public int getLengthDays() {
			return lengthDays;
		}
	}

	public Report() {
		super();
	}

	public String getGoogleId() {
		return googleId;
	}
	
	public ReportType getReportType() {
		return reportType;
	}

	public void setReportType(final ReportType reportType) {
		this.reportType = reportType;
	}

	public long getId() {
		return id;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Override
	public String toString() {
		return "Report [id=" + id + ", owner=" + owner + ", googleId=" + googleId + ", reportType=" + reportType
				+ ", startDate=" + startDate + "]";
	}
	
}