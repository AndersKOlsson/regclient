package com.github.anderskolsson.regclient.commands;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.github.anderskolsson.regclient.exceptions.RestException;
import com.github.anderskolsson.regclient.rest.RestBase;

@Parameters
public class LoginCmd implements Command {
	@Parameter(names = "--uuid", required = true)
	public String uuid;

	@Parameter(names = "--password", password = true, required = true)
	public String password;
	
	@Override
	public void execute(BaseConfig config) throws RestException {
		new RestBase(config.host, config.port).login(uuid, password);
		System.out.println("Login successful");
	}
}
