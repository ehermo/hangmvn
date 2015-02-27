package com.bitfoam.hangman.model;

/**
 * Enum Letter 
 * @author ehermo
 *
 */
public enum Letter
{
	A ("A"),
	B ("B"),
	C ("C"),
	D ("D"),
	E ("E"),
	F ("F"),
	G ("G"),
	H ("H"),
	I ("I"),
	J ("J"),
	K ("K"),
	L ("L"),
	M ("M"),
	N ("N"),
	O ("O"),
	P ("P"),
	Q ("Q"),
	R ("R"),
	S ("S"),
	T ("T"),
	U ("U"),
	V ("V"),
	W ("W"),
	X ("X"),
	Y ("Y"),
	Z ("Z"),
	NONE ("_");

	private String string;
	
	Letter(String string)
	{
		this.string = string;
	}
	
	public String getLetter()
	{
		return " " + string + " ";
	}
	public String toString()
	{
		return " " + string + " ";
	}
}
