package AWT;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CreateAccount {
	
	static ArrayList<User> tempdb;
	static
	{
		tempdb = DataBase.db ;
	}
	JFrame frame ;
	JTextField name;
	JPasswordField password ;
	JTextField phno ;
	public CreateAccount() {
		
		createFrame();
		createFields();
		createButtons();
	}

	 public void createFrame()
	 {
		 frame = new JFrame("Create Account") ;
		 frame.setLayout(null);
		 
		 Image logo = Toolkit.getDefaultToolkit().getImage("C:\\Users\\91909\\Desktop\\QspLogo.jpg") ;
		 
		 frame.setIconImage(logo);
		 
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
		 
		 name = new JTextField();
		 
		 name.setBounds(150,53,140,20);
		 frame.add(name) ;
		 
		 password = new JPasswordField() ;
		 
		 password.setBounds(150,93,140,20);
//		 
		 frame.add(password) ;
		 
		 JLabel label3 = new JLabel("Phone Number: ") ;
		 
		 label3.setBounds(53,130,120,20);
		 
		 frame.add(label3);
		 phno = new JTextField() ;
		 
		 phno.setBounds(150,133,140,20);
		 
		 frame.add(phno) ;
		 
		 
		 
	 }
	 
	 public void createButtons()
	 {
		 JButton button1 = new JButton("Submit");
		 
		 button1.setBounds(120,180,80,20);
		 frame.add(button1);
		 
		 JButton button2 = new JButton("Cancel") ;
		 button2.setBounds(220,180,80,20);
		 frame.add(button2) ;
		 
		 button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				
			}
		});
		 
		 ActionListener ac1 = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String username = name.getText();
				
				char pass [] = password.getPassword() ;
				// TO-DO  Validate username password 
				
				String ph = phno.getText() ;
				System.out.println(username);
				System.out.println(pass);
				System.out.println(ph);
				
				if (validateUserName(username)) {
				
					if (validatePassword(pass)) {
						
						
						if (validatePhno(ph)) {
							
							if(DataBase.addUser(username, ph,new String(pass)))
							{
								writeDB(DataBase.db);
								
								System.out.println(DataBase.db);
								JOptionPane.showMessageDialog(frame,
										"Account Created Successfully!","SUCCESS",
										JOptionPane.YES_OPTION);
								
								frame.dispose();
							}
							else
							{
								JOptionPane.showMessageDialog(frame,
										"Account Already Exists!","Failed",
										JOptionPane.ERROR_MESSAGE);
							}
							
						}
						else
						{
							JOptionPane.showMessageDialog(frame,"Invalid Phone Number",
									"ERROR",JOptionPane.ERROR_MESSAGE);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(frame,"Invalid Password",
								"ERROR",JOptionPane.ERROR_MESSAGE);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(frame,"INVALID USERNAME!"
							,"ERROR",JOptionPane.ERROR_MESSAGE);;
				}
				
				
				
			}
		};
		 button1.addActionListener(ac1); 
		 
	 }
	 
	 
	 public boolean validateUserName(String name)
	 {
		 for (int i = 0; i < name.length(); i++) {
			
			 char ch = name.charAt(i) ;
			 if (!(ch>='a' && ch<='z' || ch>='A'&&ch<='Z')) {
				
				 return false ;
			}
		}
		 
		 return true ;
	 }
	 
	 public boolean validatePassword(char [] pass)
	 {
		 boolean spl ,upper,lower,digit ;
		 
		 spl=upper=lower= digit=false ;
		 
		 if (pass.length>=8) {
			 
			 for (int i = 0; i < pass.length; i++) {
				
				 char ch = pass[i] ;
				 
				 if (ch>='a'&&ch<='z') {
					
					 lower = true ;
				}
				 else if (ch>='A'&&ch<='Z') {
					upper=true;
				}
				 else if (ch>='0' && ch<='9') {
					digit = true ;
				}
				 else
				 {
					 spl = true ;
				 }
				 
				 if (upper&&lower&&digit&&spl) {
					
					 break ;
				}
			}
			 
			 return upper&&lower&&digit&&spl ;
			
		}
		 
		 return false ;
	 }
	 
	 public boolean validatePhno(String no)
	 {
		 if (no.length()==10) {
			char ch = no.charAt(0);
			 if (ch=='9'||ch=='8'||ch=='7'||ch=='6') {
				
				 return true ;
			}
			 
		}
		 return false ;
	 }
	 
	 public boolean writeDB(ArrayList<User> al)
	 {
		 try (FileOutputStream fOut = new FileOutputStream("D:\\filehandle\\UserDB1.txt")){
			
			 ObjectOutputStream oOut = new ObjectOutputStream(fOut) ;
			 
			 oOut.writeObject(al);
			 
			 return true ;
			 
			 
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame,
					"Account could not be created!","Failure",
					JOptionPane.ERROR_MESSAGE);
			
			return false ;
			
		}
	 }
	 
	 public static void main(String[] args) {
		new CreateAccount();
	}
}