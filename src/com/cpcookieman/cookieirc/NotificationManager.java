package com.cpcookieman.cookieirc;

import com.sun.jna.Library;
import com.sun.jna.Native;

public class NotificationManager
{
	public static void postNotification(String title, String subtitle, String message, int tab)
	{
		try
		{
			NsUserNotificationsBridge.instance.sendNotification(title, subtitle, message, 0);
		}
		catch (UnsatisfiedLinkError e)
		{
			Main.debug("Could not post to the OSX Notification Center.");
			Main.gui.tabs.get(tab).addMessage("[NOTIFY] " + title + ": " + subtitle + ", " + message);
		}
		Main.debug("Posted Notification: " + title + ", " + subtitle + ", " + message);
	}
	
	// This controls the OS X dynamic library that talks to the Notification Center.
	interface NsUserNotificationsBridge extends Library
	{
		// Thanks to petesh (GitHub) for this library.
		NsUserNotificationsBridge instance = (NsUserNotificationsBridge) Native.loadLibrary("/usr/local/lib/NsUserNotificationsBridge.dylib", NsUserNotificationsBridge.class);
		public int sendNotification(String title, String subtitle, String text, int timeoffset);
	}
}
