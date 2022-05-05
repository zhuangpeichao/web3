package com.bean;

import java.io.Serializable;

/**
 * 
 * ��������
 * @author 26986
 *
 */
public class Type implements Serializable {

	/**
	 * ���л�id
	 */
	private static final long serialVersionUID = -8401507241892156970L;
	/**
	 * ����id
	 */
	private Integer typeId;
	/**
	 * ��������
	 */
	private String typeName;
	
	public Type() {
		super();
	}
	public Type(Integer typeId, String typeName) {
		super();
		this.typeId = typeId;
		this.typeName = typeName;
	}
	
	
	public Type(Integer typeId) {
		super();
		this.typeId = typeId;
	}
	
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	
}
