package testlearn.shanxue.edu.shanxue01.models;

import java.io.Serializable;

public class UserInterest implements Serializable{
	private String interest_ID;
	private String ID;
	private float interest_poem;
	private float interest_medicine;

	public String getInterest_ID() {
		return interest_ID;
	}

	public void setInterest_ID(String interest_ID) {
		this.interest_ID = interest_ID;
	}

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public float getInterest_poem() {
		return interest_poem;
	}

	public void setInterest_poem(float interest_poem) {
		this.interest_poem = interest_poem;
	}

	public float getInterest_medicine() {
		return interest_medicine;
	}

	public void setInterest_medicine(float interest_medicine) {
		this.interest_medicine = interest_medicine;
	}
}