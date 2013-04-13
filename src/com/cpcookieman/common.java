package com.cpcookieman;

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class common
{
	public static String version = "1.0";
	private static boolean debug = false;
	public static ArrayList<String> debugMessages = new ArrayList<String>();
	
	public static void print(String s)
	{
		System.out.println(s);
	}
	
	public static String getCommonAPIVersion()
	{
		return version;
	}
	
	public static void messageBox(String s)
	{
		JOptionPane.showMessageDialog(null, s);
	}
	
	public static void messageBox(Component o, String s)
	{
		JOptionPane.showMessageDialog(o, s);
	}
	
	public static String inputBox(String s)
	{
		return JOptionPane.showInputDialog(s);
	}
	
	public static String inputBox(Component o, String s)
	{
		return JOptionPane.showInputDialog(o, s);
	}
	
	public static String getRandomSaying()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
		String str = sdf.format(new Date());
		if(str.equals("03/15"))
		{
			return "It's Paul's birthday today!";
		}
		else if(str.equals("04/01"))
		{
			return "You might just want to ignore everything on the internet today...";
		}
		else if(str.equals("12/25"))
		{
			return "Ho ho ho!";
		}
		else if(str.equals("01/01"))
		{
			return "Happy New Year!";
		}
		else if(str.equals("10/31"))
		{
			return "Boo!";
		}
		Random rand = new Random();
		int selection = rand.nextInt(5);
		String saying;
		switch(selection)
		{
		case 0:
			saying = "Have a nice day!";
			break;
		case 1:
			saying = "You're awesome!";
			break;
		case 2:
			saying = "Free and open source FTW";
			break;
		case 3:
			saying = "Trolls may kindly stay under their bridges.";
			break;
		case 4:
			saying = "Are you thinking what I'm thinking?";
			break;
		default:
			saying = "I wonder how this happened...";
		}
		return saying;
	}
	
	public static boolean nativeLook()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			return true;
		}
		catch(Exception ex)
		{
		  return false;
		}
	}
	
	public static void setDebug(boolean b)
	{
		if(b)
		{
			debug = true;
		}
		else
		{
			debug = false;
		}
	}
	
	public static boolean getDebug()
	{
		return debug;
	}
	
	public static void debug(String s)
	{
		debug(s, true);
	}
	
	public static void debug(String s, boolean log)
	{
		if(debug)
		{
			System.out.println("Debug: " + s);
		}
		if(log)
		{
			debugMessages.add(s);
		}
	}
	
	public static boolean sleep(int ms)
	{
		try
		{
			Thread.sleep(ms);
			return true;
		}
		catch (InterruptedException e)
		{
			debug(e.getMessage());
			return false;
		}
	}
	
	public static void flushDebugMessages()
	{
		debug("Debug message log flushed", false);
		debugMessages = new ArrayList<String>();
	}
	
	public static Object[] getDebugMessages()
	{
		return debugMessages.toArray();
	}
}
