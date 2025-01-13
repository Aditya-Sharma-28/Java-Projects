package AWT;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class DataBase {
	
	static ArrayList<User>  db = new ArrayList<User>() ;
	static
	{
		FileInputStream fin;
		try {
			fin = new FileInputStream("D:\\filehandle\\UserDB1.txt");
			ObjectInputStream Oin = new ObjectInputStream(fin) ;
			
			db = (ArrayList<User>)Oin.readObject() ;
			
			System.out.println("Data loaded");
		} catch (FileNotFoundException e) {
			
			System.out.println("File not found");
		}
		catch(IOException oi)
		{
			System.out.println("No objects in the file");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	 
	 public DataBase() {
		// TODO Auto-generated constructor stub
	}
	 
	 public static boolean addUser(String userName,String phno,String pass)
	 {
		 User obj = new User(userName, pass, phno) ;
		 
		 if (!containsUser(db,obj)) {
			db.add(obj) ;
			System.out.println(db);
			return true ;
		}
		 return false ;
		 
	 }
	 
	 public static boolean containsUser(ArrayList<User> al, User obj)
	 {
		 for (User user : al) {
			
			 if (user.equals(obj)) {
				
				 return true ;
			}
		}
		 
		 return false ;
	 }

}