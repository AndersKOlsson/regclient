package com.github.anderskolsson.regclient.commands;

import com.github.anderskolsson.regclient.exceptions.RestException;

public interface Command {
	public void execute(BaseConfig config) throws RestException;
}
