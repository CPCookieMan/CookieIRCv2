package com.cpcookieman.cookieirc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.text.DefaultCaret;

import org.jibble.pircbot.User;

@SuppressWarnings("serial")
public class Tab extends javax.swing.JSplitPane
{
	public String server;
	public int serverid;
	public String title;
	public int tabIntent;
	public boolean cleared = true;
	public CommandProcessor cmdps = new CommandProcessor();
	public int action;
	// 0 is server
	// 1 is chatroom
	// 2 is PM

	public Tab(String title, boolean userlist, int selectedaction)
	{
		this.title = title;
		setName(title);
		action = selectedaction;
	jPanel1 = new javax.swing.JPanel();
    jScrollPane1 = new javax.swing.JScrollPane();
    output = new javax.swing.JTextArea();
    input = new javax.swing.JTextField();
    output.setEditable(false);
    input.addActionListener(new ActionListener()
    {
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			cmdps.process(input.getText(), tabIntent);
			input.setText("");
		}
    });
    jPanel2 = new javax.swing.JPanel();
    jScrollPane2 = new javax.swing.JScrollPane();
    jList1 = new javax.swing.JList();

    output.setColumns(20);
    output.setRows(5);
    output.setText("");
    output.setLineWrap(true);
    output.setWrapStyleWord(true);
    DefaultCaret caret = (DefaultCaret)output.getCaret();
    caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    jScrollPane1.setViewportView(output);

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 912, Short.MAX_VALUE)
        .addComponent(input)
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
    );

    setRightComponent(jPanel1);

    if(action == 1)
    {
    	Main.servers.get(serverid).getUsers(title);
    	jList1.setModel(new javax.swing.AbstractListModel() {
            User[] strings = Main.servers.get(serverid).getUsers(Main.gui.tabs.get(tabIntent).title);
            
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
    }

    
    jScrollPane2.setViewportView(jList1);

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane2, 125, 250, Short.MAX_VALUE)
    );
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
    );
    jPanel2.setSize(400, jPanel2.getHeight());
    setLeftComponent(jPanel2);
    
    if(!userlist)
    {
    	setLeftComponent(null);
    }
    input.requestFocus();
    }
	
	public void addMessage(String s)
	{
		addMessage(s, true);
	}
	
	public void addMessage(String s, boolean time)
	{
		if(cleared)
		{
			cleared = false;
		}
		else
		{
			output.append("\n");
		}
		if(time)
		{
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			String str = sdf.format(new Date());
			output.append("["+str+"] ");
		}
		output.append(s);
	}
	
	public void addMessage(String sender, String s)
	{
		addMessage(sender + "> " + s);
	}
    
	private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    javax.swing.JTextArea output;
  	protected javax.swing.JTextField input;
    
    public ServerConnection getServer()
    {
    	try
    	{
    		return Main.servers.get(serverid);
    	}
    	catch(IndexOutOfBoundsException e)
    	{
    		Main.debug(e.getMessage());
    		return null;
    	}
    }
    
	public void onAction()
    {
    	if(action == 1)
	    {
	    	Main.servers.get(serverid).getUsers(title);
	    	jList1.setModel(new javax.swing.AbstractListModel() {
	            User[] strings = Main.servers.get(serverid).getUsers(Main.gui.tabs.get(tabIntent).title);
	            
	            public int getSize() { return strings.length; }
	            public Object getElementAt(int i) { return strings[i]; }
	        });
	    }
    }

	public void tabSpecificProcess(String s)
	{
		if(s.startsWith("/nick"))
		{
			if(action == -1)
			{
				addMessage("Changing default nick to " + s.substring(6) + ".");
			}
			else
			{
				getServer().changeNick(s.substring(6));
				addMessage("Nick changed to " + s.substring(6));
			}
		}
		if(action == -1)
		{
			//NOTODO add console specific code
		}
		else if(action == 0)
		{
			//NOTODO add server specific code
		}
		else if(action == 1)
		{
			//NOTODO add room specific code
		}
		else if(action == 2)
		{
			//NOTODO add PM specific code
		}
		else
		{
			Main.debug("Unknown processing " + s);
		}
	}
}
