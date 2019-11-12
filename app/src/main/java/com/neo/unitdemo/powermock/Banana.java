package com.neo.unitdemo.powermock;

/**
 *
 */
public class Banana extends Fruit
{
	private static String COLOR = "黄色";
	public Banana(){}
	
	public static String getCOLOR()
	{
		return COLOR;
	}
	
	public String getBananaInfo(){
		return flavor() + getCOLOR();
	}
	
	private String flavor()
	{
		return "甜甜的";
	}
	
	public final boolean isLike(){
		return true;
	}
}
