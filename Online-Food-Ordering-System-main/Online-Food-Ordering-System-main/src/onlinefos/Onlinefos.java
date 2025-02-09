package onlinefos;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Image;


public class Onlinefos extends JFrame implements ActionListener{
    //creating components
    JLabel emailLabel,passwordLabel,backgroundlabel;
    JTextField nameField,emailField;
    JPasswordField passwordField;
    JButton login,register;
    static final String JDBC_URL = "jdbc:oracle:thin:@//localhost:1521/xe";
    static final String USERNAME = "braise_.j7";
    static final String PASSWORD = "john";
    
     
    public Onlinefos(){   
        //setup window properties
              setTitle("EAT24 LOGIN");
              setSize(700, 600);
              setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              
        ImageIcon backgroundImage = new ImageIcon("C:\\Users\\brais\\OneDrive\\Documents\\download.jpg"); 
        backgroundlabel = new JLabel(backgroundImage);
        backgroundlabel.setLayout(new GridBagLayout()); 
        setContentPane(backgroundlabel);
              
              //create components 
               emailLabel = new JLabel ("Email Address");
                passwordLabel = new JLabel ("Password");
                nameField = new JTextField();
                emailField = new JTextField();
                passwordField = new JPasswordField();
                login = new JButton("Login");
                register = new JButton("Register");                 
                //panel creation
                JPanel textFieldsPanel = new JPanel (new GridLayout(2,2,10,10));
                textFieldsPanel.add(emailLabel);
                textFieldsPanel.add(emailField);
                textFieldsPanel.add(passwordLabel);
                textFieldsPanel.add(passwordField);
                
                //define constraints
                JPanel panel = new JPanel(new GridBagLayout());
                GridBagConstraints constraints = new GridBagConstraints ();
                constraints.insets = new Insets(10,10,10,10);
                constraints.anchor = GridBagConstraints.WEST;
                
                
                constraints.gridx = 0;
                constraints.gridy = 0;
                panel.add(textFieldsPanel,constraints);
                
                constraints.gridx = 0;
                constraints.gridy = 1;
                constraints.gridwidth = 2;
                constraints.anchor = GridBagConstraints.WEST;
                panel.add(login,constraints);
                
                constraints.gridx = 0;
                constraints.gridy = 1;
                constraints.gridwidth = 2;
                constraints.anchor = GridBagConstraints.EAST;
                panel.add(register,constraints);

            
                add(panel);
                login.addActionListener(this);
                register.addActionListener(this);
                setVisible(true);                
                }
    
        public void actionPerformed(ActionEvent e)
    {
            if (e.getSource() == login) {
            String inputUsername = emailField.getText();
            String inputPassword = new String(passwordField.getPassword());

            try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
                String sqlQuery = "SELECT * FROM Users WHERE Username = ? AND Password = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                    preparedStatement.setString(1, inputUsername);
                    preparedStatement.setString(2, inputPassword);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        JOptionPane.showMessageDialog(this, "Login successful! Welcome to EAT24 ... ");
                        new Page1();

                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid username or password.");
                    }
                }
            } 
            catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error connecting to the database.");
            }
        }
            else if(e.getSource() == register) {
                new Register1();
         }    
        } 
   
    
    public static void main(String[] args) {
      Onlinefos signupForm = new Onlinefos();
    }
}




