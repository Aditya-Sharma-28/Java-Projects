package AWT;

import java.io.Serializable;

public class User implements Serializable{

	private String name ;
	
	private String password ;
	
	private String phno ;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public User(String name, String password, String phno) {
		super();
		this.name = name;
		this.password = password;
		this.phno = phno;
	}



	public String getName()
	{
		return name ;
	}
	
	public String getPassword()
	{
		return password ;
	}
	
	public String getPhno()
	{
		return phno ;
	}
	public void setPassword(String oldPass,String newPass)
	{
		if (password==oldPass) {
			
			password = newPass ;
		}
	}

	@Override
	public String toString() {
		return "[name=" + name + ", password=" + password + ", phno=" + phno + "]";
	}

	public boolean equals(Object o)
	{
		User ip = (User)o;
		return this.password==ip.password && this.phno==ip.phno ;
	}
	
	public int hashCode()
	{
		return password.hashCode()+phno.hashCode() ;
				
	}
	
	
	
	
}