import java.awt.event.*;
import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.util.*;
import java.awt.Desktop;
import java.net.URI;


class WebsiteListener extends WindowAdapter
{
	public void windowClosing(WindowEvent e)
	{
		System.exit(0);
	}	
}


class GUI
{
	
	public GUI()   // constructor -> used in the creation of the main frame 
	 {
	   
		JFrame fobj=new JFrame("Website Info Manager");
		JLabel lobj=new JLabel("Select  your  option");
		JButton bobj1=new JButton("ADD");
		JButton bobj2=new JButton("SEARCH");
		JButton bobj3=new JButton("OPEN");
		JButton bobj4=new JButton("DELETE");
		JButton bobj5=new JButton("CLOSE");
		JLabel lobj1=new JLabel("To  add  new  website  info");
		JLabel lobj2=new JLabel("To  search  existing  info");
		JLabel lobj3=new JLabel("Open  the  file");
		JLabel lobj4=new JLabel("Delete  the  existing  info");
	
		lobj.setBounds(120,45,160,40);
		lobj1.setBounds(20,130,190,30);
		bobj1.setBounds(240,130,90,30);
		lobj2.setBounds(20,190,190,30);
		bobj2.setBounds(240,190,90,30);
		lobj3.setBounds(20,250,190,30);
		bobj3.setBounds(240,250,90,30);
		lobj4.setBounds(20,310,190,30);
		bobj4.setBounds(240,310,90,30);
		bobj5.setBounds(140,390,90,30);
		fobj.add(lobj);
		fobj.add(lobj1);
		fobj.add(bobj1);
		fobj.add(lobj2);
		fobj.add(bobj2);
		fobj.add(lobj3);
		fobj.add(bobj3);
		fobj.add(lobj4);
		fobj.add(bobj4);
		fobj.add(bobj5);
		fobj.setSize(385,490);
		fobj.setLayout(null);
		fobj.setVisible(true);
		fobj.addWindowListener(new WebsiteListener());		

		 
		bobj5.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});


		// add function
		bobj1.addActionListener(new ActionListener()
		{
            public void actionPerformed(ActionEvent e) 
			{
               	addCredentials();
           	}
        });


		// open function
		bobj3.addActionListener(new ActionListener()
		{
            public void actionPerformed(ActionEvent e) 
			{
            	openFile();
           	}
        });

		 
		// search Credentials
		bobj2.addActionListener(new ActionListener() 
		{
            public void actionPerformed(ActionEvent e) 
			{
            	searchCredentials();
          	}
        });

		 
		// delete wesite
		bobj4.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				deleteweb();
			}
			
		});

		 
}  //  end of constructor


	
	/*
	 the deleteweb() function deletes the website name given by the user 
	 from the credentials.txt file .
	 if the website is not present in the file then it will show a message in 
	 the textArea filed that there is no such website present.
	 And if present the will delete the website from the file and it will show 
	 that the Credentials are delete..
	*/

	private void deleteweb()        
	{
			JFrame dobj=new JFrame("Delete a Website");
			JLabel dlobj=new JLabel("Enter  the  Website  to  Delete");
			JTextField ddelete=new JTextField();
			JButton ddboj=new JButton("DELETE");
			JButton close=new JButton("CLOSE");
			
			JTextArea notify=new JTextArea();
			
			dlobj.setBounds(20,50,200,30);
			ddelete.setBounds(230,50,200,30);
			ddboj.setBounds(175,130,80,30);
			notify.setBounds(110,210,220,60);
			close.setBounds(175,320,80,30);
			
			dobj.add(dlobj);
			dobj.add(ddelete);
			dobj.add(ddboj);
			dobj.add(notify);
			dobj.add(close);
			dobj.setSize(480,430);
			dobj.setLayout(null);
			dobj.setVisible(true);

		
			close.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					dobj.dispose();
				}	
			});

		
			ddboj.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					String nameToDelete = ddelete.getText();
					if(nameToDelete.isEmpty())
					{
						notify.setText("  No  data  provided  \n\n");
						notify.append(" Please enter the site name to delete\n");
					}
					else
					{
						boolean foundAndDeleted = deleteCredentials(nameToDelete);
						if(foundAndDeleted == true)
						{
							notify.setText("    Successfully  Deleted  the  Website  : \n\n");
							notify.append("                   "+nameToDelete);
						}
						else
						{
							notify.setText("  No  such  website  found . \n");
							notify.append("  Please  enter  the  website  which  is  \n");
							notify.append("  Present  in  the  file  ");
						}
					}
				}
				
			});
			
	}
	
		private boolean deleteCredentials(String websiteName)
		{
			boolean found = false;  // found variable initially set as false
			ArrayList <String> allLines = new ArrayList<String> ();
			/*
				the data which is not to be deleted will be written in this datastructure.
			*/

			try
			{
				/*
					FileReader used for opening the already created file or if not then 
					creating a new file named "credentials.txt" in the reading mode
					The BufferedReader is used for reading from the file.
				*/
				FileReader fobj = new FileReader("credentials.txt");
				BufferedReader reader = new BufferedReader(fobj);
				
				String line;
				boolean isInfoFound = false;

				while((line = reader.readLine()) != null)
				{
					if((isInfoFound == false) && line.startsWith("Site name: " + websiteName))
					{
						isInfoFound = true;
						found = true; // if website found then the found variable is set true
					}

					if(isInfoFound == false)
					{
						allLines.add(line); // adding lines in the arraylist if and only 
											//isInfoFound variable is false (data that is not
					}						// to be deleted)


					if((isInfoFound && line.isEmpty()) == true)
					{						//if the isInfoFound variable is true and line 
						isInfoFound = false;//is empty that means we have sucessfully travelled
					}						//the part of the data from the file which is to
           		}							// be deleted them the isInfoFound variable is set true
       		} 
			catch(IOException e)
			{
				System.out.println("Exception occured : "+e);
       		}

				/*
					if the found variable is false that means there was no such website in the file and 
					thus will return false but if it is made true in the while loop that means the data was 
					present.
					the data which is not to be deleted is in the arraylist now is copied back to the 
					"credentials.txt" file which still has the part of the data that is to be deleted
					but as there is no second parameter "true" mentioned while creating the object of the
					FileWriter the previous data will be overridden by the new data.
					thus we are getting back the data which was not to be deleted.
				*/
				
        		if(found == true)
				{
            		try
	    			{
						
						FileWriter fobj1 = new FileWriter("credentials.txt");
						BufferedWriter writer = new BufferedWriter(fobj1);
						
               			 for (String line : allLines) 
						 {
                   			writer.write(line + "\n");
                		 }
               			 writer.flush();  //  emptying the buffer
						 allLines.clear(); // clearing all the data from the arraylist
						 
            		} 
	    			catch(IOException obj)
					{
						System.out.println("Exception occured : "+obj);
            		}
        		}

       			 return found;
    		}
					

		/*
			this function searches the username and the password for the website name 
			given by the user in the credentials.txt file
			if there is some website specified by the user then it will show the details
			if not then it will show no such website added
		*/
		private void searchCredentials() 
		{
			JFrame sobj=new JFrame("Search Website");
			JLabel slobj=new JLabel("Enter  the  Website  To  Search");
			JTextField search=new JTextField();
			JButton bboj=new JButton("SEARCH");
			JButton close=new JButton("CLOSE");
			JTextArea found1=new JTextArea();
			JButton go = new JButton("GO");
			
			slobj.setBounds(20,60,200,30);
			search.setBounds(230,60,200,30);
			bboj.setBounds(160,140,90,30);
			found1.setBounds(80,220,275,90);
			go.setBounds(380,255,60,25);
			close.setBounds(160,350,100,30);
			
			
			sobj.add(go);
			sobj.add(slobj);
			sobj.add(search);
			sobj.add(bboj);
			sobj.add(found1);
			sobj.add(close);
			sobj.setSize(470,465);
			sobj.setLayout(null);
			sobj.setVisible(true);
			
			
			close.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					sobj.dispose();
				}
			});
			
			
			bboj.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					String name = search.getText();
					if(name.isEmpty())
					{
						found1.setText("\n");
						found1.append("\tNo  data  provided \n");
						found1.append("\n");
						found1.append("       Please  enter  the  site  name  to  search\n");
					}
					
					else
					{
						
						try
						{
							String line;
							boolean found = false;
							FileReader fobj = new FileReader("credentials.txt");
							BufferedReader reader = new BufferedReader(fobj);
							while ((line = reader.readLine()) != null) 
							{
								if (line.startsWith("Site name: " + name))
								{
									found1.setText(line + "\n");
									found1.append("\n");
									for (int i = 0; i < 2; i++)
									{
										line = reader.readLine();
										found1.append(line + "\n");
										found1.append("\n");	
									}
									found = true;
									
									break;
								}
							}
							if (found == false)
							{
								found1.setText("\n\n");
								found1.append("\tNo   such   website   added  ");
							}
							if(found == true)
							{
								
								go.addActionListener(new ActionListener()
								{
									public void actionPerformed(ActionEvent e)
									{
										String op="www."+name;
										try
										{
											URI uri = new URI(op);           //Desktop class is used to interact with the  
											Desktop.getDesktop().browse(uri);//default applications such as opening a URI in the default web 
										}
										// browser.  The browse() method of the Desktop class is used to open a specified URI.
										// URI is uniform resource identifier
										// Desktop.getDesktop().browse() method to open the URI in the default web browser.
										catch(Exception eobj)
										{
											System.out.println("Exception occured : "+eobj);
										}
									}
								 });
							}
							
						}
						catch (IOException obj)
						{
							System.out.println("Exception occured : "+obj);
						}
						
					}
				}
			});
			
		}
	
	
	
		/*
			this function is used to open the credentials.txt file just for the 
			purpose of seeing the hard-copy of the file in the text format
		*/
		private void openFile() 
		{
        	try
			{	
				File obj = new File("credentials.txt");
				Desktop.getDesktop().open(obj);		
        	} 
			catch (IOException obj)
			{
            	System.out.println("Exception occured : "+obj);
        	}
    	}



		/*
			this function is used to add the websiteName , username , and password
			into the credentials.txt file 
		*/
		private void addCredentials()
		{
			JFrame afobj=new JFrame("Adding a new website");
			JLabel ss = new JLabel("EXAMPLE   :   mywebsite.com");
			JLabel alobj1=new JLabel("Enter website name");
			JLabel alobj2=new JLabel("Enter username");
			JLabel alobj3=new JLabel("Enter password");
			JButton abobj=new JButton("SAVE");
			JButton close=new JButton("CLOSE");
			JButton suggest=new JButton("SUGGEST ?");
			
			JTextField website=new JTextField();
			JTextField username=new JTextField();
			JTextArea password=new JTextArea();
			
			ss.setBounds(130,25,400,30);
			alobj1.setBounds(20,80,200,30);
			website.setBounds(220,80,200,25);
					
			alobj2.setBounds(20,140,200,30);
			username.setBounds(220,140,200,25);
			
			alobj3.setBounds(20,200,200,30);
			password.setBounds(220,200,200,25);
					
			suggest.setBounds(25,245,100,25);		
					
			abobj.setBounds(100,330,90,30);
			close.setBounds(240,330,90,30);
			
			afobj.add(ss);
			afobj.add(suggest);
			afobj.add(close);
			afobj.add(alobj1);
			afobj.add(website);
			afobj.add(alobj2);
			afobj.add(username);
			afobj.add(alobj3);
			afobj.add(password);
			afobj.add(abobj);
			afobj.setSize(470,440);
			afobj.setLayout(null);
			afobj.setVisible(true);
			
			close.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					afobj.dispose();
				}
			});
			
			suggest.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					String new_name=website.getText();
					JFrame zobj= new JFrame("Password");
					JLabel z1obj=new JLabel("Enter  your  name");
					JTextField name=new JTextField();
					
					JLabel z2obj=new JLabel("Your  Website  name  is");
					JTextArea site_name=new JTextArea(new_name);
					
					JLabel z3obj=new JLabel("Enter  1  special  symbol  eg: / * ! @ # $");
					JTextField symbol=new JTextField();
					
					JLabel z4obj=new JLabel("Enter  3  digit  number");
					JTextField number=new JTextField();
					
					JLabel password1=new JLabel("PASSWORD  IS  :");
					JTextArea ans=new JTextArea();
					
					JButton generate=new JButton("GENERATE");
					JButton copy=new JButton("COPY");
					JButton ignore=new JButton("IGNORE");
					
					z1obj.setBounds(20,40,230,30);
					name.setBounds(250,40,200,30);
					
					z2obj.setBounds(20,90,230,30);
					site_name.setBounds(250,90,200,30);
					
					z3obj.setBounds(20,140,230,30);
					symbol.setBounds(250,140,200,30);
					
					z4obj.setBounds(20,190,230,30);
					number.setBounds(250,190,200,30);
					
					generate.setBounds(170,250,100,30);
					
					password1.setBounds(60,300,200,30);
					ans.setBounds(230,300,200,30);
					
					copy.setBounds(90,360,70,30);
					
					ignore.setBounds(280,360,80,30);
					
					zobj.add(generate);
					zobj.add(z1obj);
					zobj.add(name);
					zobj.add(z2obj);
					zobj.add(site_name);
					zobj.add(z3obj);
					zobj.add(symbol);
					zobj.add(z4obj);
					zobj.add(number);
					zobj.add(password1);
					zobj.add(ans);
					zobj.add(copy);
					zobj.add(ignore);
					zobj.setSize(500,450);
					zobj.setLayout(null);
					zobj.setVisible(true);
					
					generate.addActionListener(new ActionListener ()
					{
						public void actionPerformed(ActionEvent e)
						{
							
							String str1=name.getText();
							String str2=site_name.getText();
							String str3=symbol.getText();
							String temp=number.getText();
							int ori_number=Integer.parseInt(temp);
							String second;
							String first_letter=str1.substring(0,1);
							first_letter=first_letter.toUpperCase();
							second=str1.substring(1,2);
							second=second.toLowerCase();
							first_letter=first_letter+second;
								
							str2=str2.substring(0,3);
							str2=str2.toLowerCase();
							first_letter=first_letter+str2;
							first_letter=first_letter+str3;
							int new_number=ori_number;
							int reverse=0;
							int no;
							while(new_number != 0)
							{
								no=new_number%10;
								reverse=reverse*10+no;
								new_number=new_number/10;
							}
							reverse=reverse+ori_number;
							int or=ori_number | reverse;
							or= or << 2;
							or= or | ori_number;
							first_letter=first_letter+or;								
							ans.setText(first_letter);
							
						}
					});
					
					copy.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							String add=ans.getText();
							password.setText(add);
							zobj.dispose();
						}
					});
					
					ignore.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							zobj.dispose();
						}
					});
					
					
				}
			});
			
			abobj.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					String name = website.getText();
					String usernameSet = username.getText();
					String passwordSet = password.getText();

					saveCredentials(name, usernameSet, passwordSet);
					afobj.dispose();
				}
     		});
		}
	
	/*
		this function takes the input as websitename , username and password given by the
		user . By creating the object of the FileWriter we are opening the file
		"credentials.txt" file in the write mode and by mentioning true we are specifying 
		that the origianl data should remain as it and the new data must be appended after
		the previous data i.e by writing true the previous data will not be overridden by 
		the new data. if there was no such file as "credentials.txt" previously made then the
		FileWriter will ceate a file named "credentials.txt" and then the writing stuf will be done
		and if there is a file named "credentials.txt" already made then the contents will be written
		By creating the object of the of the class BufferedWriter we are able
		to write in the file.
		flush() -> is responsile for cleaning/ emptying the buffer after writing into the file.
	*/
		private void saveCredentials(String name, String username, String password) 
		{
       		try
			{
				FileWriter fobj = new FileWriter("credentials.txt",true);
				BufferedWriter writer = new BufferedWriter(fobj);
           		writer.write("Site name: " + name + "\n");
         		writer.write("Username: " + username + "\n");
         		writer.write("Password: " + password + "\n");
           	 	writer.write("\n");
            	writer.flush();
        	} 
			catch(IOException obj) 
			{
				System.out.println("Exception occured : "+obj);
        	}
    	}
}

public class WebsiteCredentialManager 
{
	/*
		main entry point function creates the object of the class GUI
	*/
	public static void main(String[] args) 
	{
		GUI app = new GUI();
	}
}
