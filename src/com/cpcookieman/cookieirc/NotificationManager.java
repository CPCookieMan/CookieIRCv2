package com.cpcookieman.cookieirc;

import com.sun.jna.Library;
import com.sun.jna.Native;

public class NotificationManager
{
	public static void postNotification(String title, String subtitle, String message)
	{
		Main.debug("Posted Notification: " + title + ", " + subtitle + ", " + message);
		try
		{
			NsUserNotificationsBridge.instance.sendNotification(title, subtitle, message, 0);
		}
		catch (UnsatisfiedLinkError e)
		{
			Main.debug("Could not load notification center libraries. Are we even on Mac?");
		}
	}
	
	interface NsUserNotificationsBridge extends Library
	{
		NsUserNotificationsBridge instance = (NsUserNotificationsBridge) Native.loadLibrary("/usr/local/lib/NsUserNotificationsBridge.dylib", NsUserNotificationsBridge.class);
		public int sendNotification(String title, String subtitle, String text, int timeoffset);
	}
}
