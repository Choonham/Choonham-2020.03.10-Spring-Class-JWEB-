package com.choonham.mpd.dto;

public class MsgDTO {
	private String from = null;
	private String to = null;
	private String time = null;
	private String content = null;
	private String code = null;
	
	public MsgDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getFirstLine(String content) {
		String firstLine = null;
		String[] lines = content.split("\n");
		firstLine = lines[0];
		return firstLine;
	}
	
}
