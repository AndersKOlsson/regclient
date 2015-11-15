package com.github.anderskolsson.regclient.rest.elements;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LogResponse {
	public String user;
	public String[] logins;
	
	public LogResponse() {
		super();
	}
	
	public LogResponse(final String user, final String[] logins){
		this.user = user;
		this.logins = logins;
	}
	
	public String toString(){
		return Arrays.toString(logins);
	}
}
