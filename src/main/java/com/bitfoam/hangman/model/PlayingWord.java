package com.bitfoam.hangman.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;

/**
 * This class represents the player's guesses and fails against the secretword 
 * @author ehermo
 *
 */
public class PlayingWord
{
	private String secretWord;
	LinkedHashMap<Integer, Letter> letters;

	public PlayingWord(final String keyWord)
	{
		this.secretWord = keyWord;
		letters = new LinkedHashMap<Integer, Letter>();
		for (int i = 0; i < keyWord.length(); i++)
		{
			letters.put(new Integer(i), Letter.NONE);
		}
	}
	
	public PlayingWord(final String keyWord, ArrayList<Letter> usedLetters )
	{
		this(keyWord);
		if(usedLetters != null)
		{	
			for(Letter letter: usedLetters)	
			{
				checkLetter(letter.name());
			}
		}
	}


	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("");
		for(Letter letter: letters.values())	
		{
			sb.append(letter.toString());
		}
		return sb.toString();
	}

	private ArrayList<Integer> indexesOf(String letter)
	{
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		int fromIndex = 0;

		while (fromIndex >= 0)
		{
			int index = secretWord.indexOf(letter, fromIndex);
			if (index >= 0)
			{
				indexes.add(new Integer(index));
				fromIndex = index + 1;
			} 
			else
			{
				break;
			}
		}
		return indexes;		
	}
	
	private void showGuessedLetter(String letter,ArrayList<Integer> indexes)
	{
		for(Integer index : indexes)	
		{			
			int idx = index.intValue();
			Letter letterToShow = letters.get(idx);
			letterToShow = Letter.valueOf(letter);
			letters.put(idx, letterToShow);

		}
	}
	
	/**
	 * This method evaluates the letter given by the player against the secret to word
	 * @param letter the letter given by the player
	 * @return if the letter is a guess or not
	 */
	public boolean checkLetter(String letter)
	{
		boolean isAGuess = false;

		if (letter.length() == 1)
		{
			ArrayList<Integer> indexes = new ArrayList<Integer>();
			indexes = indexesOf(letter);
			if (indexes.size() > 0)
			{
				isAGuess = true;
				showGuessedLetter(letter, indexes);
			}
		}

		return isAGuess;
	}

	/**
	 * This method retrieves if the playing word has been guessed
	 * @return if the playing word has been guessed
	 */
	public boolean isDone()
	{
		boolean isDone = true;
		
		for(Letter letter: letters.values())
		{
			if (letter.equals(Letter.NONE))
			{
				isDone = false;
				break;
			}
		}		
		return isDone;
	}

	public Collection<Letter> getLetters()
	{
		return letters.values();
	}

	public int getLength()
	{
		return letters.size();
	}

}
