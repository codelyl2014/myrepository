package com.model;

public class DbJiraData {
	private String issuid;
	private String lower_user_name;
	private String workday;
	private String timeworked;

	public String getIssuid() {
		return issuid;
	}

	public void setIssuid(String issuid) {
		this.issuid = issuid;
	}

	public String getLower_user_name() {
		return lower_user_name;
	}

	public void setLower_user_name(String lower_user_name) {
		this.lower_user_name = lower_user_name;
	}

	public String getWorkday() {
		return workday;
	}

	public void setWorkday(String workday) {
		this.workday = workday;
	}

	public String getTimeworked() {
		return timeworked;
	}

	public void setTimeworked(String timeworked) {
		this.timeworked = timeworked;
	}
}