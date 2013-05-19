package com.cpcookieman.cookieirc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Main extends com.cpcookieman.common
{	
	public static final String title = "CookieIRC";
	public static final String version = "v2.0";
	public static final String buildTag = "cpcookieman@area51";
	public static final String buildNum = "0004";
	public static String configLocation;
	public static String windowTitle = title + " " + version;
	public static int width = 1024;
	public static int height = 600;
	public static GUI gui;
	public static int i;
	public static String user = "";
	public static ArrayList<ServerConnection> servers = new ArrayList<ServerConnection>();
	public static int servercounter = -1;
	protected static JFrame frame;
	
	public static void main(String[] args)
	{
		//setDebug(true);
		nativeLook();
		frame = new JFrame();
		frame.setVisible(false);
		frame.setTitle(windowTitle);
		if(getDebug())
		{
			windowTitle = windowTitle + "." + buildNum + " - " + buildTag + " - DEBUG";
			frame.setTitle(windowTitle);
		}
		print(title + " " + version);
		print("Build " + buildNum);
		print("Built by " + buildTag);
		debug("Starting " + windowTitle);
		debug("Built by " + buildTag);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		debug("Set size to " + width + "x" + height);
		frame.setLocationRelativeTo(null);
		user = getDefaultNickname();
		gui = new GUI();
		frame.add(gui);
		debug("Added GUI to Frame");
		i = gui.addTab(new Tab("CookieIRC Console", false, -1));
		gui.tabs.get(i).addMessage("Welcome to " + windowTitle + "!");
		gui.tabs.get(i).addMessage("");
		gui.tabs.get(i).addMessage(getRandomSaying());
		gui.tabs.get(i).addMessage("");
		gui.tabs.get(i).addMessage("/connect to servers");
		gui.tabs.get(i).addMessage("/join #channels");
		gui.tabs.get(i).addMessage("/msg people");
		gui.tabs.get(i).addMessage("");
		gui.tabs.get(i).addMessage("Current default nickname is " + user + "");
		gui.tabs.get(i).addMessage("/nick NewNickname on this tab to change it");
		frame.setVisible(true);
		debug("Frame has been set to visible");
		gui.tabs.get(i).input.requestFocus();
	}
	
	public static String getDefaultNickname()
	{
		configLocation = System.getProperty("user.home") + File.separator + "." + title.toLowerCase() + File.separator;
		try
		{
			FileInputStream fis = new FileInputStream(configLocation + "defaultnick"); 
			BufferedReader br = new BufferedReader(new FileReader(new File(configLocation + "defaultnick")));
			String s;
			while(true)
			{
				s = br.readLine();
				if(s == null)
				{
					br.close();
					fis.close();
					throw new FileNotFoundException();
				}
				if(!s.startsWith("#") && !s.isEmpty())
				{
					break;
				}
			}
			br.close();
			fis.close();
			return s;
		}
		catch(UnsupportedEncodingException e)
		{
			debug("The UTF-8 encoding is not supported on this system. This is very odd.");
			return System.getProperty("user.name");
		}
		catch (FileNotFoundException e)
		{
			saveDefaultNickname(System.getProperty("user.name"));
			return System.getProperty("user.name");
		}
		catch (IOException e)
		{
			saveDefaultNickname(System.getProperty("user.name"));
			return System.getProperty("user.name");
		}
	}
	
	public static boolean saveDefaultNickname(String nickname)
	{
		try
		{
			new File(configLocation).mkdirs();
			PrintWriter out = new PrintWriter(configLocation + "defaultnick");
			out.write("# This is the file that sets the default nickname for " + title + ".\n# Edit as you wish, or using /nick NewName on the " + title + " console tab.\n");
			out.write(nickname + "\n");
			out.close();
			return true;
		}
		catch (FileNotFoundException e)
		{
			debug("Incorrect permissions for user folder, could not save default nickname.");
			return false;
		}
	}
}
