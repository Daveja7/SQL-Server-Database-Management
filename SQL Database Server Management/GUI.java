/*
name:
Class: CNT4714
Date: 
*/

package project3;

import java.sql.*;
import java.awt.Color;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

 
    public class GUI extends javax.swing.JFrame {
	private static final long serialVersionUID = 1L;
	

	
	// Variables established for connection
	String [] driverSet = new String[] { "com.mysql.cj.jdbc.Driver" };
	String [] dbSet = new String[] { "jdbc:mysql://localhost:3306/project3?useTimezone=true&serverTimezone=UTC"};
	private javax.swing.JButton clearCommand;
	private javax.swing.JButton clearResultsText;
	private javax.swing.JButton connectDatabase;
	private javax.swing.JLabel connectionStatus;
	@SuppressWarnings("rawtypes") 
        
	private javax.swing.JComboBox databaseCombo;
	private javax.swing.JPanel databaseInfo;
	private javax.swing.JLabel databaseURL;
	@SuppressWarnings("rawtypes") 
        
	private javax.swing.JComboBox driverCombo;
	private javax.swing.JButton executeSQL;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JLabel jdbcDriver;
	private javax.swing.JLabel password;
	private javax.swing.JPasswordField passwordField;

	private javax.swing.JPanel sqlExecution;
	private javax.swing.JPanel sqlcommand;
	private javax.swing.JLabel username;
	private javax.swing.JTextField usernameField;
	
	private javax.swing.JTable resultTable;
	private javax.swing.JTextArea sqlCommandArea;
	private QueryTable tableModel;

	@SuppressWarnings({ "rawtypes", "unchecked" })
        
	public GUI()  {
		

		jdbcDriver = new javax.swing.JLabel();
		username = new javax.swing.JLabel();
		password = new javax.swing.JLabel();
		databaseURL = new javax.swing.JLabel();
		connectionStatus = new javax.swing.JLabel();
		
		driverCombo = new javax.swing.JComboBox();
		databaseCombo = new javax.swing.JComboBox();

		usernameField = new javax.swing.JTextField();
		passwordField = new javax.swing.JPasswordField();
		sqlCommandArea = new javax.swing.JTextArea();

		jScrollPane1 = new javax.swing.JScrollPane();
		jScrollPane2 = new javax.swing.JScrollPane();

		sqlExecution = new javax.swing.JPanel();
		sqlcommand = new javax.swing.JPanel();
		databaseInfo = new javax.swing.JPanel();

		resultTable = new JTable();
		connectDatabase = new javax.swing.JButton();
		clearResultsText = new javax.swing.JButton();
		clearCommand = new javax.swing.JButton();
		executeSQL = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);


		jdbcDriver.setText("Drivers");
		driverCombo.setModel(new javax.swing.DefaultComboBoxModel(driverSet));
		databaseURL.setText("Database URL");
		databaseCombo.setModel(new javax.swing.DefaultComboBoxModel(dbSet));
		// User name and password taken in here
        username.setText("Username");
		password.setText("Password");
		usernameField.setToolTipText("");
		connectionStatus.setText("No Connection");
		connectionStatus.setForeground(Color.red);
		connectDatabase.setText("Connect to Selected Database");
		connectDatabase.setToolTipText("");
		connectDatabase.addActionListener(new java.awt.event.ActionListener() {
                    
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					makeConnection(evt);
				} 
                                
                                catch (ClassNotFoundException e) {
					e.printStackTrace();
				} 
                                
                                catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
                
		//Setting for GUI arragement
		javax.swing.GroupLayout databaseInfoLayout = new javax.swing.GroupLayout(databaseInfo);
		databaseInfo.setLayout(databaseInfoLayout);
		databaseInfoLayout.setHorizontalGroup(
				databaseInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, databaseInfoLayout.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(connectDatabase)
						.addContainerGap())
						.addGroup(databaseInfoLayout.createSequentialGroup()
								.addContainerGap()
								.addGroup(databaseInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(databaseInfoLayout.createSequentialGroup()
												.addGroup(databaseInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
														.addGroup(javax.swing.GroupLayout.Alignment.LEADING, databaseInfoLayout.createSequentialGroup()
																.addComponent(username)
																.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
																.addGroup(javax.swing.GroupLayout.Alignment.LEADING, databaseInfoLayout.createSequentialGroup()
																		.addGroup(databaseInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(jdbcDriver)
																				.addComponent(databaseURL))
																				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																				.addGroup(databaseInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
																						.addComponent(databaseCombo, 0, 240, Short.MAX_VALUE)
																						.addComponent(driverCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
																						.addGap(0, 0, Short.MAX_VALUE))
																						.addGroup(databaseInfoLayout.createSequentialGroup()
																								.addGroup(databaseInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																										.addComponent(connectionStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																										.addGroup(databaseInfoLayout.createSequentialGroup()
																												.addComponent(password)
																												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																												.addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
																												.addGap(0, 0, Short.MAX_VALUE)))
																												.addContainerGap())))
				);

		databaseInfoLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {databaseCombo, driverCombo, passwordField, usernameField});

		databaseInfoLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {databaseURL, jdbcDriver, password, username});

		databaseInfoLayout.setVerticalGroup(
				databaseInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(databaseInfoLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(databaseInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jdbcDriver)
								.addComponent(driverCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(databaseInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(databaseCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(databaseURL))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(databaseInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(username)
												.addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addGroup(databaseInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(password)
														.addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
														.addComponent(connectionStatus)
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
														.addComponent(connectDatabase)
														.addContainerGap())
				);
		sqlCommandArea.setColumns(20);
		sqlCommandArea.setRows(5);
		jScrollPane1.setViewportView(sqlCommandArea);
		executeSQL.setText("Execute SQL Command");
		executeSQL.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                            
				try {
					runSQL(evt);
				} 
                                
                                catch (ClassNotFoundException e) {
                                    
					e.printStackTrace();
				} 
                                
                                catch (SQLException e) {
                                    
					e.printStackTrace();
				}
			}
		});

		clearCommand.setText("Clear Command");
		clearCommand.addActionListener(new java.awt.event.ActionListener() {
                    
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                            
				clearSqlText(evt);
			}
		});

		javax.swing.GroupLayout sqlcommandLayout = new javax.swing.GroupLayout(sqlcommand);
		sqlcommand.setLayout(sqlcommandLayout);
		sqlcommandLayout.setHorizontalGroup(
				sqlcommandLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(sqlcommandLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(sqlcommandLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jScrollPane1)
								.addGroup(sqlcommandLayout.createSequentialGroup()
										.addGap(0, 172, Short.MAX_VALUE)
										.addComponent(executeSQL)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(clearCommand)))
										.addContainerGap())
				);
		sqlcommandLayout.setVerticalGroup(
				sqlcommandLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(sqlcommandLayout.createSequentialGroup()
						.addComponent(jScrollPane1)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(sqlcommandLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(executeSQL)
								.addComponent(clearCommand))
								.addContainerGap())
				);

		jScrollPane2.setViewportView(resultTable);
		javax.swing.GroupLayout sqlExecutionLayout = new javax.swing.GroupLayout(sqlExecution);
		sqlExecution.setLayout(sqlExecutionLayout);
		sqlExecutionLayout.setHorizontalGroup(
				sqlExecutionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(sqlExecutionLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jScrollPane2)
						.addContainerGap())
				);
		sqlExecutionLayout.setVerticalGroup(
				sqlExecutionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(sqlExecutionLayout.createSequentialGroup()
						.addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, Short.MAX_VALUE))
				);

		clearResultsText.setText("Clear Results Window");
		clearResultsText.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				clearResultsText(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
								.addComponent(sqlExecution, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addComponent(databaseInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(sqlcommand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addComponent(clearResultsText)))
												.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(databaseInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(sqlcommand, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(sqlExecution, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(clearResultsText)
								.addContainerGap())
				);

		pack();
	}
        //Elements end 

	@SuppressWarnings("deprecation")
        // Connection is to be made by server here
	private void makeConnection(java.awt.event.ActionEvent evt) throws SQLException, ClassNotFoundException {                                                
		try{
			tableModel = new QueryTable( driverCombo.getSelectedItem().toString(), 
					databaseCombo.getSelectedItem().toString(),
					usernameField.getText(), 
					passwordField.getText(), 
					sqlCommandArea.getText());
			connectionStatus.setText("Connected to " + databaseCombo.getSelectedItem().toString() );
			connectionStatus.setForeground(Color.green);
		} 
                
                catch (SQLException e){
			JOptionPane.showMessageDialog(null, "Could not connect to SQL Database.");
			System.out.println("Could not connect");
		}
	}                                             

	private void runSQL(java.awt.event.ActionEvent evt) throws ClassNotFoundException, SQLException { 
		try 
		{
			tableModel.setQuery( sqlCommandArea.getText() );
			resultTable.setModel(tableModel);
		} 
                
		catch ( SQLException sqlException ) 
		{
			JOptionPane.showMessageDialog( null, 
					sqlException.getMessage(), "ERROR", 
					JOptionPane.ERROR_MESSAGE );
		}
	}                                          

	// Command to clear Script
	private void clearSqlText(java.awt.event.ActionEvent evt) {
            
		sqlCommandArea.setText("");
	}                                            
        
	private void clearResultsText(java.awt.event.ActionEvent evt) { 
            
		resultTable.setModel(new DefaultTableModel());
	}

	public static void main(String args[]) {
	
		new GUI().setVisible(true);
	}

}
