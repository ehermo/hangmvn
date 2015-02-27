package com.bitfoam.hangman.model;

import java.util.ArrayList;
import java.util.UUID;

public class HangmanGame
{
	private UUID id;
	private int remainingMoves;
	private ArrayList<Letter> usedLetters;
	private PlayingWord playingWord;
	private String secretWord;
	private Status status;
	
	
	public HangmanGame()
	{
		id = UUID.randomUUID();
		remainingMoves = Body.values().length;
		usedLetters = new ArrayList<Letter>();
		secretWord = Dictionary.random();
		playingWord = new PlayingWord(secretWord);
		status = Status.PLAYING;
	}
	
	/*
	 * Constructor for developing purposes
	 */
	public HangmanGame(final String secretWord)
	{
		this.id = UUID.randomUUID();
		this.remainingMoves = Body.values().length;
		this.usedLetters = new ArrayList<Letter>();
		this.secretWord = secretWord;
		this.playingWord = new PlayingWord(secretWord);
		this.status = Status.PLAYING;
	}
	
	public HangmanGame(final HangmanBean hb)
	{
		this.id = UUID.fromString(hb.getId());
		this.remainingMoves = Integer.parseInt(hb.getRemainingMoves());
		this.usedLetters = new ArrayList<Letter>();
		String stringUsedLetters = hb.getUsedLetters();
		String[] items = stringUsedLetters.split("[\\s,]+");
		if (items.length > 2)
		{			
			for(int i = 1; i< items.length -1 ; i ++)
			{
				usedLetters.add(Letter.valueOf(items[i]));
			}
		}
		this.secretWord = hb.getSecretWord();
		this.playingWord = new PlayingWord(secretWord, usedLetters);
		this.status = Status.valueOf(hb.getStatus());
	}
	
	public Status playLetter(String letter)
	{
		if(status == Status.PLAYING)
		{	
			usedLetters.add(Letter.valueOf(letter));
				
			if(playingWord.checkLetter(letter))
			{
				if(playingWord.isDone())
				{
					status = Status.WIN;
				}
			}
			else
			{
				remainingMoves--;
			}
			
			if(status != Status.WIN )				
			{
				if(remainingMoves > 0)
				{
					status = Status.PLAYING;
				}
				else 
				{
					status = Status.GAME_OVER;
				}
			}			
		}
		
		return status;		
	}

	public UUID getId()
	{
		return id;
	}

	public void setId(UUID id)
	{
		this.id = id;
	}

	public String getRemainingMoves()
	{
		return Integer.toString(remainingMoves);
	}

	public void setRemainingMoves(int remainingMoves)
	{
		this.remainingMoves = remainingMoves;
	}

	public ArrayList<Letter> getUsedLetters()
	{
		return usedLetters;
	}

	public void setUsedLetters(ArrayList<Letter> usedLetters)
	{
		this.usedLetters = usedLetters;
	}

	public String getPlayingWord()
	{
		return playingWord.toString();
	}

	public void setPlayingWord(PlayingWord playingWord)
	{
		this.playingWord = playingWord;
	}

	public String getSecretWord()
	{
		return secretWord;
	}

	public void setSecretWord(String secretWord)
	{
		this.secretWord = secretWord;
	}

	public Status getStatus()
	{
		return status;
	}

	public void setStatus(Status status)
	{
		this.status = status;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("ID: ").append(getId()).append("\n")
		.append("RemainingMoves: ").append(getRemainingMoves()).append("\n")
		.append("Used Letters: ").append(getUsedLetters()).append("\n")
		.append("PlayingWord: ").append(getPlayingWord()).append("\n")
		.append("SecretWord: ").append(getSecretWord()).append("\n")
		.append("Status :").append(getStatus());
		
		return sb.toString();
	}
	
}
