package com.blog.exception;

import lombok.Data;

@Data
public class ResourseNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String resourcename;
	private String fieldname;
	private String fieldvalue;
	public ResourseNotFoundException(String resourcename, String fieldname, String fieldvalue) {
		super(String.format("%s is not found with %s : %l", resourcename,fieldname,fieldvalue));
		this.resourcename = resourcename;
		this.fieldname = fieldname;
		this.fieldvalue = fieldvalue;
	}
	
}
