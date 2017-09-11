package testlearn.shanxue.edu.shanxue01.models;

import java.io.Serializable;

public class UserseifModle implements Serializable{
	private String study_item_ID;
	private String book_ID;
	private int entry_enable_flag;
	private String entry_item;
	private String entry_explain1;
	private String entry_explain2;
	private String entry_explain3;
	private String entry_createDate;
	private String entry_updateDate;
	private int entry_difficulty;
	private int entry_like_number;
	private int isUpload;

	public String getStudy_item_ID() {
		return study_item_ID;
	}

	public void setStudy_item_ID(String study_item_ID) {
		this.study_item_ID = study_item_ID;
	}

	public String getBook_ID() {
		return book_ID;
	}

	public void setBook_ID(String book_ID) {
		this.book_ID = book_ID;
	}

	public int getEntry_enable_flag() {
		return entry_enable_flag;
	}

	public void setEntry_enable_flag(int entry_enable_flag) {
		this.entry_enable_flag = entry_enable_flag;
	}

	public String getEntry_item() {
		return entry_item;
	}

	public void setEntry_item(String entry_item) {
		this.entry_item = entry_item;
	}

	public String getEntry_explain1() {
		return entry_explain1;
	}

	public void setEntry_explain1(String entry_explain1) {
		this.entry_explain1 = entry_explain1;
	}

	public String getEntry_explain2() {
		return entry_explain2;
	}

	public void setEntry_explain2(String entry_explain2) {
		this.entry_explain2 = entry_explain2;
	}

	public String getEntry_explain3() {
		return entry_explain3;
	}

	public void setEntry_explain3(String entry_explain3) {
		this.entry_explain3 = entry_explain3;
	}

	public String getEntry_createDate() {
		return entry_createDate;
	}

	public void setEntry_createDate(String entry_createDate) {
		this.entry_createDate = entry_createDate;
	}

	public String getEntry_updateDate() {
		return entry_updateDate;
	}

	public void setEntry_updateDate(String entry_updateDate) {
		this.entry_updateDate = entry_updateDate;
	}

	public int getEntry_difficulty() {
		return entry_difficulty;
	}

	public void setEntry_difficulty(int entry_difficulty) {
		this.entry_difficulty = entry_difficulty;
	}

	public int getEntry_like_number() {
		return entry_like_number;
	}

	public void setEntry_like_number(int entry_like_number) {
		this.entry_like_number = entry_like_number;
	}

	public int getIsUpload() {
		return isUpload;
	}

	public void setIsUpload(int isUpload) {
		this.isUpload = isUpload;
	}
}