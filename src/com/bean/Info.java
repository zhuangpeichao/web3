package com.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * ������Ϣ
 * @author 26986
 *
 */
public class Info implements Serializable {

	/**
	 * ���л�id
	 * 
	 */
	private static final long serialVersionUID = -5318888072409370570L;
	/**
	 * ����id
	 */
	private Integer newsId;
	/**
	 * ���ű���
	 */
	private String newsTitle;
	/**
	 * ��������
	 */
	private Type newsType;
	/**
	 * ����ͼƬ
	 */
	private String newsImg;
	/**
	 * ��������
	 */
	private String newsContent;
	/**
	 * ������
	 */
	private User sendUser;
	/**
	 * ����ʱ��
	 */
	private Date sendTime;
	
	public Info() {
		super();
	}
	public Info(Integer newsId, String newsTitle, Type newsType,
			String newsImg, String newsContent, User sendUser, Date sendTime) {
		super();
		this.newsId = newsId;
		this.newsTitle = newsTitle;
		this.newsType = newsType;
		this.newsImg = newsImg;
		this.newsContent = newsContent;
		this.sendUser = sendUser;
		this.sendTime = sendTime;
	}
	public Integer getNewsId() {
		return newsId;
	}
	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}
	public String getNewsTitle() {
		return newsTitle;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	
	public Type getNewsType() {
		return newsType;
	}
	public void setNewsType(Type newsType) {
		this.newsType = newsType;
	}
	public String getNewsImg() {
		return newsImg;
	}
	public void setNewsImg(String newsImg) {
		this.newsImg = newsImg;
	}
	public String getNewsContent() {
		return newsContent;
	}
	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}
	
	public User getSendUser() {
		return sendUser;
	}
	public void setSendUser(User sendUser) {
		this.sendUser = sendUser;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	
	
	
	
	
	
	
}
