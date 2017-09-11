package testlearn.shanxue.edu.shanxue01.models;

import java.io.Serializable;

public class UserInfoModle implements Serializable {
	private String ID;
	private String password;
	private String nickName;
	private String portrait;
	private int sex;
	private String birthday;
	private String region;
	private String school;
	private String personalResume;
	private String updateTime;
	private String createDate;
	private int userLevel;
	private int uploadEntry;
	private int receiveLike;
	private String others;

//	public static final Parcelable.Creator<UserInfoModle> CREATOR = new Creator<UserInfoModle>() {
//		public UserInfoModle createFromParcel(Parcel source) {
//			UserInfoModle userInfoModle = new UserInfoModle();
//			userInfoModle.ID = source.readString();
//			userInfoModle.password = source.readString();
//			userInfoModle.nickName = source.readString();
//			userInfoModle.portrait = source.readString();
//			userInfoModle.sex = source.readInt();
//			userInfoModle.birthday = source.readString();
//			userInfoModle.region = source.readString();
//			userInfoModle.school = source.readString();
//			userInfoModle.personalResume = source.readString();
//			userInfoModle.updateTime = source.readString();
//			userInfoModle.createDate = source.readString();
//			userInfoModle.others = source.readString();
//			userInfoModle.userLevel = source.readInt();
//			userInfoModle.uploadEntry = source.readInt();
//			userInfoModle.receiveLike = source.readInt();
//			return userInfoModle;
//		}
//		public UserInfoModle[] newArray(int size) {
//			return new UserInfoModle[size];
//		}
//	};


	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getPersonalResume() {
		return personalResume;
	}

	public void setPersonalResume(String personalResume) {
		this.personalResume = personalResume;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public int getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}

	public int getUploadEntry() {
		return uploadEntry;
	}

	public void setUploadEntry(int uploadEntry) {
		this.uploadEntry = uploadEntry;
	}

	public int getReceiveLike() {
		return receiveLike;
	}

	public void setReceiveLike(int receiveLike) {
		this.receiveLike = receiveLike;
	}

	public String getOthers() {
		return others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

}