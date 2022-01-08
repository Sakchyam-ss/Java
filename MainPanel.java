package Final;
import java.awt.Color;


import java.awt.Dimension;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.DecimalFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.TitledBorder;

/**
 * 
 * Assignment by Sakchyam Shrestha
 */


@SuppressWarnings("serial")
public class MainPanel extends JPanel {
	
	int count;

	private final static String[] list = {"Choose Below", "inches/cm","pound/kilograms","degrees/radians","acres/hectares","miles/kilometers","yards/meters","celsius/fahrenheit"};
	private JTextField textField;

	private JLabel label;
	public JLabel counter;
	
	@SuppressWarnings("rawtypes")
	private JComboBox combo;                                             
	public JCheckBox revButton;
	public File file;
	private DefaultComboBoxModel<String> model;
	
	public CurrencyPanel currencyPanel;
	

    /*----------------------------------
     * Creating Menu
     * ---------------------------------
     */
	
	JMenuBar setupMenu() {

		//CREATING menu bar with menu options.
		
		JMenuBar MenuBar = new JMenuBar();
		JMenu MenuFile = new JMenu("File");
		JMenu MenuHelp = new JMenu("Help");
		
		//ADDING files to Menu Bar
		MenuBar.add(MenuFile);
		MenuBar.add(MenuHelp);

		//SHORTCUT KEYS for Menu Bar
		MenuFile.setMnemonic(KeyEvent.VK_F);
		MenuHelp.setMnemonic(KeyEvent.VK_H);
		
		//Creating Options for Files Menu
		JMenuItem OpenFile = new JMenuItem("Open");
		JMenuItem SaveFile = new JMenuItem("Save");
		JMenuItem ExitFile = new JMenuItem("Exit");
		
		//Adding the options into Files Menu.
		MenuFile.add(OpenFile);
		MenuFile.add(SaveFile);
		MenuFile.add(ExitFile);
		
		//SHORTCUT KEYS for File Menu Bar
		OpenFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		SaveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		ExitFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

		//Loading Images for File Menu Options
		OpenFile.setIcon(new ImageIcon("src/images/open.png"));
		SaveFile.setIcon(new ImageIcon("src/images/save.png"));
		ExitFile.setIcon(new ImageIcon("src/images/exit.png"));
				
		//Adding Tool tip to FileMenu with Description
		OpenFile.setToolTipText("Opens new file.");
		SaveFile.setToolTipText("Save Current Data");
		ExitFile.setToolTipText("Exits the Program.");
         
//----------------------------------------------------------------------------------------------------------------------------------------------------//		
//----------------------------------------------------------------------------------------------------------------------------------------------------//		
		//Creating Options for Help Menu
		JMenuItem HelpAbout = new JMenuItem("About");
		JMenuItem HelpTips = new JMenuItem("Tips");                                          //setting shortcut key for help (ctrl+H)

		//Adding the options into Help Menu.
		MenuHelp.add(HelpAbout);
		MenuHelp.add(HelpTips);

		//SHORTCUT KEYS for Help Menu Bar
		HelpAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		HelpTips.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
		
		//Loading Images for Help Menu Options
		HelpAbout.setIcon(new ImageIcon("src/images/about.png"));
		HelpTips.setIcon(new ImageIcon("src/images/tips.png"));
		
		//Adding Tool tip to Help Menu with Description
		HelpAbout.setToolTipText("Displays info about the program.");
		HelpTips.setToolTipText("Displays tips and tricks.");
						
//----------------------------------------------------------------------------------------------------------------------------------------------------//		
//----------------------------------------------------------------------------------------------------------------------------------------------------//						
		/*-----------------------------------------------------
		 *Adding an action listener for the application to exit.
		 *-----------------------------------------------------
		 */
		
		ExitFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		
			System.exit(0);
		
			}
		});
		
		
		/*----------------------------------------------------------------
		 * About dialogue box describing the purpose of the application, the author name, and copyright message.
		 * Adding ActionListner to display dialog box for about
		 * ------------------------------------------------------
		 */
		
		HelpAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
			
				JOptionPane.showMessageDialog(null, "This 2 in 1 program lets u convert a type of numerical value "
						+ "to another type and also works as a British Pound currency converter.\n "
						+ "This Program was created by Sakchyam Shrestha(c7261179)\n "
						+ "DONOT COPY/RESELL/GIVE WITHOUT PERMISSON! \n"
						+ "All Rights Reserved\n"
						, "About Us", JOptionPane.INFORMATION_MESSAGE);
			
				}
				
		});

		
		/*-------------------------------------------------------
		 * Adding ActionListner to upload file
		 * ------------------------------------------------------
		 */

		OpenFile.addActionListener(new ActionListener() {          // choose text file to upload
			
			@Override
			public void actionPerformed(ActionEvent ae) {
				
				JFileChooser fc = new JFileChooser();
				fc.showOpenDialog(null);
				file = fc.getSelectedFile();
				currencyPanel.loadFile(file);
				currencyPanel.cpcombo.setModel(model);
			}
		});
		
		

		return MenuBar;
	}

	
//----------------------------------------------------------------------------------------------------------------------------------------------------//
//----------------------------------------------------------------------------------------------------------------------------------------------------//
	
	
@SuppressWarnings({ "rawtypes", "unchecked" })
	MainPanel() {
		
		setBorder(new TitledBorder(null, "Unit conversion Panel", TitledBorder.CENTER, TitledBorder.TOP, null, null));

		ActionListener listener = new ConvertListener();

		combo = new JComboBox(list);
		combo.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		combo.setToolTipText("Drop down to choose the units for conversion");
		combo.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		combo.addActionListener(listener);                                     //Convert according to the options choose

		JLabel inputLabel = new JLabel("Enter value:");
		inputLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		final JButton convertButton = new JButton("Convert");
		convertButton.setToolTipText("Click here to convert the value");
		convertButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		convertButton.addActionListener(listener);                            //convert values when clicked

		revButton = new JCheckBox("Reverse Conversion");
		revButton.setToolTipText("Tick to convert the value in reverse");
		revButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		revButton.setToolTipText("Select to reverse the operation");
		
		
		JButton clear = new JButton("Clear");
		clear.setToolTipText("Click to reset all the value");
		clear.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		
		/*-------------------------------------------------------
		 * Adding ActionListner to clear all the fields
		 * ------------------------------------------------------
		 */
		
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				label.setText(null);
				textField.setText(null);
				counter.setText(null);
				count = 0;
				currencyPanel.textFieldCurrency.setText(null);
				currencyPanel.labelCurrency.setText(null);
				
			}
		});
		


		label = new JLabel("---");
		label.setToolTipText("The conversion value will be displayed here");
		textField = new JTextField(5);
		textField.setToolTipText("Enter the value to be converted ");
		
		add(combo);                 //conversion box
		add(inputLabel);			//Label for "Enter Value"
		add(textField);				//Field to enter value for conversion
		add(convertButton);			//Button to convert
		add(label);					//Result after conversion
		add(revButton);			//Checkbox for reverse
		
		JLabel lblCount = new JLabel("Conversion Count : ");
		lblCount.setToolTipText("The number of conversion operation will display here");
		lblCount.setForeground(Color.BLACK);
		lblCount.setFont(new Font("Times New Roman", Font.BOLD, 17));
		add(lblCount);
		
		
		count=0;
		counter = new JLabel("");
		counter.setText("null");
		counter.setToolTipText("The number of conversion operation will display here");
		
		add (counter);				//to count no. of operations
		add (clear);				//to clear or reset all fields
		
		/*--------
		 * Pressing Enter
		 */
		
		textField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_ENTER) {
						convertButton.doClick();
					}
				}
		}
				);

		setPreferredSize(new Dimension(550, 100));
		setBackground(new Color(255,255,255));
	}

	private class ConvertListener implements ActionListener {
		

		@Override
		public void actionPerformed(ActionEvent event) {

			String text = textField.getText().trim();
			
			if((event.getSource() == combo || event.getSource() == revButton) && text.isEmpty()) {
				
			}

			else if (text.isEmpty() == false) {
				
				try {
				
				double value = Double.parseDouble(text);

				// the factor applied during the conversion
				double factor = 0;

				// the offset applied during the conversion.
				double offset = 0;

				// For reverse check 
				if(revButton.isSelected()) {
					switch (combo.getSelectedIndex()) {
					
					
					case 0:
						String Alert = "Select a option.";
						JOptionPane.showMessageDialog(null, Alert);
						break;
						
					case 1: // inches/cm
						factor = 0.33978;
						break;
							
					case 2: // pound/kgs
						factor = 2.2046;
						break;
						
					case 3: // degree/radians
						factor = 57.2957795;
						break;
						
					case 4: // acres/hectares
						factor = 2.47105381;
						break;
						
					case 5: // miles/km
						factor = 0.621371192;
						break;
						
					case 6: // yards/meters
						factor = 1.0936133;
						break;
						
					case 7: // cel//fahren
						factor=1;
						offset=-273.15;
						break; 
						}
			
				}
				
				else {
				switch (combo.getSelectedIndex()) {
				
				case 0:
					String Alert = "Select a option.";
					JOptionPane.showMessageDialog(null, Alert);
					break;
				case 1:  // inches/cm
					factor = 2.54;
					break;
						
				case 2: // pound/kgs
					factor = 0.45359237;
					break;
					
				case 3: // degree/radians
					factor = 0.0174532925;
					break;
					
				case 4: // acres/hectar
					factor = 0.40468564;
					break;
					
				case 5: // miles/km
					factor = 1.609344;
					break;
					
				case 6: // yards/meters
					factor = 0.9144;
					break;
					
				case 7: // cel//fahren
					factor = 1;
					offset = 273.15;
					break;
					}
				
			}	
								
				double result = factor * value + offset;                 //formula
				
				/*------------------------------------------------
				 * limiting the conversion result to two decimal only 
				 * -----------------------------------------------
				 */
				
				DecimalFormat df = new DecimalFormat("#.##");
				result = Double.valueOf(df.format(result));

				label.setText(Double.toString(result));
				
				count++;
				counter.setText(String.valueOf(count));
			}
			

			
			/*--------------------------------------------------------
			 * if convert is pressed without entering any value
			 * -------------------------------------------------------
			 */
		
			catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Not an integer. Enter Integer value");
			}	
		}
			
			/*--------------------------------------------------------
			 * if string is entered instead of integer
			 * -------------------------------------------------------
			 */
			
			else {
				JOptionPane.showMessageDialog(null, "Value is expected in field");
				}
		
	}
		
		
}
}
