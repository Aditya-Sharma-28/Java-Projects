package Notepad;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NotePad{//class declaration
	JFrame frame;
	JTextArea textArea;
	JScrollPane scroll;
	JMenuBar menuBar;
	JMenu fileMenu,editMenu,formatMenu,languageMenu,commandPromptMenu;
	JMenuItem itemNew,itemNewWindow,itemOpen,itemSaveAs,itemSave,itemExit,itemWordWrap,itemFont,itemFontSize,itemJava,itemHtml,itemCpp,openCmd;
	FileDialog fd;
	String openFileName = null;
	String openPath = null;
	Font arial,newRoman,consolas;
	String fontStyle="Arial";
	boolean wrap = false;
	
	public NotePad(){//constructor
		createFrame();
		createTextArea();
		createScrollBar();
		createMenuBar();
		createFileMenuItems();
		createFormatMenuItems();
		createLanguageMenuitem();
		createComandPrompt();
		}
	
	public void createFrame() {//creating the basic frame of the project
		frame = new JFrame("Notepad");
	    frame.setLayout(new BorderLayout()); // Use BorderLayout for automatic resizing
	    frame.setVisible(true);
	    frame.setLocationRelativeTo(null);
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    frame.setSize(500, 500);
	}
	public void createTextArea() {//creating the writing area for note pad
		textArea = new JTextArea();
		frame.add(textArea);
		textArea.setFont(new Font("Arial",Font.PLAIN,20));
	}
	public void createScrollBar() { 
		scroll = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		frame.add(scroll);
	}
	public void createMenuBar() {//creating horizontal menu bar
		menuBar = new JMenuBar();//creating menu bar object
		frame.setJMenuBar(menuBar);//adding menu bar to the frame
		
		//creating the menu bar options
		fileMenu = new JMenu("File");//creating file option to the bar and giving it a name
		menuBar.add(fileMenu);//adding to the menu bar
		editMenu = new JMenu("Edit");
		menuBar.add(editMenu);
		formatMenu = new JMenu("Format");
		menuBar.add(formatMenu);
		languageMenu = new JMenu("Language");
		menuBar.add(languageMenu);
		commandPromptMenu = new JMenu("Command Prompt");
		menuBar.add(commandPromptMenu);
	}
	public void createFileMenuItems() {//creating file option's item
		itemNew = new JMenuItem("New");//creating file item with a name of New
		fileMenu.add(itemNew);
		itemNewWindow = new JMenuItem("New Window");
		fileMenu.add(itemNewWindow);
		itemOpen = new JMenuItem("Open");
		fileMenu.add(itemOpen);
		itemSaveAs = new JMenuItem("Save As");
		fileMenu.add(itemSaveAs);
		itemSave = new JMenuItem("Save");
		fileMenu.add(itemSave);
		itemExit = new JMenuItem("Exit");
		fileMenu.add(itemExit);
		
		//adding functionality for the menu item
		itemNew.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");//it just create a new frame on the existing frame
				frame.setTitle("Untitled");
				openPath = null;
				openFileName = null;
				
			}
		});
		itemNewWindow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				NotePad n1 = new NotePad();//it will call the constructor so the new frame can open
				n1.frame.setTitle("Untitled");
				
			}
		});
		itemExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();//by clicking on exit it will close the frame
				
			}
		});
		itemOpen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fd = new FileDialog(frame,"Open File",FileDialog.LOAD);//it opens the file selecting window from windows
				fd.setVisible(true);
				String fileName = fd.getFile();//fetching file name
				String path = fd.getDirectory();//fetching file path
				if (fileName != null) { // Checking if fileName is not null
				    textArea.setText(""); // Clear existing text
				    openPath = path; // Set the openPath variable
				    frame.setTitle(fileName); // Update frame title

				    try (BufferedReader br = new BufferedReader(new FileReader(path + fileName))) {
				        String sentence = br.readLine(); // Read the first line
				        while (sentence != null) { // Loop until no more lines are available
				            textArea.append(sentence + "\n"); // Append the line to textArea
				            sentence = br.readLine(); // Read the next line
				        }
				    } catch (FileNotFoundException e1) {
				        System.out.println("File not found");
				    } catch (IOException e1) {
				        System.out.println("Error reading the file");
				    } catch (NullPointerException npe) {
				        // Empty catch block for NullPointerException (not recommended)
				    }
				}				
			}
		});
		itemSaveAs.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fd = new FileDialog(frame,"Save As",FileDialog.SAVE);
				fd.setVisible(true);
				String fileName = fd.getFile();
				String path = fd.getDirectory();
				if(fileName!=null && path!=null) {
					writeDataToFile(fileName,path);
				}
				
			}
		});
		itemSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(openFileName!=null && openPath!=null) {
					writeDataToFile(openFileName,openPath);
				}
				else {
					fd = new FileDialog(frame,"Save As",FileDialog.SAVE);
					fd.setVisible(true);
					String fileName = fd.getFile();
					String path = fd.getDirectory();
					if(fileName!=null && path!=null) {
						writeDataToFile(fileName,path);
					}
				}
			}
		});
	}
	public void createFormatMenuItems() {//creating format option's items
		itemWordWrap = new JMenuItem("Word wrap off");
		formatMenu.add(itemWordWrap);
		itemFont = new JMenuItem("Font");
		formatMenu.add(itemFont);
		itemFontSize = new JMenuItem("Font Size");
		formatMenu.add(itemFontSize);
		
		// Add an ActionListener to the Word Wrap menu item
		itemWordWrap.addActionListener(new ActionListener() {
		    
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Check if word wrapping is currently disabled
		        if (!wrap) {
		            // Enable word wrapping
		            textArea.setLineWrap(true); // Lines will wrap at the edge of the text area
		            textArea.setWrapStyleWord(true); // Wraps lines at word boundaries instead of splitting words
		            itemWordWrap.setText("Word Wrap off"); // Update menu item text to indicate the current state
		            wrap = true; // Update the wrap state
		        } else {
		            // Disable word wrapping
		            textArea.setLineWrap(false); // Disable line wrapping
		            textArea.setWrapStyleWord(false); // Word boundaries wrapping no longer applies
		            itemWordWrap.setText("Word Wrap on"); // Update menu item text to indicate the current state
		            wrap = false; // Update the wrap state
		        }
		    }
		});
		
		itemFont = new JMenu("Font");//creating font item as menu(menu in menu)
		formatMenu.add(itemFont);
		
		JMenuItem itemArial = new JMenuItem("Arial");
		itemFont.add(itemArial);
		JMenuItem itemTimesNewRoman = new JMenuItem("Times New Roman");
		itemFont.add(itemTimesNewRoman);
		JMenuItem itemConlas = new JMenuItem("Consolas");
		itemFont.add(itemConlas);
		
		itemArial.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setFontStyle("Arial");
				
			}
		});
		itemTimesNewRoman.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setFontStyle("Times New Roman");
				
			}
		});
		itemConlas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setFontStyle("Consoles");
				
			}
		});
		
		itemFontSize = new JMenu("Font size");//creating font size item as menu(menu in menu)
		formatMenu.add(itemFontSize);
		
		JMenuItem size10 = new JMenuItem("10");//creating font size items to select from
		itemFontSize.add(size10);
		JMenuItem size12 = new JMenuItem("12");
		itemFontSize.add(size12);
		JMenuItem size14 = new JMenuItem("14");
		itemFontSize.add(size14);
		JMenuItem size16 = new JMenuItem("16");
		itemFontSize.add(size16);
		JMenuItem size18 = new JMenuItem("18");
		itemFontSize.add(size18);
		JMenuItem size20 = new JMenuItem("20");
		itemFontSize.add(size20);
		JMenuItem size24 = new JMenuItem("24");
		itemFontSize.add(size24);
		JMenuItem size28 = new JMenuItem("28");
		itemFontSize.add(size28);
		JMenuItem size32 = new JMenuItem("32");
		itemFontSize.add(size32);
		JMenuItem size36 = new JMenuItem("36");
		itemFontSize.add(size36);
		JMenuItem size40 = new JMenuItem("40");
		itemFontSize.add(size40);
		
		//adding functionality to the font size items
		size10.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setFontSize(10);	
			}
		});
		size12.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setFontSize(12);	
			}
		});
		size14.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setFontSize(14);	
			}
		});
		size16.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setFontSize(16);	
			}
		});
		size18.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				setFontSize(18);	
			}
		});
		size20.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				setFontSize(20);	
			}
		});
		size24.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				setFontSize(24);	
			}
		});
		size28.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				setFontSize(28);	
			}
		});
		size32.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				setFontSize(32);	
			}
		});
		size36.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				setFontSize(36);	
			}
		});
		size10.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				setFontSize(40);	
			}
		});
		
	}
	public void createLanguageMenuitem() {//creating languages option's item
		itemJava = new JMenuItem("Java");
		languageMenu.add(itemJava);
		itemHtml = new JMenuItem("HTML");
		languageMenu.add(itemHtml);
		itemCpp = new JMenuItem("C++");
		languageMenu.add(itemCpp);
		
		itemJava.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				NotePad n1 = new NotePad();//it will call the constructor so the new frame can open
				n1.frame.setTitle("Java Boilerplate Code");
				String javaBoilerplate = """
	                    public class Main {
	                        public static void main(String[] args) {
	                            System.out.println("Hello, World!");
	                        }
	                    }
	                    """;
	            n1.textArea.setText(javaBoilerplate);
				
			}
		});
		itemHtml.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	NotePad n1 = new NotePad();//it will call the constructor so the new frame can open
				n1.frame.setTitle("HTML Boilerplate Code");
	            String htmlBoilerplate = """
	                    <!DOCTYPE html>
	                    <html>
	                    <head>
	                        <title>My HTML Page</title>
	                    </head>
	                    <body>
	                        <h1>Hello, World!</h1>
	                    </body>
	                    </html>
	                    """;
	            n1.textArea.setText(htmlBoilerplate);
	        }
	    });
		itemCpp.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	NotePad n1 = new NotePad();//it will call the constructor so the new frame can open
				n1.frame.setTitle("C++ Boilerplate Code");
	            String cppBoilerplate = """
	                    #include <iostream>
	                    
	                    int main() {
	                        std::cout << "Hello, World!" << std::endl;
	                        return 0;
	                    }
	                    """;
	            n1.textArea.setText(cppBoilerplate);
	        }
	    });
	}
	public void createComandPrompt()
	{
		openCmd = new JMenuItem("Open Command prompt");
		commandPromptMenu.add(openCmd);
		
openCmd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					if(openPath != null)
					{
						Runtime.getRuntime().exec(new String[] {"cmd","/k","start"},null,new File(openPath));
					}
					else {
						Runtime.getRuntime().exec(new String[] {"cmd","/k","start"},null,null);
					}
				} catch (IOException e1) {
					System.out.println("Couldn't lunch cmd");
				}
				
			}
		});
	}
	public void writeDataToFile(String fileName,String path) {
		openFileName = fileName;
		openPath = path;
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path+fileName))){
			String text = textArea.getText();
			bw.write(text);
			frame.setTitle(fileName);
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e1) {
			System.out.println("File write success");
		}
	}
	public void setFontSize(int size) {
		arial = new Font("Arial", Font.PLAIN, size);
		newRoman = new Font("Times New Roman",Font.PLAIN,size);
		consolas = new Font("Consolas",Font.PLAIN,size);
		setFontStyle(fontStyle);
	}
	public void setFontStyle(String font) {
		fontStyle=font;
		switch(font)
		{
			case "Arial": {
				textArea.setFont(arial);
				break;
			}
			case "Times New Roman": {
				textArea.setFont(newRoman);
				break;
			}
			case "Consolas": {
				textArea.setFont(consolas);
				break;
			}
		}
	}
}