package com.github.anderskolsson.regclient.commands;

import com.beust.jcommander.Parameter;

public class BaseConfig {
	@Parameter(names = "--help", help = true)
	public boolean help;
	
	@Parameter(names = "--host", required = false)
	public String host = "localhost";
	
	@Parameter(names = "--p", required = false)
	public int port = 6789;
}
