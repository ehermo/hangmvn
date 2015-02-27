package com.bitfoam.hangman.model;

import java.util.Random;

/**
 * Collection of words to play
 * @author ehermo
 *
 */
public class Dictionary
{
	
	private final static String[] WORDS =
	{
		"CORNERSTONE",
		"EMPTY",
		"BLUEBERRY",
		"SQUIRREL",
		"CAPTAIN",
		"SOLDIER",
		"HAPPINESS",
		"DICTIONARY",
		"SUCCESS",
		"LOSER",
		"ARMY",
		"ANATOMY",
		"FANTASY",
		"WINNER",
		"ROUTINE",
		"HORSE",
		"HOTCHPOTCH"
	};
	
	public synchronized static String random()
	{
		int randomIndex = (new Random()).nextInt(WORDS.length);
		return WORDS[randomIndex];
	}
}
