package ui;

import com.formdev.flatlaf.intellijthemes.FlatGradiantoNatureGreenIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatLightFlatIJTheme;
import dao.InsuranceClaimDAO;
import java.awt.Desktop;
import java.awt.Dimension;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.table.DefaultTableModel;
import model.Appointment;
import model.Bill;
import model.InsuranceClaim;
import model.Patient;
import model.enums.BillStatus;
import model.enums.ClaimStatus;
import model.enums.PaymentMethod;
import service.BillService;
import service.InsuranceClaimService;
import service.PatientService;
import service.ReportService;
import session.SessionManager;

public class BillFrame extends javax.swing.JFrame {

    private Appointment appointment;
    private final PatientService patientService = new PatientService();
    private final BillService billService = new BillService();
    private final InsuranceClaimService insuranceClaimService = new InsuranceClaimService();
    private final ReportService reportService = new ReportService();

    private Patient patient;
    private int updateBillId;

    public BillFrame(Appointment appointment) {
        initComponents();
        this.appointment = appointment;
        loadData();

    }

    private void loadData() {
        tfAppointmentId.setText(String.valueOf(appointment.getId()));
        tfPatientId.setText(String.valueOf(appointment.getPatientId()));
        try {
            patient = patientService.getPatientById(appointment.getPatientId(), SessionManager.getInstance().getCurrentStaff());
            tfPatientName.setText(patient.getName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        tfServiceType.setText(appointment.getType().name());
        loadPaymentMethod();
        loadPaymentStatus();
        loadBillData();
        btnUpdate.setEnabled(false);
        cbPaymentMethod.setEnabled(true);
        btnPay.setEnabled(true);
        btnViewReport.setEnabled(false);

    }

    private void loadPaymentStatus() {

        cbPaymentStatus.removeAllItems();
        cbPaymentStatus.addItem("SELECT");

        for (BillStatus status : BillStatus.values()) {
            cbPaymentStatus.addItem(status.toString());
        }

    }

    private void loadPaymentMethod() {

        cbPaymentMethod.removeAllItems();
        cbPaymentMethod.addItem("SELECT");

        for (PaymentMethod method : PaymentMethod.values()) {
            cbPaymentMethod.addItem(method.toString());
        }

    }

    private void loadBillData() {

        try {
            Bill bill = billService.getBillByAppointmentId(appointment.getId());

            if (bill != null) {

                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                model.setRowCount(0);

                Vector<String> vector = new Vector<>();
                vector.add(String.valueOf(bill.getId()));
                vector.add(String.valueOf(bill.getAppointmentId()));
                vector.add(patient.getName());
                vector.add(bill.getPaymentMethod().name());
                vector.add(String.valueOf(bill.getAmount()));
                vector.add(bill.getServiceType());
                vector.add(bill.getCreatedAt());
                vector.add(bill.getStatus().name());

                model.addRow(vector);

                loadClaimData(bill.getId());

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void loadClaimData(int billId) {

        try {
            InsuranceClaim claim = insuranceClaimService.getClaimByBillId(billId);

            if (claim != null) {
                DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
                model.setRowCount(0);

                Vector<String> vector = new Vector<>();
                vector.add(String.valueOf(claim.getId()));
                vector.add(String.valueOf(claim.getBillId()));
                vector.add(patient.getName());
                vector.add(claim.getInsuranceProvider());
                vector.add(claim.getSubmittedAt());
                vector.add(claim.getReviewedAt());
                vector.add(claim.getPolicyNumber());
                vector.add(claim.getNotes());
                vector.add(claim.getClaimStatus().name());

                model.addRow(vector);

                taNotes.setText(claim.getNotes());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfAppointmentId = new javax.swing.JTextField();
        tfPatientId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tfPatientName = new javax.swing.JTextField();
        tfAmount = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tfServiceType = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbPaymentMethod = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cbPaymentStatus = new javax.swing.JComboBox<>();
        btnPay = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnUpdate1 = new javax.swing.JButton();
        btnViewReport = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        taNotes = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("BIll");

        jLabel2.setText("Appointment Id");

        tfAppointmentId.setEditable(false);

        tfPatientId.setEditable(false);

        jLabel3.setText("Patient Id");

        jLabel4.setText("Patient Name");

        tfPatientName.setEditable(false);

        jLabel5.setText("Amount");

        tfServiceType.setEditable(false);

        jLabel6.setText("Service Type");

        jLabel7.setText("Payment Method");

        cbPaymentMethod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setText("Payemnt Status");

        cbPaymentStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnPay.setText("Pay");
        btnPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPayActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnUpdate1.setText("<Back");
        btnUpdate1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdate1ActionPerformed(evt);
            }
        });

        btnViewReport.setText("View Report");
        btnViewReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewReportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(tfAppointmentId)
                            .addComponent(jLabel3)
                            .addComponent(tfPatientId)
                            .addComponent(jLabel4)
                            .addComponent(tfPatientName)
                            .addComponent(jLabel5)
                            .addComponent(tfAmount)
                            .addComponent(jLabel6)
                            .addComponent(tfServiceType)
                            .addComponent(jLabel7)
                            .addComponent(cbPaymentMethod, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8)
                            .addComponent(cbPaymentStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnPay, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                    .addComponent(btnUpdate1, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                    .addComponent(btnViewReport, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfAppointmentId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfPatientId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfServiceType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbPaymentMethod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbPaymentStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnPay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnUpdate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnViewReport)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnUpdate1)
                .addContainerGap())
        );

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("BIll Table");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Bill Id", "Appointment Id", "Patient Name", "Payment Method", "Amount", "Service Type", "Created At", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Insurance Claim Table");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Claim Id", "Bill Id", "Patient", "Insurance Provider", "Submitted At", "Reviewed At", "Policy Number", "Notes", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setRowHeight(60);
        jScrollPane2.setViewportView(jTable2);

        jLabel11.setText("Notes");

        taNotes.setEditable(false);
        taNotes.setColumns(20);
        taNotes.setRows(5);
        jScrollPane3.setViewportView(taNotes);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPayActionPerformed
        String appointmentId = tfAppointmentId.getText();
        String patientId = tfPatientId.getText();
        String patientName = tfPatientName.getText();
        String amount = tfAmount.getText();
        String serviceType = tfServiceType.getText();
        String paymentMethod = cbPaymentMethod.getSelectedItem().toString();

        if (amount.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter a Payment Amount", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (paymentMethod.equals("SELECT")) {
            JOptionPane.showMessageDialog(this, "Please Enter a Payment Method", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            if (paymentMethod.equals("CASH")) {

                try {
                    int billId = billService.createBill(Integer.parseInt(patientId), Integer.parseInt(appointmentId), Double.parseDouble(amount), serviceType, PaymentMethod.CASH);
                    JOptionPane.showMessageDialog(this, "Bill Added Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    loadData();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            } else if (paymentMethod.equals("INSURANCE")) {

                try {
                    int billId = billService.createBill(Integer.parseInt(patientId), Integer.parseInt(appointmentId), Double.parseDouble(amount), serviceType, PaymentMethod.INSURANCE);
                    JOptionPane.showMessageDialog(this, "Bill Added Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                    Random random = new Random();
                    int policyNumber = random.nextInt(900000) + 100000;

                    InsuranceClaim claim = new InsuranceClaim();
                    claim.setBillId(billId);
                    claim.setPatientId(Integer.parseInt(patientId));
                    claim.setInsuranceProvider("Globemed Insurance");
                    claim.setPolicyNumber(String.valueOf(policyNumber));
                    claim.setClaimStatus(ClaimStatus.SUBMITTED);
                    claim.setSubmittedAt(java.time.LocalDateTime.now().toString());
                    claim.setReviewedAt(null);

                    Bill bill = billService.getBillById(billId);

                    int claimId = insuranceClaimService.createClaim(bill, claim, patient, SessionManager.getInstance().getCurrentStaff());

                    if (claimId > 0) {
                        JOptionPane.showMessageDialog(this, "Insurance Process Complete!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        loadData();
                    } else {
                        JOptionPane.showMessageDialog(this, "Error in Insurance Process", "Warning", JOptionPane.WARNING_MESSAGE);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

        }
    }//GEN-LAST:event_btnPayActionPerformed

    private void btnUpdate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdate1ActionPerformed
        new AppointmentFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnUpdate1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() == 2) {

            btnPay.setEnabled(false);
            btnUpdate.setEnabled(true);
            cbPaymentMethod.setEnabled(false);
            btnViewReport.setEnabled(true);

            int selectedRow = jTable1.getSelectedRow();

            String id = String.valueOf(jTable1.getValueAt(selectedRow, 0));
            updateBillId = Integer.parseInt(id);

        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        String billStatus = cbPaymentStatus.getSelectedItem().toString();

        if (billStatus.equals("SELECT")) {
            JOptionPane.showMessageDialog(this, "Please select a Status", "Warning", JOptionPane.WARNING_MESSAGE);

        } else {
            try {
                Bill bill = billService.getBillById(updateBillId);
                bill.setStatus(BillStatus.valueOf(billStatus));

                boolean updated = billService.updateBill(bill);
                if (updated) {
                    JOptionPane.showMessageDialog(this, "Bill Updated Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                } else {
                    JOptionPane.showMessageDialog(this, "Bill Updated Failed!", "Warning", JOptionPane.WARNING_MESSAGE);

                }

                loadData();

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }


    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnViewReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewReportActionPerformed
        try {
            String htmlReport = reportService.generateBillReport(updateBillId);

            JEditorPane editorPane = new JEditorPane("text/html", htmlReport);
            editorPane.setEditable(false);

            
            editorPane.addHyperlinkListener(e -> {
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    if (Desktop.isDesktopSupported()) {
                        try {
                            Desktop.getDesktop().browse(e.getURL().toURI());
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });

            JScrollPane scrollPane = new JScrollPane(editorPane);
            scrollPane.setPreferredSize(new Dimension(800, 600));

            JOptionPane.showMessageDialog(this, scrollPane, "Patient Treatment Report", JOptionPane.PLAIN_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnViewReportActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatGradiantoNatureGreenIJTheme.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new BillFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPay;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnUpdate1;
    private javax.swing.JButton btnViewReport;
    private javax.swing.JComboBox<String> cbPaymentMethod;
    private javax.swing.JComboBox<String> cbPaymentStatus;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea taNotes;
    private javax.swing.JTextField tfAmount;
    private javax.swing.JTextField tfAppointmentId;
    private javax.swing.JTextField tfPatientId;
    private javax.swing.JTextField tfPatientName;
    private javax.swing.JTextField tfServiceType;
    // End of variables declaration//GEN-END:variables
}
