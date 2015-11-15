package com.github.anderskolsson.regclient.rest.elements;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JsonUser {
	public String uuid;
	public String userName;
	public String password;

	public JsonUser() {
		super();
	}

	public JsonUser(final String uuid, final String userName, final String password) {
		this.uuid = uuid;
		this.userName = userName;
		this.password = password;
	}
}