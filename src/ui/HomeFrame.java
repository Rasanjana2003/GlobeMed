package ui;

import com.formdev.flatlaf.intellijthemes.FlatGradiantoNatureGreenIJTheme;
import model.Staff;
import session.SessionManager;


public class HomeFrame extends javax.swing.JFrame {

    
    public HomeFrame() {
        initComponents();
        checkLoggedUser();
    }
    
    

    private void checkLoggedUser(){
        Staff currentStaff = SessionManager.getInstance().getCurrentStaff();
        if(currentStaff != null){
            
            nameLabel1.setText("Welcome "+currentStaff.getName());
            
            if(currentStaff.getRole().equals("NURSE") || currentStaff.getRole().equals("PHARMACIST")||currentStaff.getRole().equals("RECEPTIONIST")){
                btnManageStaff.setEnabled(false);
                btnManageFacility.setEnabled(false);
                btnDoctorSchedule.setEnabled(false);
            }else if(currentStaff.getRole().equals("DOCTOR")){
                btnManageStaff.setEnabled(false);
                btnManageFacility.setEnabled(false);
            }
            
        }
    
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        nameLabel1 = new javax.swing.JLabel();
        btnManageStaff = new javax.swing.JButton();
        btnManagePatient = new javax.swing.JButton();
        btnManageFacility = new javax.swing.JButton();
        btnScheduleAppointments = new javax.swing.JButton();
        btnDoctorSchedule = new javax.swing.JButton();
        btnSignout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        nameLabel1.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        nameLabel1.setText("Welcome");

        btnManageStaff.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnManageStaff.setText("Manage Staff");
        btnManageStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageStaffActionPerformed(evt);
            }
        });

        btnManagePatient.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnManagePatient.setText("Manage Patient");
        btnManagePatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManagePatientActionPerformed(evt);
            }
        });

        btnManageFacility.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnManageFacility.setText("Manage Facility");
        btnManageFacility.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageFacilityActionPerformed(evt);
            }
        });

        btnScheduleAppointments.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnScheduleAppointments.setText("Schedule Appointments");
        btnScheduleAppointments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnScheduleAppointmentsActionPerformed(evt);
            }
        });

        btnDoctorSchedule.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDoctorSchedule.setText("Doctor's Schedule");
        btnDoctorSchedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoctorScheduleActionPerformed(evt);
            }
        });

        btnSignout.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSignout.setText("Signout");
        btnSignout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(105, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnManageStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnManagePatient, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnManageFacility, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDoctorSchedule, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnScheduleAppointments, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                        .addComponent(btnSignout, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nameLabel1)
                .addGap(204, 204, 204))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(nameLabel1)
                .addGap(34, 34, 34)
                .addComponent(btnManageStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnManagePatient, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnManageFacility, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDoctorSchedule, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSignout, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnScheduleAppointments, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(61, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnManageStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageStaffActionPerformed
        new StaffFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnManageStaffActionPerformed

    private void btnManagePatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManagePatientActionPerformed
       new PatientFrame().setVisible(true);
       this.dispose();
    }//GEN-LAST:event_btnManagePatientActionPerformed

    private void btnManageFacilityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageFacilityActionPerformed
        new FacilityFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnManageFacilityActionPerformed

    private void btnScheduleAppointmentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnScheduleAppointmentsActionPerformed
        new AppointmentFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnScheduleAppointmentsActionPerformed

    private void btnDoctorScheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoctorScheduleActionPerformed
        new DoctorScheduleFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnDoctorScheduleActionPerformed

    private void btnSignoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignoutActionPerformed
        SessionManager.getInstance().clearSession();
        new SignInFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSignoutActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatGradiantoNatureGreenIJTheme.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDoctorSchedule;
    private javax.swing.JButton btnManageFacility;
    private javax.swing.JButton btnManagePatient;
    private javax.swing.JButton btnManageStaff;
    private javax.swing.JButton btnScheduleAppointments;
    private javax.swing.JButton btnSignout;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel nameLabel1;
    // End of variables declaration//GEN-END:variables
}
