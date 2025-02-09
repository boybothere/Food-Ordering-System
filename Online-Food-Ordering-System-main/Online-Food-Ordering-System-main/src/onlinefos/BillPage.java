package onlinefos;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.Image;
import static onlinefos.Register1.JDBC_URL;
import static onlinefos.Register1.PASSWORD;
import static onlinefos.Register1.USERNAME;

public class BillPage extends JFrame{
    private JPanel menuPanel, billingPanel;
    private JLabel backgroundlabel;
    private JButton addToBillButton, generateBillButton,cancelOrderButton;
    private JTextArea billArea;
     static final String JDBC_URL = "jdbc:oracle:thin:@//localhost:1521/xe";
    static final String USERNAME = "braise_.j7";
    static final String PASSWORD = "****";

    private String[] foodItems = {"Pizza", "Burger", "Salad","Chicken 65","Shawarma","Biryanni","Tandoori (Full)","Ice Cream","Fried Rice"};
    private double[] prices = {149,50,20,70,70,200,350,80,150};

    public BillPage() {
        setTitle("EAT24 Billing Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new GridLayout(1, 2));
        
        ImageIcon backgroundImage = new ImageIcon("C:\\Users\\brais\\OneDrive\\Documents\\OIP.jpg"); 
        backgroundlabel = new JLabel(backgroundImage);
        backgroundlabel.setLayout(new GridBagLayout()); 
        setContentPane(backgroundlabel);
     

        createMenuPanel();
        createBillingPanel();

        add(menuPanel);
        add(billingPanel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createMenuPanel() {
        menuPanel = new JPanel(new BorderLayout());
        JPanel itemsPanel = new JPanel(new GridLayout(foodItems.length, 10));
        
        for (int i = 0; i < foodItems.length; i++) {
            JLabel itemNameLabel = new JLabel(foodItems[i]);
            JLabel priceLabel = new JLabel("₹" + prices[i]);
            JTextField quantityField = new JTextField("0");
            itemsPanel.add(itemNameLabel);
            itemsPanel.add(priceLabel);
            itemsPanel.add(quantityField);
        }
        menuPanel.add(itemsPanel, BorderLayout.CENTER);

        addToBillButton = new JButton("Add to Bill");
        addToBillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToBill();
                 Component[] components = ((JPanel) menuPanel.getComponent(0)).getComponents();
        for (int i = 0; i < components.length; i += 3) {
            JLabel itemNameLabel = (JLabel) components[i];
            JTextField quantityField = (JTextField) components[i + 2];
            String itemName = itemNameLabel.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            double price = getPrice(itemName);
            double total = price * quantity;
             if (e.getSource() == addToBillButton) {
            insertDataIntoDatabase(itemName, quantity, total);
             }
        }
            }
        });
        menuPanel.add(addToBillButton, BorderLayout.NORTH);
        
        cancelOrderButton = new JButton("Cancel Order");
        cancelOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelOrder();
            }
        });
        menuPanel.add(cancelOrderButton, BorderLayout.SOUTH);
        
    }
    
  private void insertDataIntoDatabase(String itemName, int quantity, double total) {
    try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
        String selectSql = "SELECT * FROM UserOrdersss WHERE Item_Name = ?";
        try (PreparedStatement selectStatement = connection.prepareStatement(selectSql)) {
            selectStatement.setString(1, itemName);
            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
                int existingQuantity = resultSet.getInt("Item_Quantity");
                double existingTotal = resultSet.getDouble("Total_Price");
                int newQuantity = quantity;
                double newTotal = total;
                String updateSql = "UPDATE UserOrdersss SET Item_Quantity = ?, Total_Price = ? WHERE Item_Name = ?";
                try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
                    updateStatement.setInt(1, newQuantity);
                    updateStatement.setDouble(2, newTotal);
                    updateStatement.setString(3, itemName);
                    updateStatement.executeUpdate();
                }
            } else {
                String insertSql = "INSERT INTO UserOrdersss (Item_Name, Item_Quantity, Total_Price) VALUES (?, ?, ?)";
                try (PreparedStatement insertStatement = connection.prepareStatement(insertSql)) {
                    insertStatement.setString(1, itemName);
                    insertStatement.setInt(2, quantity);
                    insertStatement.setDouble(3, total);
                    insertStatement.executeUpdate();
                }
            }
        }
    } 
    catch (SQLException ex) {
        ex.printStackTrace();
    }
}
    
    private void cancelOrder() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String sql = "DELETE FROM UserOrdersss";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.executeUpdate(); // Use executeUpdate for DELETE
                JOptionPane.showMessageDialog(this, "Order cancelled successfully!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    
    private void createBillingPanel() {
        billingPanel = new JPanel(new BorderLayout());
        billArea = new JTextArea(20, 30);
        JScrollPane scrollPane = new JScrollPane(billArea);
        billingPanel.add(scrollPane, BorderLayout.CENTER);

        generateBillButton = new JButton("Generate Bill");
        generateBillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateBill();
            }
        });
        billingPanel.add(generateBillButton, BorderLayout.SOUTH);
    }

    private void addToBill() {
        billArea.append("Items added to bill:\n");
        Component[] components = ((JPanel) menuPanel.getComponent(0)).getComponents();
        for (int i = 0; i < components.length; i += 3) {
            JLabel itemNameLabel = (JLabel) components[i];
            JTextField quantityField = (JTextField) components[i + 2];
            String itemName = itemNameLabel.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            double price = getPrice(itemName);
            double total = price * quantity;
            billArea.append(itemName + " - Quantity: " + quantity + ", Total: ₹" + total + "\n");
        }
        billArea.append("\n");
    }

    private void generateBill() {
        double totalAmount = 0;
        Component[] components = ((JPanel) menuPanel.getComponent(0)).getComponents();
        for (int i = 0; i < components.length; i += 3) {
            JTextField quantityField = (JTextField) components[i + 2];
            int quantity = Integer.parseInt(quantityField.getText());
            double price = getPrice(((JLabel) components[i]).getText());
            totalAmount += price * quantity;
        }
        JOptionPane.showMessageDialog(this, "Total Bill Amount: ₹" + totalAmount);
    }

    private double getPrice(String itemName) {
        for (int i = 0; i < foodItems.length; i++) {
            if (foodItems[i].equals(itemName)) {
                return prices[i];
            }
        }
        return 0.0; 
    }


  public static void main (String args[])
    {
                SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BillPage();
            }
        });

    }
}