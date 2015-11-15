package com.github.anderskolsson.regclient;

import java.io.IOException;
import java.net.URISyntaxException;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.github.anderskolsson.regclient.commands.BaseConfig;
import com.github.anderskolsson.regclient.commands.Command;
import com.github.anderskolsson.regclient.commands.GetLogCmd;
import com.github.anderskolsson.regclient.commands.LoginCmd;
import com.github.anderskolsson.regclient.commands.RegisterCmd;
import com.github.anderskolsson.regclient.exceptions.RestException;

public class RegClient {
	enum COMMANDS {
		register(new RegisterCmd()),
		login(new LoginCmd()),
		getlogins(new GetLogCmd());
		
		private Command command;
		private COMMANDS(final Command command){
			this.command = command;
		}
		public Command getCommand(){
			return this.command;
		}
	}

	public static void main(String[] args) throws IOException, URISyntaxException {
		BaseConfig config = new BaseConfig();
		JCommander jc = new JCommander(config);
		for(COMMANDS command : COMMANDS.values()){
			jc.addCommand(command.name(), command.getCommand());
		}

		try {
			jc.parse(args);
			if(config.help){
				jc.usage();
				System.exit(0);
			}
			if(null == jc.getParsedCommand()){
				jc.usage();
				System.err.println("A Command is required");
				System.exit(1);
			}
			COMMANDS selectedCmd = COMMANDS.valueOf(jc.getParsedCommand());
			if(null == selectedCmd){
				jc.usage();
				System.err.println("No such command: " + jc.getParsedCommand());
				System.exit(1);
			}
			selectedCmd.getCommand().execute(config);
		} catch (ParameterException | RestException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}
}
