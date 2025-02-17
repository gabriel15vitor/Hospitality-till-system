/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package till;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import food.desserts;
import food.food;
import drinks.drinks;
import static java.lang.Double.parseDouble;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gabri
 */
public class main extends javax.swing.JFrame {
    private String quantityStr = ""; // Shared quantity input
    ResultSet queryResult;
    String size = "";
    public HashMap<String, HashMap<String, Double>> beerPrices = new HashMap<>();
    public HashMap<String, HashMap<String, Double>> ciderPrices = new HashMap<>();
    public HashMap<String, HashMap<String, Double>> winePrices = new HashMap<>();
    public HashMap<String, HashMap<String, Double>> spiritPrices = new HashMap<>();
    public HashMap<String, Double> softPrices = new HashMap<>();
    public HashMap<String, Double> foodPrices = new HashMap<>();
    /**
     * Creates new form NewJFrame
     */
    public main() {
        initComponents();
        contentPanel.setLayout(new java.awt.CardLayout());
        contentPanel.add(new drinks(this), "Drinks");
        contentPanel.add(new food(this), "Food");
        contentPanel.add(new desserts(this), "Desserts");
        updateQuantityTextField();
    }
    
    public String getQuantityStr() {
        return quantityStr; // Access the current quantity
    }
    public void appendToQuantity(String number) {
        quantityStr += number; // Append number to the quantity
        updateQuantityTextField(); // Update the text field
    }
    public void clearQuantity() {
        quantityStr = ""; // Clear the quantity
        updateQuantityTextField(); // Clear the text field
    }
    private void updateQuantityTextField() {
        quantityTextField.setText(quantityStr.isEmpty() ? "" : quantityStr);
    }
    
    private void updateItems(){
        updateBeers();
        updateWines();
        updateCiders();
        updateSpirits();
        updateSoft();
        updateFood();
    }
    
    private ResultSet updateData(String query){
        String url = "jdbc:mysql://localhost:3306/Hospitality";
        String username = "root";
        String password = "";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            return resultSet;
        }catch(ClassNotFoundException | SQLException ex){
            return null;
        }
    }
    
    private void updateBeers(){
        queryResult = updateData("SELECT `Name`, `Pint`, `Twothirds`, `Half`, `OneThird` FROM `drinks` WHERE `Type_of_Drink` = 'Beer'");
        
        if(queryResult != null){
            try {
                while(queryResult.next()){
                    String name = queryResult.getString(1).toLowerCase().replace(" ", "");
                    HashMap<String, Double> prices = new HashMap<>();
                    prices.put("", queryResult.getDouble(2));
                    prices.put("2/3", queryResult.getDouble(3));
                    prices.put("Half", queryResult.getDouble(4));
                    prices.put("1/3", queryResult.getDouble(5));
                    beerPrices.put(name, prices);
                }
            } catch (SQLException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void updateCiders(){
        queryResult = updateData("SELECT `Name`, `Pint`, `Twothirds`, `Half`, `OneThird` FROM `drinks` WHERE `Type_of_Drink` = 'Cider'");
        
        if(queryResult != null){
            try {
                while(queryResult.next()){
                    String name = queryResult.getString(1).toLowerCase().replace(" ", "");
                    
                    HashMap<String, Double> prices = new HashMap<>();
                    prices.put("", queryResult.getDouble(2));
                    prices.put("2/3", queryResult.getDouble(3));
                    prices.put("Half", queryResult.getDouble(4));
                    prices.put("1/3", queryResult.getDouble(5));
                    ciderPrices.put(name, prices);
                }
            } catch (SQLException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void updateWines(){
        queryResult = updateData("SELECT `Name`, `Bottle`, `250ml`, `175ml`, `125ml` FROM `drinks` WHERE `Type_of_Drink` = 'Wine'");
        
        if(queryResult != null){
            try {
                while(queryResult.next()){
                    String name = queryResult.getString(1).toLowerCase().replace(" ", "");
                    
                    HashMap<String, Double> prices = new HashMap<>();
                    prices.put("", queryResult.getDouble(2));
                    prices.put("250ml", queryResult.getDouble(3));
                    prices.put("175ml", queryResult.getDouble(4));
                    prices.put("125ml", queryResult.getDouble(5));
                    winePrices.put(name, prices);
                }
            } catch (SQLException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void updateSpirits(){
        queryResult = updateData("SELECT `Name`, `50ml`, `25ml` FROM `drinks` WHERE `Type_of_Drink` = 'Spirit'");
        
        if(queryResult != null){
            try {
                while(queryResult.next()){
                    String name = queryResult.getString(1).toLowerCase().replace(" ", "");
                    
                    HashMap<String, Double> prices = new HashMap<>();
                    prices.put("50ml", queryResult.getDouble(2));
                    prices.put("25ml", queryResult.getDouble(3));
                    prices.put("", queryResult.getDouble(3));
                    spiritPrices.put(name, prices);
                }
            } catch (SQLException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void updateSoft(){
        queryResult = updateData("SELECT `Name`, `Bottle` FROM `drinks` WHERE `Type_of_Drink` = 'Soft'");
        
        if(queryResult != null){
            try {
                while(queryResult.next()){
                    softPrices.put(queryResult.getString(1).toLowerCase().replace(" ", ""), queryResult.getDouble(2));
                }
            } catch (SQLException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void updateFood(){
        queryResult = updateData("SELECT `Name`, `Price` FROM `food`");
        
        if(queryResult != null){
            try {
                while(queryResult.next()){
                    foodPrices.put(queryResult.getString(1).toLowerCase().replace(" ", ""), queryResult.getDouble(2));
                }
            } catch (SQLException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void sumItems(DefaultTableModel model){
        int rows = model.getRowCount();
        int remove = 0;
        if(rows >= 1){
            String number, total;
            double sum = 0;
            

            for(int i = 0; i < rows; i++){
                total = model.getValueAt(i, 2).toString();
                if("Total".equals(total)){
                    remove=i;
                }else{
                    number = model.getValueAt(i, 3).toString();
                    sum += parseDouble(number);
                }
            }
            if(remove != 0){ model.removeRow(remove);}
            model.addRow(new Object[]{null, null, "Total", sum});
        }
    }
    
    public void addFood(String name1){
        String name = name1.toLowerCase().replace(" ", "");
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Double price = foodPrices.get(name);
        
        if(price != null){
            String quantityString = getQuantityStr();// Get the quantity from the main class
            int quantity = quantityString.isEmpty() ? 1 : Integer.parseInt(quantityString);

            // Add the row to the table
            model.addRow(new Object[]{quantity, "", name1, price*quantity});
        }
        
        // Clear the quantity in the main class
        clearQuantity();
        //generates the total spending
        sumItems(model);
    }
    
    public void setDrinksSize(String drinksSize){
        size = drinksSize;
    }
    
    public void addDrink(String name1, String type){
        Double r = null;
        String name = name1.toLowerCase().replace(" ", "");
        switch(type){
            
            case "beer" -> {
                if(size.equals("") || size.equals("2/3") || size.equals("Half") || size.equals("1/3")){
                    r = beerPrices.get(name).get(size);
                }else{
                    r = null;
                }
            }
            
            case "cider" -> {
                if(size.equals("") || size.equals("2/3") || size.equals("Half") || size.equals("1/3")){
                    r = ciderPrices.get(name).get(size);
                }else{
                    r = null;
                }
            }
                
            case "wine" -> {
                if(size.equals("") || size.equals("250ml") || size.equals("175ml") || size.equals("125ml")){
                    r = winePrices.get(name).get(size);
                }else{
                    r = null;
                }
            }
                
            case "spirit" -> {
                if(size.equals("") || size.equals("50ml") || size.equals("25ml")){
                    r = spiritPrices.get(name).get(size);
                }else{
                    r = null;
                }
            }
                
            case "soft" -> {
                r = softPrices.get(name);  
            }
        }
        Double price = r;
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        
        if(price != null){
            String quantityStr = getQuantityStr();// Get the quantity from the main class
            int quantity = quantityStr.isEmpty() ? 1 : Integer.parseInt(quantityStr);
            if(size.isEmpty()){
                switch(type){
                    case "beer" -> size = "Pint";
                    case "cider" -> size = "Pint";
                    case "wine" -> size = "Bottle";
                    case "spirit" -> size = "25ml";
                    case "soft" -> size = "Bottle"; 
                }
            }

            // Add the row to the table
            model.addRow(new Object[]{quantity, size, name1, price*quantity});
        }

        // Clear the quantity in the main class
        clearQuantity();
        size="";
        sumItems(model);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        drinks = new javax.swing.JButton();
        food = new javax.swing.JButton();
        desserts = new javax.swing.JButton();
        configPanel = new javax.swing.JPanel();
        corret = new javax.swing.JButton();
        updateTill = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        contentPanel = new javax.swing.JPanel();
        javax.swing.JButton eight = new javax.swing.JButton();
        javax.swing.JButton seven = new javax.swing.JButton();
        javax.swing.JButton nine = new javax.swing.JButton();
        javax.swing.JButton four = new javax.swing.JButton();
        javax.swing.JButton five = new javax.swing.JButton();
        javax.swing.JButton six = new javax.swing.JButton();
        javax.swing.JButton one = new javax.swing.JButton();
        javax.swing.JButton two = new javax.swing.JButton();
        javax.swing.JButton three = new javax.swing.JButton();
        javax.swing.JButton zero = new javax.swing.JButton();
        javax.swing.JButton clear = new javax.swing.JButton();
        quantityTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        table.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Quantity", "Size", "Item", "Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.getTableHeader().setReorderingAllowed(false);
        table.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tablePropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(3).setResizable(false);
        }

        drinks.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        drinks.setText("Drinks");
        drinks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drinksActionPerformed(evt);
            }
        });

        food.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        food.setText("Food");
        food.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                foodActionPerformed(evt);
            }
        });

        desserts.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        desserts.setText("Desserts");
        desserts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dessertsActionPerformed(evt);
            }
        });

        configPanel.setBackground(new java.awt.Color(153, 255, 255));

        corret.setText("Error corret");
        corret.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                corretActionPerformed(evt);
            }
        });

        updateTill.setText("Update Till");
        updateTill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateTillActionPerformed(evt);
            }
        });

        jButton1.setText("Paid orders");

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton2.setText("Pay");

        javax.swing.GroupLayout configPanelLayout = new javax.swing.GroupLayout(configPanel);
        configPanel.setLayout(configPanelLayout);
        configPanelLayout.setHorizontalGroup(
            configPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(corret, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
            .addComponent(updateTill, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        configPanelLayout.setVerticalGroup(
            configPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(configPanelLayout.createSequentialGroup()
                .addComponent(corret, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(updateTill, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 586, Short.MAX_VALUE)
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 505, Short.MAX_VALUE)
        );

        eight.setText("8");
        eight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eightActionPerformed(evt);
            }
        });

        seven.setText("7");
        seven.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sevenActionPerformed(evt);
            }
        });

        nine.setText("9");
        nine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nineActionPerformed(evt);
            }
        });

        four.setText("4");
        four.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fourActionPerformed(evt);
            }
        });

        five.setText("5");
        five.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fiveActionPerformed(evt);
            }
        });

        six.setText("6");
        six.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sixActionPerformed(evt);
            }
        });

        one.setText("1");
        one.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oneActionPerformed(evt);
            }
        });

        two.setText("2");
        two.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                twoActionPerformed(evt);
            }
        });

        three.setText("3");
        three.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                threeActionPerformed(evt);
            }
        });

        zero.setText("0");
        zero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zeroActionPerformed(evt);
            }
        });

        clear.setText("Clear");
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });

        quantityTextField.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(four, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(seven, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(one, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(eight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(five, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(two, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(six, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(nine, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(three, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(zero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(clear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(quantityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(drinks, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                        .addComponent(food, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                        .addComponent(desserts, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(configPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(quantityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(seven, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eight, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nine, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(five, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(six, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(four, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(two, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(one, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(three, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(zero, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(desserts, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(food, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(drinks, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(configPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void foodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_foodActionPerformed
        java.awt.CardLayout cl = (java.awt.CardLayout) contentPanel.getLayout();
        cl.show(contentPanel, "Food"); // Switch to Food Panel
    }//GEN-LAST:event_foodActionPerformed

    private void dessertsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dessertsActionPerformed
        java.awt.CardLayout cl = (java.awt.CardLayout) contentPanel.getLayout();
        cl.show(contentPanel, "Desserts"); // Switch to Desserts Panel
    }//GEN-LAST:event_dessertsActionPerformed

    private void drinksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drinksActionPerformed
        java.awt.CardLayout cl = (java.awt.CardLayout) contentPanel.getLayout();
        cl.show(contentPanel, "Drinks"); // Switch to Drinks Panel
    }//GEN-LAST:event_drinksActionPerformed

    private void tablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tablePropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_tablePropertyChange

    private void eightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eightActionPerformed
        appendToQuantity("8");
    }//GEN-LAST:event_eightActionPerformed

    private void sevenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sevenActionPerformed
        appendToQuantity("7");
    }//GEN-LAST:event_sevenActionPerformed

    private void oneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oneActionPerformed
        appendToQuantity("1");
    }//GEN-LAST:event_oneActionPerformed

    private void twoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_twoActionPerformed
        appendToQuantity("2");
    }//GEN-LAST:event_twoActionPerformed

    private void threeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_threeActionPerformed
        appendToQuantity("3");
    }//GEN-LAST:event_threeActionPerformed

    private void fourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fourActionPerformed
        appendToQuantity("4");
    }//GEN-LAST:event_fourActionPerformed

    private void fiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fiveActionPerformed
        appendToQuantity("5");
    }//GEN-LAST:event_fiveActionPerformed

    private void sixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sixActionPerformed
        appendToQuantity("6");
    }//GEN-LAST:event_sixActionPerformed

    private void nineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nineActionPerformed
        appendToQuantity("9");
    }//GEN-LAST:event_nineActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        clearQuantity();
    }//GEN-LAST:event_clearActionPerformed

    private void zeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zeroActionPerformed
        appendToQuantity("0");
    }//GEN-LAST:event_zeroActionPerformed

    private void corretActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_corretActionPerformed
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int row = table.getSelectedRow();
        if(row >= 0){
            String total = model.getValueAt(row, 2).toString();
            if(!total.equals("Total") && (model.getRowCount() > 2) ){
                model.removeRow(row);
                sumItems(model);
            }else{
                if((model.getRowCount() == 2) && "Total".equals(model.getValueAt(1, 2).toString())){
                    model.removeRow(0);
                    model.removeRow(0);
                }
            }
        }
    }//GEN-LAST:event_corretActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        updateItems();
    }//GEN-LAST:event_formWindowOpened

    private void updateTillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateTillActionPerformed
        updateItems();
    }//GEN-LAST:event_updateTillActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel configPanel;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JButton corret;
    private javax.swing.JButton desserts;
    private javax.swing.JButton drinks;
    private javax.swing.JButton food;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField quantityTextField;
    public javax.swing.JTable table;
    private javax.swing.JButton updateTill;
    // End of variables declaration//GEN-END:variables
}
