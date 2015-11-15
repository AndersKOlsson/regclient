package com.github.anderskolsson.regclient.commands;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.github.anderskolsson.regclient.exceptions.RestException;
import com.github.anderskolsson.regclient.rest.RestBase;

@Parameters
public class RegisterCmd implements Command {
	@Parameter(names = "--username", required = true)
	public String userName;

	@Parameter(names = "--password", password = true, required = true)
	public String password;

	public void execute(final BaseConfig config) throws RestException {
		String userUrl = new RestBase(config.host, config.port).register(userName, password);
		System.out.println("User successfully created: " + System.lineSeparator() + userUrl);
	}
}
