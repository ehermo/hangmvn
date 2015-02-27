package com.bitfoam.hangman.model;


import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = { "id", "remainingMoves", "usedLetters", "playingWord","secretWord","status" })
public class HangmanBean implements Serializable
{
	/**
	 * 
	 */
	static final long serialVersionUID = -7026664422678579136L;
	String id;
	String remainingMoves;
	String usedLetters;
	String playingWord;
	String secretWord;
	String status;
	
	public HangmanBean()
	{
		
	}
	
	public HangmanBean(HangmanGame hg)
	{
		this.id = hg.getId().toString();
		this.remainingMoves = hg.getRemainingMoves();
		this.usedLetters = hg.getUsedLetters().toString();
		this.playingWord = hg.getPlayingWord();
		this.secretWord = hg.getSecretWord();
		this.status = hg.getStatus().toString();
	}
	
	public String getId()
	{
		return id;
	}

	public String getRemainingMoves()
	{
		return remainingMoves;
	}

	public String getUsedLetters()
	{
		return usedLetters;
	}

	public String getPlayingWord()
	{
		return playingWord;
	}

	public String getSecretWord()
	{
		return secretWord;
	}

	public String getStatus()
	{
		return status;
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

	public void setId(String id)
	{
		this.id = id;
	}

	public void setRemainingMoves(String remainingMoves)
	{
		this.remainingMoves = remainingMoves;
	}

	public void setUsedLetters(String usedLetters)
	{
		this.usedLetters = usedLetters;
	}

	public void setPlayingWord(String playingWord)
	{
		this.playingWord = playingWord;
	}

	public void setSecretWord(String secretWord)
	{
		this.secretWord = secretWord;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

}
