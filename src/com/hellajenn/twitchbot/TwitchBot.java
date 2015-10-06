package com.hellajenn.twitchbot;

import java.util.TreeMap;

import org.jibble.pircbot.PircBot;

public class TwitchBot extends PircBot {
	
	public static final String SERVER = "irc.twitch.tv";
	public static final int PORT = 6667;
	
	private TreeMap<String, String> responders;

	public TwitchBot(String userName) {
		setName(userName);
		responders = new TreeMap<String, String>();
		
	}
	@Override
	public void onMessage(String channel, String sender,
            String login, String hostname, String message) {
		
		String[] splitMessage = message.split(" ");
		String command = splitMessage[0];
			
		if (responderExists(command)) {
			String response = getResponse(command);
			response = response.replaceAll("(?i)\\$name", sender);
			sendMessage(channel, response);
		}
	}
	
	public void addResponder(String command, String response) {
		responders.put(command.toLowerCase().trim(), response.trim());
	}
	
	public void removeResponder(String command) {
		responders.remove(command.toLowerCase().trim());
	}
	
	public boolean responderExists(String command) {
		return responders.containsKey(command.toLowerCase().trim());
	}
	
	public String getResponse(String command) {
		return responders.get(command.toLowerCase().trim());
		
	}

}
