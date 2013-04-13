package com.cpcookieman.cookieirc;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class GUI extends javax.swing.JPanel
{
	protected ArrayList<Tab> tabs = new ArrayList<Tab>();
	private int tabcounter = -1;
	
    public GUI()
    {
        initComponents();
    }
    
    public int addTab(Tab tab, boolean switchTo)
    {
    	tabs.add(tab);
    	jTabbedPane1.add(tab);
    	tabcounter++;
    	tab.tabIntent = tabcounter;
    	Main.debug("Tab " + tabcounter + " added");
    	try
        {
    		if(switchTo)
    		{
    			Main.gui.jTabbedPane1.setSelectedComponent(tab);
    			tab.input.requestFocus();
    		}
        }
        catch(IllegalArgumentException e)
        {
        	Main.debug("Warning, illegal argument exception switching tabs.", false);
        }
    	return tabcounter;
    }
    
    public int addTab(Tab tab)
    {
    	return addTab(tab, true);
    }
                   
    private void initComponents()
    {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        setPreferredSize(new java.awt.Dimension(1024, 600));

        jTabbedPane1.getAccessibleContext().setAccessibleName("CookieIRC");
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }
    
    protected javax.swing.JTabbedPane jTabbedPane1;
    
}

