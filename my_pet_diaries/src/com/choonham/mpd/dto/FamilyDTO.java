package com.choonham.mpd.dto;


public class FamilyDTO {

	private String memberID = null;
	private String hostID = null;
	private String familyName = null;
	private String petName = null;
	private String cDate = null;
	private int status = 0;
	private String lastWalkWith = null;
	private String lastWalkTime = null;
	private String lastTreat = null;
	private String whoGive = null;
	private String memo = null;
	
	public FamilyDTO() {
	}

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public String getHostID() {
		return hostID;
	}

	public void setHostID(String hostID) {
		this.hostID = hostID;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public String getcDate() {
		return cDate;
	}

	public void setcDate(String cDate) {
		this.cDate = cDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int statue) {
		this.status = statue;
	}

	public String getLastWalkWith() {
		return lastWalkWith;
	}

	public void setLastWalkWith(String lastWalkWith) {
		this.lastWalkWith = lastWalkWith;
	}

	public String getLastWalkTime() {
		return lastWalkTime;
	}

	public void setLastWalkTime(String lastWalkTime) {
		this.lastWalkTime = lastWalkTime;
	}

	public String getLastTreat() {
		return lastTreat;
	}

	public void setLastTreat(String lastTreat) {
		this.lastTreat = lastTreat;
	}

	public String getWhoGive() {
		return whoGive;
	}

	public void setWhoGive(String whoGive) {
		this.whoGive = whoGive;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
