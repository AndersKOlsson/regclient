package com.github.anderskolsson.regclient.commands;

import java.util.Arrays;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.github.anderskolsson.regclient.exceptions.RestException;
import com.github.anderskolsson.regclient.rest.RestBase;

@Parameters
public class GetLogCmd implements Command {
	@Parameter(names = "--uuid", required = true)
	public String uuid;

	@Parameter(names = "--password", password = true, required = true)
	public String password;
	
	@Parameter(names = "--numLogs")
	public int numLogs = 5;
	
	@Override
	public void execute(BaseConfig config) throws RestException {
		String[] logins = new RestBase(config.host, config.port).getLogs(uuid, password, numLogs);
		System.out.println("Logins: "+System.lineSeparator()+Arrays.toString(logins));
	}
}
