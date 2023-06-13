package object;

import java.sql.Date;

public class IntroductionObj {
	// フィールド変数
	private int introductionId;
	private String introductionUserId;
	private String introductionName;
	private String introductionKana;
	private String introductionGender;
	private String[] introductionHobby;
	private String introductionDescription;
	private Date createdAt;
	private Date updatedAt;

	// getter,setter
	public String getIntroductionName() {
		return introductionName;
	}
	public void setIntroductionName(String introductionName) {
		this.introductionName = introductionName;
	}

	public String getIntroductionKana() {
		return introductionKana;
	}
	public void setIntroductionKana(String introductionKana) {
		this.introductionKana = introductionKana;
	}

	public String getIntroductionGender() {
		return introductionGender;
	}
	public void setIntroductionGender(String introductionGender) {
		this.introductionGender = introductionGender;
	}

	public String[] getIntroductionHobby() {
		return introductionHobby;
	}
	public void setIntroductionHobby(String[] introduction_hobby) {
		this.introductionHobby = introduction_hobby;
	}

	public String getIntroductionDescription() {
		return introductionDescription;
	}
	public void setIntroductionDescription(String introductionDescription) {
		this.introductionDescription = introductionDescription;
	}

	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public int getIntroductionId() {
		return introductionId;
	}
	public void setIntroductionId(int user_id) {
		this.introductionId = user_id;
	}

	public String getIntroductionUserId() {
		return introductionUserId;
	}
	public void setIntroductionUserId(String introductionUserId) {
		this.introductionUserId = introductionUserId;
	}
}
