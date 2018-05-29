package com.suyaodong.entity;

public class Book {

	/**
	 * bookID 书ID bookName 书名 college 院系 phone 练习电话 weChat 微信名称 descript 描述 time
	 * 上传时间
	 */
	private int bookID = 0;
	private byte[][] images = new byte[3][1024];
	private String bookName = "";
	private String college = "";
	private String phone = "";
	private String weChat = "";
	private String descript = "";
	private long time = 0;

	public String getWeChart() {
		return weChat;
	}

	public void setWeChart(String weChart) {
		this.weChat = weChart;
	}

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public byte[][] getImages() {
		return images;
	}

	public void setImages(byte[][] images) {
		this.images = images;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAcademic() {
		return college;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public void setAcademic(String academic) {
		this.college = academic;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String toString() {
		return "bookID:" + bookID + "bookName:" + bookName;

	}

}
