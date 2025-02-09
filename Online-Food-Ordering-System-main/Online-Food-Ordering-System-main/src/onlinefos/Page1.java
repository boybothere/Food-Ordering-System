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



public class Page1 extends JFrame{
        private String username;
       private JLabel backgroundlabel;
    Page1(){
        this.username = username;
        setTitle(" EAT24 ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        setSize(1100, 700);
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(255, 87, 34));
        JLabel headerLabel = new JLabel(" Welcome to EAT24! ");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(Color.BLACK);
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        JPanel menuPanel = new JPanel(new GridLayout(3, 3, 10, 10));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        
            JButton foodButton = new JButton("Pizza");
            foodButton.setPreferredSize(new Dimension(200, 150));
            foodButton.setBackground(Color.RED);
            foodButton.addActionListener(new FoodItemListener("Pizza"));
            foodButton.setIcon(new ImageIcon("C:\\Users\\brais\\OneDrive\\Documents\\piz.jpg"));
            menuPanel.add(foodButton);
            
            JButton foodButto = new JButton("Burger");
            foodButto.setPreferredSize(new Dimension(200, 150));
                        foodButto.setBackground(Color.GREEN);
            foodButto.addActionListener(new FoodItemListener("Burger"));
                        foodButto.setIcon(new ImageIcon("C:\\Users\\brais\\OneDrive\\Documents\\bur.jpg"));
            menuPanel.add(foodButto);
            
            JButton foodButt = new JButton("Salad");
            foodButt.setPreferredSize(new Dimension(200, 150));
                        foodButt.setBackground(Color.YELLOW);
            foodButt.addActionListener(new FoodItemListener("Salad"));
                        foodButt.setIcon(new ImageIcon("C:\\Users\\brais\\OneDrive\\Documents\\sa.jpg"));

            menuPanel.add(foodButt);
            
            JButton foodButn = new JButton("Chicken 65");
            foodButn.setPreferredSize(new Dimension(200, 150));
                        foodButn.setBackground(Color.BLUE);
            foodButn.addActionListener(new FoodItemListener("Chicken 65"));
                        foodButn.setIcon(new ImageIcon("C:\\Users\\brais\\OneDrive\\Documents\\ch.jpg"));

            menuPanel.add(foodButn);
            
            JButton foButton = new JButton("Shawarma");
            foButton.setPreferredSize(new Dimension(200, 150));
                        foButton.setBackground(Color.CYAN);
            foButton.addActionListener(new FoodItemListener("Shawarma"));
                        foButton.setIcon(new ImageIcon("C:\\Users\\brais\\OneDrive\\Documents\\shawarma.jpg"));

            menuPanel.add(foButton);
            
            JButton fooutton = new JButton("Biryani");
            fooutton.setPreferredSize(new Dimension(200, 150));
                        fooutton.setBackground(Color.MAGENTA);
            fooutton.addActionListener(new FoodItemListener("Biryani"));
                        fooutton.setIcon(new ImageIcon("C:\\Users\\brais\\OneDrive\\Documents\\br.jpg"));

            menuPanel.add(fooutton);

             JButton foodtton = new JButton("Tandoori (Full)");
            foodtton.setPreferredSize(new Dimension(200, 150));
                        foodtton.setBackground(Color.ORANGE);
            foodtton.addActionListener(new FoodItemListener("Tandoori (Full)"));
                        foodtton.setIcon(new ImageIcon("C:\\Users\\brais\\OneDrive\\Documents\\td.jpg"));

            menuPanel.add(foodtton);
            
            JButton foodBun = new JButton("Ice Cream");
            foodBun.setPreferredSize(new Dimension(200, 150));
                        foodBun.setBackground(Color.GRAY);
            foodBun.addActionListener(new FoodItemListener("Ice Cream"));
                        foodBun.setIcon(new ImageIcon("C:\\Users\\brais\\OneDrive\\Documents\\ic.jpg"));

            menuPanel.add(foodBun);
            
            JButton fButton = new JButton("Fried Rice");
            fButton.setPreferredSize(new Dimension(200, 150));
                        fButton.setBackground(Color.PINK);
            fButton.addActionListener(new FoodItemListener("Fried Rice"));
                        fButton.setIcon(new ImageIcon("C:\\Users\\brais\\OneDrive\\Documents\\fr.jpg"));
            menuPanel.add(fButton);
            
        add(menuPanel, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(255, 87, 34));
        JLabel footerLabel = new JLabel("Order Now");
        footerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        footerLabel.setForeground(Color.WHITE);
        footerPanel.add(footerLabel);
        add(footerPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
     setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    private class FoodItemListener implements ActionListener {
        private String itemName;

        public FoodItemListener(String itemName) {
            this.itemName = itemName;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
                    new BillPage();
    }
    }

    
    private double getItemPrice(String itemName) {
        switch (itemName) {
            case "Pizza":
                return 149.0;
            case "Burger":
                return 50.0;
            case "Salad":
                return 20.0;
            case "Chicken 65":
                return 70.0;
            case "Shawarma":
                return 70.0;
            case "Biryani":
                return 200.0;
            case "Tandoori(Full)":
                return 350.0;
            case "Ice Cream":
                return 80.0;
            case "Fried Rice":
                return 150.0;
            default:
                return 0.0;
        }
    }

class BillPage2 extends JFrame {
    public BillPage2(String itemName, int quantity, double price) {
        setTitle("Order Summary");
    }
}
    public static void main (String args[])
    {
        new Page1();
    }
}
