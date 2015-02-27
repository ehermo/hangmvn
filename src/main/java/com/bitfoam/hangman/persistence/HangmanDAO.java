package com.bitfoam.hangman.persistence;

import java.util.concurrent.ConcurrentHashMap;

import com.bitfoam.hangman.model.HangmanGame;

/**
 * Singleton by enum approach to hold the running game instances
 * @author ehermo
 *
 */
public enum HangmanDAO
{
	
	instance;

	private ConcurrentHashMap<String, HangmanGame> contentProvider = new ConcurrentHashMap<String, HangmanGame>();
	
	private HangmanDAO()
	{
		
	}
	
	public ConcurrentHashMap<String,HangmanGame> getModel()
	{
		return contentProvider;
	}
}
