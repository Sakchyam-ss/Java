package Final;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JFrame;

/**
 * The main driver program for the GUI based conversion program.
 * 
 * Assignment by Sakchyam Shrestha
 *  */
public class Converter {
	 
    public static void main(String[] args) {
    	
        JFrame frame = new JFrame("Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        MainPanel panel = new MainPanel();
        CurrencyPanel currencyPanel = new CurrencyPanel();
        		
        frame.setJMenuBar(panel.setupMenu());
    
        frame.getContentPane().add(panel);
        frame.getContentPane().add(currencyPanel);

        
        frame.setLayout(new GridLayout(2, 1));
        frame.pack();
        frame.setVisible(true);
        
        
        
        panel.currencyPanel = currencyPanel;
        currencyPanel.mainPanel = panel;
    }
}

