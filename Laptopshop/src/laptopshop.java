import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class laptopshop {

	private JFrame frame;
	private JTextField lpname;
	private JTextField modelname;
	private JTextField pricename;
	private JTextField lpid;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					laptopshop window = new laptopshop();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public laptopshop() {
		initialize();
		Connect();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
 
	 public void Connect()
	    {
	        try {
	        	Class.forName("com.mysql.cj.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost/laptopshop", "root","");
	        }
	        catch (ClassNotFoundException ex) 
	        {
	          ex.printStackTrace();
	        }
	        catch (SQLException ex) 
	        {
	        	   ex.printStackTrace();
	        }
 
	    }
	 
	 public void table_load()
	    {
	    	try 
	    	{
		    pst = con.prepareStatement("select * from booktable");
		    rs = pst.executeQuery();
		    table_1.setModel(DbUtils.resultSetToTableModel(rs));
		} 
	    	catch (SQLException e) 
	    	 {
	    		e.printStackTrace();
		  } 
	    }



	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 705, 481);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblLaptopShop = new JLabel("Laptop Shop");
		lblLaptopShop.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblLaptopShop.setBounds(270, 11, 164, 47);
		frame.getContentPane().add(lblLaptopShop);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Registration", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 77, 344, 206);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblLaptopName = new JLabel("Laptop Name : ");
		lblLaptopName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblLaptopName.setBounds(27, 32, 101, 32);
		panel.add(lblLaptopName);
		
		JLabel lblModel = new JLabel("Model :");
		lblModel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblModel.setBounds(27, 75, 101, 32);
		panel.add(lblModel);
		
		JLabel lblPrice = new JLabel("Price :");
		lblPrice.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblPrice.setBounds(27, 118, 101, 32);
		panel.add(lblPrice);
		
		lpname = new JTextField();
		lpname.setBounds(138, 39, 168, 25);
		panel.add(lpname);
		lpname.setColumns(10);
		
		modelname = new JTextField();
		modelname.setBounds(138, 82, 168, 25);
		panel.add(modelname);
		modelname.setColumns(10);
		
		pricename = new JTextField();
		pricename.setBounds(138, 125, 168, 25);
		panel.add(pricename);
		pricename.setColumns(10);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String lname = lpname.getText();
				String model = modelname.getText();
				String price = pricename.getText();
							
				 try {
					pst = con.prepareStatement("insert into booktable(name,model,price)values(?,?,?)");
					pst.setString(1, lname);
					pst.setString(2, model);
					pst.setString(3, price);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
					table_load();
						           
					lpname.setText("");
					modelname.setText("");
					pricename.setText("");
					lpname.requestFocus();
				   }

				catch (SQLException e1) 
			        {
									
				e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNewButton.setBounds(10, 300, 100, 60);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				lpname.setText("");
				modelname.setText("");
				pricename.setText("");
				lpid.setText("");
				
				lpname.requestFocus();
					
			}
		});
		btnClear.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnClear.setBounds(133, 300, 100, 60);
		frame.getContentPane().add(btnClear);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnExit.setBounds(254, 300, 100, 60);
		frame.getContentPane().add(btnExit);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 371, 344, 60);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblLaptopId = new JLabel("Laptop Id :");
		lblLaptopId.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblLaptopId.setBounds(10, 24, 99, 25);
		panel_1.add(lblLaptopId);
		
		lpid = new JTextField();
		lpid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				try {
			          
		            String id = lpid.getText();

		                pst = con.prepareStatement("select name,model,price from booktable where id = ?");
		                pst.setString(1, id);
		                ResultSet rs = pst.executeQuery();

		            if(rs.next()==true)
		            {
		              
		                String lname = rs.getString(1);
		                String model = rs.getString(2);
		                String price = rs.getString(3);
		                
		                lpname.setText(lname);
		                modelname.setText(model);
		                pricename.setText(price);
		                
		                
		            }   
		            else
		            {
		            	lpname.setText("");
		                modelname.setText("");
		                pricename.setText("");
		                 
		            }
		            


		        } 
			
			 catch (SQLException ex) {
		           
		        }
			
			
			
			
			}
		});
		lpid.setBounds(119, 27, 186, 22);
		panel_1.add(lpid);
		lpid.setColumns(10);
		
		table = new JTable();
		table.setBounds(377, 115, 28, -25);
		frame.getContentPane().add(table);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(364, 82, 315, 278);
		frame.getContentPane().add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String lname = lpname.getText();
				String model = modelname.getText();
				String price = pricename.getText();
				String laptopid = lpid.getText();
							
				 try {
					pst = con.prepareStatement("update booktable set name= ?,model= ?,price= ? where id=?");
					pst.setString(1, lname);
					pst.setString(2, model);
					pst.setString(3, price);
					pst.setString(4, laptopid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Updatedddd!!!!!");
					table_load();
						           
					lpname.setText("");
					modelname.setText("");
					pricename.setText("");
					lpname.requestFocus();
				   }

				catch (SQLException e1) 
			        {
									
				e1.printStackTrace();
				}
				
			}
			
		});
		btnUpdate.setForeground(Color.BLACK);
		btnUpdate.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnUpdate.setBounds(407, 367, 100, 60);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String laptopid = lpid.getText();
							
				 try {
					pst = con.prepareStatement("delete from booktable where id= ?");
				    pst.setString(1, laptopid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Updatedddd!!!!!");
					table_load();
						           
					lpname.setText("");
					modelname.setText("");
					pricename.setText("");
					lpname.requestFocus();
				   }

				catch (SQLException e1) 
			        {
									
				e1.printStackTrace();
				}
				
			
			}
		});
		btnDelete.setForeground(Color.BLACK);
		btnDelete.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnDelete.setBounds(526, 367, 100, 60);
		frame.getContentPane().add(btnDelete);
	}
}
