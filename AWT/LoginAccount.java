package AWT;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginAccount {
	static ArrayList<User> tempdb;
	static
	{
		tempdb = DataBase.db ;
	}
	JFrame frame ;
	JTextField text1 ;
	JPasswordField text2 ;
	
	public LoginAccount() {
		
		createFrame();
		createFields();
		createButtons();
	}
	public void createFrame()
	{
		frame = new JFrame("Login") ;
		
		Image icon =Toolkit.getDefaultToolkit().getImage("C:\\Users\\91909\\Desktop\\QspLogo.jpg");
		
		frame.setIconImage(icon);
		frame.setLayout(null);
		
		frame.setSize(500,300);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
	}
	
	public void createFields()
	 {
		 JLabel label1 = new JLabel("Username: ") ;
		 
		 label1.setBounds(80,50,80,20);
		 
		 frame.add(label1) ;
		 
		 JLabel label2 = new JLabel("Password: ") ;
		 
		 label2.setBounds(83,90,80,20);
		 
		 frame.add(label2) ;
		 
		 text1 = new JTextField();
		 
		 text1.setBounds(150,53,140,20);
		 frame.add(text1) ;
		 
		 text2 = new JPasswordField() ;
		 
		 text2.setBounds(150,93,140,20);
//		 
		 frame.add(text2) ;
		 
		 JLabel createAccount = new JLabel("Don't have an account?");
		 
		 createAccount.setBounds(140,160,200,20);
		 
		 frame.add(createAccount);
		 
		 createAccount.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		 
		 createAccount.addMouseListener(new MouseAdapter() {
			 
			 public void mouseClicked(MouseEvent e) 
			 {
				 CreateAccount cr = new CreateAccount() ;
			 }
		});
	 }
	
	 public void createButtons()
	 {
		 JButton button1 = new JButton("Login");
		 
		 button1.setBounds(120,130,80,20);
		 frame.add(button1);
		 
		 JButton button2 = new JButton("Cancel") ;
		 button2.setBounds(220,130,80,20);
		 frame.add(button2) ;
		 
		 
		 
		 ActionListener ac1 = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String username = text1.getText();
				
				char pass [] = text2.getPassword() ;
				// TO-DO  Validate username password 
				
				System.out.println(username);
				System.out.println(pass);
				
				
				try
				{
//					if (tempdb..equals(username)&&CreateAccount.passWord.equals(new String(pass))) {
//						JOptionPane.showMessageDialog(frame,"Login Successful!","SUCCESS!",JOptionPane.YES_OPTION);
//					}
//					else
//					{
//						JOptionPane.showMessageDialog(frame,"Incorrect Credentials!","Login Failed!",JOptionPane.ERROR_MESSAGE);
//					}
				}
				catch(NullPointerException np)
				{
					JOptionPane.showMessageDialog(frame,"Account not created!","Login Failed!",JOptionPane.ERROR_MESSAGE);
					
				}
			}
		};
		 button1.addActionListener(ac1); 
		 
	 }
	 
}