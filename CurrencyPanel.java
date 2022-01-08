package Final;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.ComboBoxEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


/**
 * 
 *Assignment by Sakchyam Shrestha
 */

@SuppressWarnings("serial")
public class CurrencyPanel extends JPanel {

	private final static String[] currency_List = { "Japanese Yen(JPY)","Euro (EUR)", "US Dollars (USD)", "Australian Dollars (AUD)",
			"Canadian Dollars (CAD)", "South Korean Won (KRW) ","Thai Baht (THB)", "United Arab Emirates Dirham (AED)" };
	double num1, num2;
	static String symbol;
	public MainPanel mainPanel;

	public JTextField textFieldCurrency;
	public JLabel labelCurrency;
	public JComboBox<String> cpcombo;
	public JButton convertCurrencyBtn;
	public CurrencyPanel currencyPanel;
	
	
	private DefaultComboBoxModel<String> model;
	private ArrayList<String> converse_rate;
	private ArrayList<String> converse_list;
	private ArrayList<String> sym;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	CurrencyPanel() {

		setBorder(
				new TitledBorder(null, "Currency conversion Panel", TitledBorder.CENTER, TitledBorder.TOP, null, null));

		ActionListener listener = new ConvertListener();

		cpcombo = new JComboBox(currency_List);
		cpcombo.setToolTipText("Drop down to choose the units for conversion");
		cpcombo.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		cpcombo.addActionListener(listener); // Convert according to the options choose

		JLabel inputLabel = new JLabel("Enter value:");
		inputLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		final JButton convertCurrencyBtn = new JButton("Convert");
		convertCurrencyBtn.setToolTipText("Click here to convert the value");
		convertCurrencyBtn.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		convertCurrencyBtn.addActionListener(listener); // convert values when clicked

		labelCurrency = new JLabel("---");
		labelCurrency.setToolTipText("The conversion value will be displayed here");
		textFieldCurrency = new JTextField(5);
		textFieldCurrency.setToolTipText("Enter the value to be converted ");
		textFieldCurrency.addActionListener(listener);

		add(cpcombo); // conversion box
		add(inputLabel); // Label for "Enter Value"
		add(textFieldCurrency); // Field to enter value for conversion
		add(convertCurrencyBtn); // Button to convert
		add(labelCurrency); // Result after conversion

		/*--------
		 * Pressing Enter
		 */

		textFieldCurrency.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					convertCurrencyBtn.doClick();
				}
			}
		});

		setPreferredSize(new Dimension(500, 100));
		setBackground(new java.awt.Color(200, 225, 255));
	}

	private class ConvertListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			String text = textFieldCurrency.getText().trim();
			boolean checkReverse = mainPanel.revButton.isSelected();

			if (!text.isEmpty()) {

				try {

					switch (cpcombo.getSelectedIndex()) {
					case 0: // Euro
						num1 = 137.52;
						symbol = "¥";
						break;
					case 1: // Euro
						num1 = 1.09;
						symbol = "€";
						break;
					case 2:
						// American Dollar
						num1 = 1.29;
						symbol = "$";
						break;
					case 3:
						// Australian Dollar
						num1 = 1.78;
						symbol = "A$";
						break;
					case 4:
						// Canadian Dollar
						num1 = 1.70;
						symbol = "C$";
						break;
					case 5:
						// South Korean Won
						num1 = 158.95;
						symbol = "₩";
						break;
					case 6:
						// thai Baht
						num1 = 40.52;
						symbol = "฿";
						break;
					case 7:
						// united Arab Emirates Dirham
						num1 = 4.75;
						symbol = "د.إ";
						break;
					}

					
					num2 = Double.parseDouble(text);
					DecimalFormat df = new DecimalFormat("0.00");   //limits to 2 decimal

				
					if (checkReverse == false) {
						double result = num1 * num2;
						System.out.print("Direct Conversion:" + result);
						labelCurrency.setText("Result : " + symbol + df.format(result));
						mainPanel.count++;
						mainPanel.counter.setText(String.valueOf(mainPanel.count));
					}
					
					else if (checkReverse == true) {				//reverse 
						double result = num2 / num1;
						labelCurrency.setText("£" + df.format(result));
						System.out.print("Reverse Conversion:" + result);
						mainPanel.count++;
						mainPanel.counter.setText(String.valueOf(mainPanel.count));
					}
				}

			
				catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Not an integer. Enter Integer value");
				}
			}

			
			else if (text.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Value is expected in field");
			}
		}
	}

	void loadFile(File file) {
		
		
		try {
			 BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
		
			String line = in.readLine();
			converse_list = new ArrayList<String>();
			converse_rate = new ArrayList<String>();
			sym = new ArrayList<String>();
			
			model = new DefaultComboBoxModel<String>();
			String rate = "";
			String currency_symbol = "";
			
			boolean isNum = false;

			while (line != null) {
				isNum = false;

				// Process 'line' (split up)

				String[] parts = line.split(",");

				if (parts.length == 3) {
					rate = parts[1].trim().replace("\\s+", "");
					currency_symbol = parts[2].trim();

					try {
						Double.parseDouble(rate);
						isNum = true;
					} catch (Exception ex) {
						isNum = false;
						JOptionPane.showMessageDialog(null,"Unsupported File Format");
					}
				}

				if (isNum) {
					model.addElement(parts[0]);
					converse_list.add(parts[1]);
					converse_rate.add(rate);
					sym.add(currency_symbol);
					
				}

				// validate, store somewhere etc.
				line = in.readLine(); // read next line (if available)
			}

			in.close();

		} catch (Exception e) {

			String msg = e.getMessage();

			// show the message to the user!
		}
		
		/*
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
			
			cpcombo.removeAllItems();
			String line = in.readLine();
			while(line != null)
			{
				String[] parts = line.split(",");
				try {
					if(parts.length == 3) {
						
					}
			}
			
			}
		
		}*/
	}
	
}
