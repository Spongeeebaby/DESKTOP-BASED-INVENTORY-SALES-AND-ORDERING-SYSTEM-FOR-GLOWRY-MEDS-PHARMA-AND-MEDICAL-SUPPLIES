
package gui;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Product;
import service.InventoryService;
import java.util.List;
import javax.swing.JTextArea;
import model.Sales;
import model.SaleItem;
import service.SalesService;
import java.time.LocalDateTime;
import java.awt.Color;
import java.time.format.DateTimeParseException;
import javax.swing.table.DefaultTableCellRenderer;

public class staffFrame extends javax.swing.JFrame {

   private final InventoryService inventoryService;
   private final SalesService salesService;

 
   private int selectedRow = -1;
   private final int staffId;
   
    public staffFrame(int staffId) {
        initComponents();
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        
        this.staffId = staffId;
        
        inventoryService = new InventoryService();
        salesService = new SalesService();

        LocalDate today = LocalDate.now();
        DateTimeFormatter format =
        DateTimeFormatter.ofPattern("MM/dd/yyyy");

        date_txt.setText(today.format(format));

        setupExpirationColor();
        loadProducts();
        
    }

    private void loadProducts() {

        DefaultTableModel model = (DefaultTableModel) available_medicine.getModel();

        model.setRowCount(0);

        List<Product> products = inventoryService.getAllProducts();

        for (Product product : products) { model.addRow(new Object[]{
            product.getCode(),
            product.getMedicine(),
            product.getCategory(),
            product.getPrice(),
            product.getStock(),
            product.getExpDate()});
    }
    }
    private void calculateTotal() {

        DefaultTableModel model = (DefaultTableModel) order_table.getModel();

        double total = 0;

        for (int i = 0; i < model.getRowCount(); i++) {

            Object value = model.getValueAt(i, 4);
            if (value != null && !value.toString().trim().isEmpty()) {total += Double.parseDouble(value.toString());
    }
    }

    total_label.setText(String.format("%.2f", total));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        date_txt = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        logout_btn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        search_txt = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        available_medicine = new javax.swing.JTable();
        place_order_btn = new javax.swing.JButton();
        quantity_txt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        order_table = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        change_label = new javax.swing.JLabel();
        money_paid_txt = new javax.swing.JTextField();
        total_label = new javax.swing.JLabel();
        pay_btn = new javax.swing.JButton();
        remove_btn = new javax.swing.JButton();
        print_reciept_btn = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        search_btn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1380, 720));

        jPanel4.setBackground(new java.awt.Color(153, 153, 255));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 2, true));

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 102));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/clerk (2).png"))); // NOI18N
        jLabel3.setText("CASHIER");

        date_txt.setBackground(new java.awt.Color(153, 153, 255));
        date_txt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        date_txt.setBorder(null);
        date_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                date_txtActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Date:");

        logout_btn.setBackground(new java.awt.Color(204, 204, 255));
        logout_btn.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        logout_btn.setText("log out");
        logout_btn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 1, true));
        logout_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logout_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logout_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(297, 297, 297)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(425, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(483, 483, 483)
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(date_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(date_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(logout_btn))))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jLabel4.setText("Search Medicine");

        search_txt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 1, true));

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));
        jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 2, true));

        available_medicine.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        available_medicine.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "CODE", "MEDICINE NAME", "CATEGORY", "PRICE", "STOCK", "EXP DATE"
            }
        ));
        available_medicine.setSelectionBackground(new java.awt.Color(0, 0, 102));
        available_medicine.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                available_medicineMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(available_medicine);

        jTabbedPane1.addTab("", jScrollPane1);

        place_order_btn.setBackground(new java.awt.Color(0, 0, 102));
        place_order_btn.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        place_order_btn.setForeground(new java.awt.Color(255, 255, 255));
        place_order_btn.setText("Place Order");
        place_order_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                place_order_btnActionPerformed(evt);
            }
        });

        quantity_txt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        quantity_txt.setText("        ");
        quantity_txt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 102)));
        quantity_txt.setSelectionColor(new java.awt.Color(0, 0, 102));
        quantity_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantity_txtActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 102));
        jLabel5.setText("Quantity");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(129, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(quantity_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(place_order_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(128, 128, 128))
            .addComponent(jTabbedPane1)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(quantity_txt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(place_order_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)))
                .addGap(31, 31, 31))
        );

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));
        jPanel6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 153), 2, true));

        order_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODE", "MEDICINE NAME", "PRICE", "QTY", "TOTAL"
            }
        ));
        jScrollPane2.setViewportView(order_table);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 102));
        jLabel7.setText("Total:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 102));
        jLabel8.setText("Money Paid:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 102));
        jLabel9.setText("Change :");

        money_paid_txt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        money_paid_txt.setText("                ");
        money_paid_txt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 1, true));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(change_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(money_paid_txt, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                    .addComponent(total_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(16, 16, 16))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(12, 12, 12))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(total_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addGap(6, 6, 6))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(money_paid_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(change_label, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pay_btn.setBackground(new java.awt.Color(0, 204, 0));
        pay_btn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        pay_btn.setForeground(new java.awt.Color(255, 255, 255));
        pay_btn.setText("Pay");
        pay_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pay_btnActionPerformed(evt);
            }
        });

        remove_btn.setBackground(new java.awt.Color(204, 0, 0));
        remove_btn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        remove_btn.setForeground(new java.awt.Color(255, 255, 255));
        remove_btn.setText("Remove");
        remove_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remove_btnActionPerformed(evt);
            }
        });

        print_reciept_btn.setBackground(new java.awt.Color(0, 0, 102));
        print_reciept_btn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        print_reciept_btn.setForeground(new java.awt.Color(255, 255, 255));
        print_reciept_btn.setText("Print Recipt");
        print_reciept_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                print_reciept_btnActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("                            Order");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(pay_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(print_reciept_btn, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(remove_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(remove_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(print_reciept_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pay_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        search_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/magnifier.png"))); // NOI18N
        search_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_btnActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(153, 153, 255));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 2, true));

        jLabel1.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 102));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/drugs.png"))); // NOI18N
        jLabel1.setText("GLOWRY MEDS PHARMA");

        jLabel2.setForeground(new java.awt.Color(0, 0, 102));
        jLabel2.setText("AND MEDICAL SUPPLIES");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(71, 71, 71))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(search_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(search_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(search_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(search_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 719, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void search_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_btnActionPerformed
        
        String keyword = search_txt.getText().trim();

        if (keyword.isEmpty()) {
            loadProducts();
            return;
    }

        DefaultTableModel model = (DefaultTableModel) available_medicine.getModel();
            model.setRowCount(0);

    List<Product> products = inventoryService.searchProducts(keyword);

    for (Product product : products) {

        model.addRow(new Object[]{
            product.getCode(),
            product.getMedicine(),
            product.getCategory(),
            product.getPrice(),
            product.getStock(),
            product.getExpDate()
        });
    }

    if (products.isEmpty()) {

        JOptionPane.showMessageDialog(this,"No medicine found.");
        
    }
        
    }//GEN-LAST:event_search_btnActionPerformed

    private void print_reciept_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_print_reciept_btnActionPerformed

      // Check if there are orders
    if (order_table.getRowCount() == 0) {
        JOptionPane.showMessageDialog(this, "There are no orders to print.");
        return;
    }

    try {
        // Get payment details
        double total = Double.parseDouble(total_label.getText().trim());

        double moneyPaid = Double.parseDouble(money_paid_txt.getText().trim());

        double change = moneyPaid - total;

        // Get the order table model
        DefaultTableModel orderModel = (DefaultTableModel) order_table.getModel();

        // Open receipt frame
        receiptframe receipt = new receiptframe(
                orderModel,
                total,
                moneyPaid,
                change
        );

        receipt.setVisible(true);

        // Clear orders after opening the receipt
        orderModel.setRowCount(0);

        // Reset fields
        total_label.setText("0.00");
        money_paid_txt.setText("");
        change_label.setText("");
        quantity_txt.setText("");

        selectedRow = -1;

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Please check the payment details.");
        
    }
    

    }//GEN-LAST:event_print_reciept_btnActionPerformed

    private void remove_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remove_btnActionPerformed

        int selectedRow = order_table.getSelectedRow();

        if (selectedRow == -1) {JOptionPane.showMessageDialog(this,"Please select an order to remove.");
            return;
        }

        DefaultTableModel model = (DefaultTableModel) order_table.getModel();

        model.removeRow(selectedRow);
        calculateTotal();
        change_label.setText("");
    }//GEN-LAST:event_remove_btnActionPerformed

    private void pay_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pay_btnActionPerformed

      if (order_table.getRowCount() == 0) {
        JOptionPane.showMessageDialog(this, "There are no items in the order.");
        return;
    }

    try {
        // Get total and payment
        double total = Double.parseDouble(total_label.getText().trim());

        String paidText = money_paid_txt.getText().trim();

        if (paidText.isEmpty()) {JOptionPane.showMessageDialog(this, "Please enter the amount paid.");
            return;
        }

        double moneyPaid = Double.parseDouble(paidText);

        if (moneyPaid < 0) {JOptionPane.showMessageDialog(this,"Payment cannot be negative.");
            return;
        }

        if (moneyPaid < total) {
            JOptionPane.showMessageDialog(
                    this,
                    "Insufficient payment."
            );
            return;
        }

        double change = moneyPaid - total;

        DefaultTableModel orderModel =
                (DefaultTableModel) order_table.getModel();

        // =====================================
        // CHECK ALL PRODUCTS AND STOCK FIRST
        // =====================================
        for (int i = 0; i < orderModel.getRowCount(); i++) {

            String code = orderModel.getValueAt(i, 0).toString();

            int quantity = Integer.parseInt(
                    orderModel.getValueAt(i, 3).toString()
            );

            Product product = inventoryService.findByCode(code);

            if (product == null) {
                JOptionPane.showMessageDialog(
                        this,
                        "Product not found: " + code
                );
                return;
            }

            if (quantity <= 0) {
                JOptionPane.showMessageDialog(
                        this,
                        "Invalid quantity for: " + product.getMedicine()
                );
                return;
            }

            if (product.getStock() < quantity) {
                JOptionPane.showMessageDialog(
                        this,
                        "Not enough stock for: "
                        + product.getMedicine()
                );
                return;
            }
        }

        // =====================================
        // SAVE SALE
        // =====================================
        Sales sale = new Sales(
                staffId,
                LocalDateTime.now(),
                total,
                moneyPaid,
                change
        );

        int saleId = salesService.addSale(sale);

        if (saleId == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Failed to save sale."
            );
            return;
        }

        // =====================================
        // SAVE SALE ITEMS AND REDUCE STOCK
        // =====================================
        for (int i = 0; i < orderModel.getRowCount(); i++) {

            String code = orderModel.getValueAt(i, 0).toString();

            String medicine = orderModel.getValueAt(i, 1).toString();

            double price = Double.parseDouble(
                    orderModel.getValueAt(i, 2).toString()
            );

            int quantity = Integer.parseInt(
                    orderModel.getValueAt(i, 3).toString()
            );

            double subtotal = Double.parseDouble(
                    orderModel.getValueAt(i, 4).toString()
            );

            // Reduce inventory stock
            boolean stockUpdated = inventoryService.reduceStock(
                    code,
                    quantity
            );

            if (!stockUpdated) {
                JOptionPane.showMessageDialog(
                        this,
                        "Failed to update stock for: " + medicine
                        + ". Please check the inventory and sales records."
                );
                return;
            }

            // Save product sold
            SaleItem item = new SaleItem(
                    code,
                    medicine,
                    quantity,
                    price,
                    subtotal
            );

            item.setSaleId(saleId);

            salesService.addSaleItem(item);
        }

        // =====================================
        // DISPLAY CHANGE
        // =====================================
        change_label.setText(
                String.format("%.2f", change)
        );

        // Refresh available medicine table
        loadProducts();


    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(
                this,
                "Please enter a valid amount or quantity."
        );

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "An error occurred during payment: "
                + e.getMessage()
        );
    }
}


// =====================================
// GET EXPIRATION DATE COLOR
// =====================================
private Color getExpiryColor(String expDate) {

    try {
        LocalDate expirationDate;

        // MySQL format: yyyy-MM-dd
        try {
            expirationDate = LocalDate.parse(expDate);

        } catch (DateTimeParseException e) {

            // GUI format: MM/dd/yyyy
            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("MM/dd/yyyy");

            expirationDate = LocalDate.parse(expDate, formatter);
        }

        LocalDate today = LocalDate.now();

        // Already expired
        if (expirationDate.isBefore(today)) {
            return Color.RED;
        }

        // Expires today or within 3 days
        if (!expirationDate.isAfter(today.plusDays(3))) {
            return Color.RED;
        }

        // Expires within 1 month
        if (!expirationDate.isAfter(today.plusMonths(1))) {
            return Color.ORANGE;
        }

        // Expires within 2 months
        if (!expirationDate.isAfter(today.plusMonths(2))) {
            return Color.YELLOW;
        }

    } catch (Exception e) {

        System.out.println(
                "Expiration Color Error: " + e.getMessage()
        );
    }

    return null;
}


// =====================================
// SET UP EXPIRATION DATE COLORS
// =====================================
private void setupExpirationColor() {

    DefaultTableCellRenderer renderer =
            new DefaultTableCellRenderer() {

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

            // Default colors
            component.setBackground(Color.WHITE);
            component.setForeground(Color.BLACK);

            if (value != null) {

                Color expiryColor =
                        getExpiryColor(value.toString());

                if (expiryColor != null) {

                    component.setBackground(expiryColor);

                    if (expiryColor == Color.RED
                            || expiryColor == Color.ORANGE) {

                        component.setForeground(Color.WHITE);

                    } else {
                        component.setForeground(Color.BLACK);
                    }
                }
            }

            return component;
        }
    };

    // EXP DATE is column index 5
    available_medicine.getColumnModel()
            .getColumn(5)
            .setCellRenderer(renderer);


    
    }//GEN-LAST:event_pay_btnActionPerformed

    private void quantity_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantity_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantity_txtActionPerformed

    private void place_order_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_place_order_btnActionPerformed

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(
                this,
                "Please select a medicine first."
            );
            return;
        }

        if (quantity_txt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Please enter quantity."
            );
            return;
        }

        try {

            int quantity = Integer.parseInt(quantity_txt.getText().trim());

            if (quantity <= 0) {
                JOptionPane.showMessageDialog(
                    this,
                    "Quantity must be greater than 0."
                );
                return;
            }

            String code = available_medicine
            .getValueAt(selectedRow, 0)
            .toString();

            String medicine = available_medicine
            .getValueAt(selectedRow, 1)
            .toString();

            double price = Double.parseDouble(
                available_medicine
                .getValueAt(selectedRow, 3)
                .toString()
            );

            double total = price * quantity;

            DefaultTableModel orderModel =
            (DefaultTableModel) order_table.getModel();

            orderModel.addRow(new Object[]{
                code,
                medicine,
                price,
                quantity,
                total
            });

            calculateTotal();

            quantity_txt.setText("");
            selectedRow = -1;

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                this,
                "Please enter a valid whole number for quantity."
            );
        }
    }//GEN-LAST:event_place_order_btnActionPerformed

    private void available_medicineMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_available_medicineMouseClicked

        selectedRow = available_medicine.getSelectedRow();

        if (selectedRow >= 0) {

            String code = available_medicine
            .getValueAt(selectedRow, 0)
            .toString();

            String medicine = available_medicine
            .getValueAt(selectedRow, 1)
            .toString();

            System.out.println("Selected: " + code + " - " + medicine);
        }
    }//GEN-LAST:event_available_medicineMouseClicked

    private void logout_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logout_btnActionPerformed
            
        loginframe login = new loginframe();
        login.setVisible(true);
        this.dispose();
        
        
    }//GEN-LAST:event_logout_btnActionPerformed

    private void date_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_date_txtActionPerformed

    }//GEN-LAST:event_date_txtActionPerformed

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
            java.util.logging.Logger.getLogger(staffFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(staffFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(staffFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(staffFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new staffFrame(1).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable available_medicine;
    private javax.swing.JLabel change_label;
    private javax.swing.JTextField date_txt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton logout_btn;
    private javax.swing.JTextField money_paid_txt;
    private javax.swing.JTable order_table;
    private javax.swing.JButton pay_btn;
    private javax.swing.JButton place_order_btn;
    private javax.swing.JButton print_reciept_btn;
    private javax.swing.JTextField quantity_txt;
    private javax.swing.JButton remove_btn;
    private javax.swing.JButton search_btn;
    private javax.swing.JTextField search_txt;
    private javax.swing.JLabel total_label;
    // End of variables declaration//GEN-END:variables
}
