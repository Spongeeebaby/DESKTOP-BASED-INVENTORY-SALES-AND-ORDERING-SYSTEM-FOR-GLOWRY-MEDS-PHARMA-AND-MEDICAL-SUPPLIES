
package gui;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Product;
import service.InventoryService;
import java.util.List;
import dao.archiveDAO;
import java.awt.Color;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.table.DefaultTableCellRenderer;

public class inventotyFrame extends javax.swing.JFrame {

   private final InventoryService inventoryService;
   private final int adminId;
   private final archiveDAO archiveDAO;
   
   private int selectedId = -1;
    
    public inventotyFrame(int adminId) {
        initComponents();
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        
        this.adminId = adminId;
        inventoryService = new InventoryService();
        archiveDAO = new archiveDAO();
        
        
        setupTable();
        setupExpirationColor();
        

        
        loadTable();
    }
    
     private void clearFields() {

        code_txt.setText("");
        medicine_txt.setText("");
        price_txt.setText("");
        stock_txt.setText("");
        exp_date_txt.setText("");

        categoriescombobox.setSelectedIndex(0);
        unit_combobox.setSelectedIndex(0);

        jTable1.clearSelection();

        selectedId = -1;
    }

    
    private void setupTable() {

    DefaultTableModel model = new DefaultTableModel(new Object[][] {},new String[] {
                    "ID",
                    "CODE",
                    "MEDICINE",
                    "CATEGORY",
                    "PRICE",
                    "STOCK",
                    "UNIT",
                    "EXP DATE"
                }
            );

    jTable1.setModel(model);
    
    jTable1.getColumnModel().getColumn(0).setMinWidth(0);
    jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
    jTable1.getColumnModel().getColumn(0).setWidth(0);
    }
    
    private void loadTable() {

    DefaultTableModel model =(DefaultTableModel)jTable1.getModel();

    model.setRowCount(0);

    List<Product> products = inventoryService.getAllProducts();

    for (Product product : products) {model.addRow(new Object[] {
                product.getId(),
                product.getCode(),
                product.getMedicine(),
                product.getCategory(),
                product.getPrice(),
                product.getStock(),
                product.getUnit(),
                product.getExpDate()
            }
        );
    }
    }
    private void searchMedicine() {

    String keyword = search_med_txt.getText().trim();

    // If search box is empty, show all products
    if (keyword.isEmpty()) {
        loadTable();
        return;
    }

    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

    model.setRowCount(0);

    List<Product> products = inventoryService.searchProducts(keyword);

    for (Product product : products) {

        model.addRow(new Object[]{
            product.getId(),
            product.getCode(),
            product.getMedicine(),
            product.getCategory(),
            product.getPrice(),
            product.getStock(),
            product.getUnit(),
            product.getExpDate()
        });
    }

    if (products.isEmpty()) {

        JOptionPane.showMessageDialog(this, "No medicine found.");
    }
}
    private Color getExpiryColor(String expDate) {

    try {

        LocalDate expirationDate;

        // MySQL DATE format: yyyy-MM-dd
        try {

            expirationDate = LocalDate.parse(expDate);

        } catch (DateTimeParseException e) {

            // MM/dd/yyyy format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

            expirationDate = LocalDate.parse(expDate, formatter);
        }

        LocalDate today = LocalDate.now();

        // 1. Already expired
        if (expirationDate.isBefore(today)) {
            return Color.RED;
        }

        // 2. Expires today or within 3 days
        if (!expirationDate.isAfter(today.plusDays(3))) {
            return Color.RED;
        }

        // 3. Expires within 1 month
        if (!expirationDate.isAfter(today.plusMonths(1))) {
            return Color.ORANGE;
        }

        // 4. Expires within 2 months
        if (!expirationDate.isAfter(today.plusMonths(2))) {
            return Color.YELLOW;
        }

    } catch (Exception e) {

        System.out.println(
                "Expiration Color Error: "
                + e.getMessage()
        );
    }

    // More than 2 months away
    return null;
}
  
   private void setupExpirationColor() {

    DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {

        @Override
        public java.awt.Component getTableCellRendererComponent(
                javax.swing.JTable table,
                Object value,
                boolean isSelected,
                boolean hasFocus,
                int row,
                int column) {

            java.awt.Component component =
                    super.getTableCellRendererComponent(
                            table,
                            value,
                            isSelected,
                            hasFocus,
                            row,
                            column
                    );

            // Default
            component.setBackground(Color.WHITE);
            component.setForeground(Color.BLACK);

            if (value != null) {

                Color expiryColor = getExpiryColor(value.toString());

                if (expiryColor != null) {component.setBackground(expiryColor);

                    if (expiryColor == Color.RED || expiryColor == Color.ORANGE) {
                        component.setForeground(Color.WHITE);

                    } else {
                        component.setForeground(Color.BLACK);
                    }
                }
            }

            return component;
        }
    };

    // EXP DATE = column 7
    jTable1.getColumnModel()
            .getColumn(7)
            .setCellRenderer(renderer);
}
    
  
     
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        code_txt = new javax.swing.JTextField();
        medicine_txt = new javax.swing.JTextField();
        price_txt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        stock_txt = new javax.swing.JTextField();
        exp_date_txt = new javax.swing.JTextField();
        unit_combobox = new javax.swing.JComboBox<>();
        categoriescombobox = new javax.swing.JComboBox<>();
        add_btn = new javax.swing.JButton();
        update_btn = new javax.swing.JButton();
        remove_btn = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        search_med_txt = new javax.swing.JTextField();
        search_med_btn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1380, 720));

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 2, true));
        jPanel2.setPreferredSize(new java.awt.Dimension(253, 160));

        jLabel9.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 102));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/drugs.png"))); // NOI18N
        jLabel9.setText("GLOWRY MEDS PHARMA");

        jLabel10.setForeground(new java.awt.Color(0, 0, 102));
        jLabel10.setText("AND MEDICAL SUPPLIES");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(60, 60, 60))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(153, 153, 255));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 2, true));

        jButton1.setBackground(new java.awt.Color(204, 204, 204));
        jButton1.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 102));
        jButton1.setText("Back");
        jButton1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 1, true));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 36)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 102));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/inventory (5).png"))); // NOI18N
        jLabel11.setText("INVENTORY");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(275, 275, 275)
                .addComponent(jLabel11)
                .addContainerGap(421, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(153, 153, 255));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 2, true));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 102));
        jLabel1.setText("Code:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 102));
        jLabel2.setText("Category:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 102));
        jLabel3.setText("Medicine:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 102));
        jLabel4.setText("Price:");

        code_txt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 1, true));
        code_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                code_txtActionPerformed(evt);
            }
        });

        medicine_txt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 1, true));
        medicine_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                medicine_txtActionPerformed(evt);
            }
        });

        price_txt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 1, true));
        price_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                price_txtActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 102));
        jLabel5.setText("Stock:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 102));
        jLabel6.setText("Unit:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 102));
        jLabel7.setText("Exp Date:");

        stock_txt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 1, true));
        stock_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stock_txtActionPerformed(evt);
            }
        });

        exp_date_txt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 1, true));
        exp_date_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exp_date_txtActionPerformed(evt);
            }
        });

        unit_combobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tablet", "Capsule", "Bottle", "Box", "Pack", "Piece", " " }));
        unit_combobox.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 1, true));

        categoriescombobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Analgesic", "Antacid", "Antibiotic", "Antidiarrheal", "Antifungal", "Antihistamine", "Antihypertensive", "Antiseptic", "Cough Medicine", "Decongestant", "Expectorant", "Laxative", "Vitamin" }));
        categoriescombobox.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 1, true));

        add_btn.setBackground(new java.awt.Color(51, 153, 0));
        add_btn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        add_btn.setForeground(new java.awt.Color(255, 255, 255));
        add_btn.setText("ADD");
        add_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_btnActionPerformed(evt);
            }
        });

        update_btn.setBackground(new java.awt.Color(0, 0, 102));
        update_btn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        update_btn.setForeground(new java.awt.Color(255, 255, 255));
        update_btn.setText("UPDATE");
        update_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_btnActionPerformed(evt);
            }
        });

        remove_btn.setBackground(new java.awt.Color(204, 0, 0));
        remove_btn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        remove_btn.setForeground(new java.awt.Color(255, 255, 255));
        remove_btn.setText("REMOVE");
        remove_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remove_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(add_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(update_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(remove_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(unit_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(medicine_txt))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(38, 38, 38)
                                        .addComponent(code_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 3, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(stock_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(18, 18, 18)
                                    .addComponent(price_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(categoriescombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(exp_date_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(code_txt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(medicine_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(categoriescombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(price_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stock_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(unit_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exp_date_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(27, 27, 27)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(update_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(remove_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        jPanel7.setBackground(new java.awt.Color(153, 153, 255));
        jPanel7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 2, true));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jTable1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "CODE", "MEDICINE", "CATEGORIES", "PRICE", "STOCK", "UNIT", "EXPARATION DATE"
            }
        ));
        jTable1.setSelectionBackground(new java.awt.Color(153, 153, 255));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 102));
        jLabel8.setText("Search Medicine");

        search_med_txt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 1, true));

        search_med_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/magnifier.png"))); // NOI18N
        search_med_btn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 1, true));
        search_med_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_med_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 9, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(search_med_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(search_med_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(588, 588, 588))
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(search_med_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(search_med_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void code_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_code_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_code_txtActionPerformed

    private void medicine_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_medicine_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_medicine_txtActionPerformed

    private void price_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_price_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_price_txtActionPerformed

    private void stock_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stock_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stock_txtActionPerformed

    private void exp_date_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exp_date_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_exp_date_txtActionPerformed

    private void add_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_btnActionPerformed
        
    String code = code_txt.getText().trim();

    String medicine = medicine_txt.getText().trim();

    String category = categoriescombobox.getSelectedItem().toString();

    String priceText = price_txt.getText().trim();

    String stockText = stock_txt.getText().trim();

    String unit = unit_combobox.getSelectedItem().toString();

    String expDate = exp_date_txt.getText().trim();

    if (code.isEmpty()|| medicine.isEmpty()|| priceText.isEmpty()|| stockText.isEmpty()|| expDate.isEmpty()) {

        JOptionPane.showMessageDialog(this,"Please fill in all fields.");
        return;
    }

    try {double price = Double.parseDouble(priceText);

        int stock = Integer.parseInt(stockText);

        Product product = new Product(
                    adminId,
                    code,
                    medicine,
                    category,
                    price,
                    stock,
                    unit,
                    expDate);

        boolean success = inventoryService.addProduct(product);

        if (success) {JOptionPane.showMessageDialog(this,"Product added successfully!");

            clearFields();
            loadTable();

        } else {JOptionPane.showMessageDialog(this,"Failed to add product.");
        }

    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(this,"Price must be a number " + "and stock must be a whole number.");
    }
        
              
    }//GEN-LAST:event_add_btnActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
       
         int row = jTable1.getSelectedRow();

    if (row >= 0) {selectedId = Integer.parseInt(jTable1.getValueAt(row, 0).toString());

        code_txt.setText(jTable1.getValueAt(row, 1).toString());

        medicine_txt.setText(jTable1.getValueAt(row, 2).toString());

        categoriescombobox.setSelectedItem(jTable1.getValueAt(row, 3).toString());

        price_txt.setText(jTable1.getValueAt(row, 4).toString());

        stock_txt.setText(jTable1.getValueAt(row, 5).toString());

        unit_combobox.setSelectedItem(jTable1.getValueAt(row, 6).toString());

        exp_date_txt.setText(jTable1.getValueAt(row, 7).toString() );
    }
        
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void update_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_btnActionPerformed
       
       if (selectedId == -1) {
        JOptionPane.showMessageDialog(
                this,
                "Please select a product from the table first."
        );
        return;
    }

    String code = code_txt.getText().trim();
    String medicine = medicine_txt.getText().trim();
    String priceText = price_txt.getText().trim();
    String stockText = stock_txt.getText().trim();
    String expDate = exp_date_txt.getText().trim();

    if (code.isEmpty() || medicine.isEmpty()
            || priceText.isEmpty() || stockText.isEmpty()
            || expDate.isEmpty()) {

        JOptionPane.showMessageDialog(
                this,
                "Please fill in all fields."
        );
        return;
    }

    try {
        double price = Double.parseDouble(priceText);
        int stock = Integer.parseInt(stockText);

        if (price < 0 || stock < 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Price and stock cannot be negative."
            );
            return;
        }

        String category =
                categoriescombobox.getSelectedItem().toString();

        String unit =
                unit_combobox.getSelectedItem().toString();

        Product product = new Product(
                selectedId,
                adminId,
                code,
                medicine,
                category,
                price,
                stock,
                unit,
                expDate
        );

        boolean success = inventoryService.updateProduct(product);

        if (success) {
            loadTable();

            JOptionPane.showMessageDialog(
                    this,
                    "Product updated successfully!"
            );

            clearFields();

        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "No product was updated. Please check the database."
            );
        }

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(
                this,
                "Price must be a number and stock must be a whole number."
        );
    } catch (Exception e) {
        JOptionPane.showMessageDialog(
                this,
                "Error updating product: " + e.getMessage()
        );
        e.printStackTrace();
    }
             
    }//GEN-LAST:event_update_btnActionPerformed

    private void remove_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remove_btnActionPerformed
        
        if (selectedId == -1) {

        JOptionPane.showMessageDialog(
                this,
                "Please select a product first."
        );

        return;
    }

    int confirm = JOptionPane.showConfirmDialog(
            this,
            "Move this medicine to archive?",
            "Confirm Remove",
            JOptionPane.YES_NO_OPTION
    );

    if (confirm == JOptionPane.YES_OPTION) {

        boolean success =
                archiveDAO.archiveMedicine(selectedId);

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Medicine moved to archive successfully!"
            );

            clearFields();
            loadTable();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to archive medicine."
            );
        }
    }
        
        
    }//GEN-LAST:event_remove_btnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
        adminFrame admin = new adminFrame(adminId);
        admin.setVisible(true);
        this.dispose();
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void search_med_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_med_btnActionPerformed
       
         searchMedicine();

    }//GEN-LAST:event_search_med_btnActionPerformed

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
            java.util.logging.Logger.getLogger(inventotyFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(inventotyFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(inventotyFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(inventotyFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new inventotyFrame(1).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add_btn;
    private javax.swing.JComboBox<String> categoriescombobox;
    private javax.swing.JTextField code_txt;
    private javax.swing.JTextField exp_date_txt;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField medicine_txt;
    private javax.swing.JTextField price_txt;
    private javax.swing.JButton remove_btn;
    private javax.swing.JButton search_med_btn;
    private javax.swing.JTextField search_med_txt;
    private javax.swing.JTextField stock_txt;
    private javax.swing.JComboBox<String> unit_combobox;
    private javax.swing.JButton update_btn;
    // End of variables declaration//GEN-END:variables
}
