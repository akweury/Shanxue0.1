package testlearn.shanxue.edu.shanxue01.models;

import java.io.Serializable;

public class UserFocusModle implements Serializable{
	private String focus_table_ID;
	private String focus_ID;
	private String befocused_ID;

	public String getFocus_table_ID() {
		return focus_table_ID;
	}

	public void setFocus_table_ID(String focus_table_ID) {
		this.focus_table_ID = focus_table_ID;
	}

	public String getFocus_ID() {
		return focus_ID;
	}

	public void setFocus_ID(String focus_ID) {
		this.focus_ID = focus_ID;
	}

	public String getBefocused_ID() {
		return befocused_ID;
	}

	public void setBefocused_ID(String befocused_ID) {
		this.befocused_ID = befocused_ID;
	}
}