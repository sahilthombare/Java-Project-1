import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.util.*;
import java.awt.Desktop;

class WebsiteListener extends WindowAdapter
{
	public void windowClosing(WindowEvent e)
	{
		System.exit(0);
	}	
}


class WebsiteCredentialManager 
{
	
	public WebsiteCredentialManager()   // constructor -> used in the creation of the main frame 
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
	
		lobj.setBounds(95,50,160,40);
		lobj1.setBounds(20,110,160,30);
		bobj1.setBounds(190,110,90,30);
		lobj2.setBounds(20,160,160,30);
		bobj2.setBounds(190,160,90,30);
		lobj3.setBounds(20,210,160,30);
		bobj3.setBounds(190,210,90,30);
		lobj4.setBounds(20,260,160,30);
		bobj4.setBounds(190,260,90,30);
		bobj5.setBounds(110,320,90,30);
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
		fobj.setSize(320,410);
		fobj.setLayout(null);
		fobj.setVisible(true);
		fobj.addWindowListener(new WebsiteListener());		

		 
		bobj5.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				fobj.dispose();
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
		JLabel dlobj=new JLabel("Enter the Website to Delete");
		JTextField ddelete=new JTextField();
		JButton ddboj=new JButton("DELETE");
		JButton close=new JButton("CLOSE");
			
		JTextArea notify=new JTextArea();
			
		dlobj.setBounds(20,50,170,30);
		ddelete.setBounds(200,50,170,30);
		ddboj.setBounds(150,120,80,30);
		notify.setBounds(100,190,200,50);
		close.setBounds(150,275,80,30);
			
		dobj.add(dlobj);
		dobj.add(ddelete);
		dobj.add(ddboj);
		dobj.add(notify);
		dobj.add(close);
		dobj.setSize(400,370);
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
					notify.setText("No data provided\n");
					notify.append("Please enter the site name to delete\n");
				}
				else
				{
					boolean foundAndDeleted = deleteCredentials(nameToDelete);
					if(foundAndDeleted == true)
					{
						notify.setText("Successfully deleted.");
					}
					else
					{
						notify.setText("No such website found.");
					}
				}
			}
				
		});
			
	}
	
		private boolean deleteCredentials(String websiteName)
		{
			boolean found = false;
			ArrayList <String> allLines = new ArrayList<String> ();

			try(BufferedReader reader = new BufferedReader(new FileReader("credentials.txt")))
			{
				String line;
				boolean isInfoFound = false;

				while((line = reader.readLine()) != null)
				{
					if((isInfoFound == false) && line.startsWith("Site name: " + websiteName))
					{
						isInfoFound = true;
						found = true;
					}

					if(isInfoFound == false)
					{
						allLines.add(line);
					}

					if((isInfoFound && line.isEmpty()) == true)
					{
						isInfoFound = false;
					}
            			}
       			 } 
			catch(IOException e)
			{
				System.out.println("Exception occured : "+e);
       			 }

        		if(found == true)
			{
            			try (BufferedWriter writer = new BufferedWriter(new FileWriter("credentials.txt")))
	    			{
               				 for (String line : allLines)
					 {
                    				writer.write(line + "\n");
                			 }
               				 writer.flush();
            			} 
	    			catch (IOException obj)
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
		JLabel slobj=new JLabel("Enter the Website");
		JTextField search=new JTextField();
		JButton bboj=new JButton("SEARCH");
		JButton close=new JButton("CLOSE");
		JTextArea found1=new JTextArea();
			
		slobj.setBounds(20,60,120,30);
		search.setBounds(130,60,210,30);
		bboj.setBounds(130,125,90,30);
		close.setBounds(130,275,90,30);
		found1.setBounds(30,190,275,60);
		sobj.add(slobj);
		sobj.add(search);
		sobj.add(bboj);
		sobj.add(found1);
		sobj.add(close);
		sobj.setSize(370,380);
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
					found1.setText("No data provided\n");
					found1.append("Please enter the site name to search\n");
				}
					
				else
				{
						
					try (BufferedReader reader = new BufferedReader(new FileReader("credentials.txt")))
					{
						String line;
						boolean found = false;
						while ((line = reader.readLine()) != null) 
						{
							if (line.startsWith("Site name: " + name))
							{
								found1.setText(line + "\n");
								for (int i = 0; i < 2; i++)
								{
									line = reader.readLine();
									found1.append(line + "\n");
								}
								found = true;
								break;
							}
						}
						if (found == false)
						{
							found1.setText("No such website added.");
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
            		Desktop.getDesktop().open(new File("credentials.txt"));
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
		JLabel alobj1=new JLabel("Enter website name");
		JLabel alobj2=new JLabel("Enter username");
		JLabel alobj3=new JLabel("Enter password");
		JButton abobj=new JButton("SAVE");
		JButton close=new JButton("CLOSE");
		JTextField website=new JTextField();
		JTextField username=new JTextField();
		JTextField password=new JTextField();
			
		alobj1.setBounds(20,50,130,30);
		website.setBounds(150,50,180,25);
					
		alobj2.setBounds(20,100,130,30);
		username.setBounds(150,100,180,25);
			
		alobj3.setBounds(20,150,130,30);
		password.setBounds(150,150,180,25);
					
		abobj.setBounds(70,215,90,30);
		close.setBounds(210,215,90,30);
			
		afobj.add(close);
		afobj.add(alobj1);
		afobj.add(website);
		afobj.add(alobj2);
		afobj.add(username);
		afobj.add(alobj3);
		afobj.add(password);
		afobj.add(abobj);
		afobj.setSize(380,310);
		afobj.setLayout(null);
		afobj.setVisible(true);
			
		close.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				afobj.dispose();
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
	
	
	private void saveCredentials(String name, String username, String password) 
	{
       		 try(BufferedWriter writer = new BufferedWriter(new FileWriter("credentials.txt", true)))
		{
           		writer.write("Site name: " + name + "\n");
         		writer.write("Username: " + username + "\n");
         		writer.write("Password: " + password + "\n");
           	 	writer.write("\n");
            		writer.flush();
        	} 
		catch (IOException obj) 
		{
			System.out.println("Exception occured : "+obj);
        	}
    	}



	/*
		main starter function creates the object of the class
	*/
	public static void main(String[] args) 
	{
        	WebsiteCredentialManager app = new WebsiteCredentialManager();
    	}
}
