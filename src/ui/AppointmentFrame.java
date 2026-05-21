package ui;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.intellijthemes.FlatGradiantoNatureGreenIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatLightFlatIJTheme;
import java.awt.event.ItemEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Appointment;
import model.Facility;
import model.Patient;
import model.Room;
import model.Staff;
import model.enums.AppointmentStatus;
import model.enums.AppointmentType;
import model.enums.Role;
import patterns.mediator.AppointmentScheduler;
import patterns.mediator.SchedulerMediator;
import service.FacilityService;
import service.PatientService;
import service.RoomService;
import service.StaffService;
import session.SessionManager;


public class AppointmentFrame extends javax.swing.JFrame {

    private final FacilityService facilityService = new FacilityService();
    private final PatientService patientService = new PatientService();
    private final StaffService staffService = new StaffService();
    private final RoomService roomService = new RoomService();
    private final HashMap<String, Integer> facilityMap = new HashMap<>();
    private final HashMap<String, Integer> roomMap = new HashMap<>();
    private final HashMap<String, Integer> patientMap = new HashMap<>();
    private final HashMap<String, Integer> doctorMap = new HashMap<>();
    private int appointmentId;
    SchedulerMediator appointmentScheduler;

    public AppointmentFrame() {
        initComponents();
        appointmentScheduler = new AppointmentScheduler();
        clear();

    }

    private void loadType() {

        cbType.removeAllItems();
        cbType.addItem("SELECT");

        for (AppointmentType status : AppointmentType.values()) {
            cbType.addItem(status.toString());
        }

    }

    private void loadFacility() {
        try {
            List<Facility> facilityList = facilityService.getAllFacilities();
            cbFacility.removeAllItems();
            cbFacility.addItem("SELECT");

            for (Facility facility : facilityList) {
                cbFacility.addItem(facility.getName());
                facilityMap.put(facility.getName(), facility.getId());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void loadRoom(int facilityId) {
        try {
            List<Room> roomList = roomService.getRoomsByFacility(facilityId);
            cbRoom.removeAllItems();

            for (Room room : roomList) {
                cbRoom.addItem(room.getName());
                roomMap.put(room.getName(), room.getId());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void loadPatient(List<Patient> searchPatients) {

        cbPatient.removeAllItems();

        for (Patient patient : searchPatients) {
            cbPatient.addItem(patient.getName() + " " + patient.getNic());
            patientMap.put(patient.getName() + " " + patient.getNic(), patient.getId());
        }

    }

    private void loadDoctor(List<Staff> searchDoctors) {

        cbDoctor.removeAllItems();

        for (Staff doctor : searchDoctors) {
            cbDoctor.addItem(doctor.getName());
            doctorMap.put(doctor.getName(), doctor.getId());
        }

    }

    private void loadAppointments(String text) {
        try {
            List<Appointment> appointmentList;
            if (text.isEmpty()) {
                 appointmentList = appointmentScheduler.getAppointments();
            } else {
                 appointmentList = appointmentScheduler.searchAppointments(text);
            }

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            for (Appointment appointment : appointmentList) {
                Vector<String> vector = new Vector<>();
                vector.add(String.valueOf(appointment.getId()));
                vector.add(appointment.getTitle());
                Patient patient = patientService.getPatientById(appointment.getPatientId(), SessionManager.getInstance().getCurrentStaff());
                vector.add(patient.getName());

                Staff staff = staffService.getStaffById(appointment.getStaffId());
                vector.add(staff.getName());

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedStartDateTime = appointment.getStartTime().format(formatter);
                String formattedEndDateTime = appointment.getEndTime().format(formatter);

                vector.add(formattedStartDateTime);
                vector.add(formattedEndDateTime);

                Facility facility = facilityService.getFacilityById(appointment.getFacilityId());
                vector.add(facility.getName());

                Room room = roomService.getRoomById(appointment.getRoomId());
                vector.add(room.getName());
                vector.add(appointment.getNotes());
                vector.add(appointment.getType().toString());
                vector.add(appointment.getStatus().toString());

                model.addRow(vector);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

   

    private void clear() {
        tfPatient.setText("");
        cbPatient.removeAllItems();
        tfDoctor.setText("");
        cbDoctor.removeAllItems();
        tfTitle.setText("");
        cbFacility.setSelectedIndex(0);
        cbRoom.setSelectedIndex(0);
        taNotes.setText("");
        btnDelete.setEnabled(false);
        btnAddBill.setEnabled(false);
        btnSave.setEnabled(true);
        loadFacility();
        loadType();
        loadAppointments("");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfPatient = new javax.swing.JTextField();
        btnPatientSearch = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cbPatient = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        tfDoctor = new javax.swing.JTextField();
        btnDoctorSearch = new javax.swing.JButton();
        cbDoctor = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        tfTitle = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        dateTimePickerStart = new com.github.lgooddatepicker.components.DateTimePicker();
        dateTimePickerEnd = new com.github.lgooddatepicker.components.DateTimePicker();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbFacility = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cbRoom = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cbType = new javax.swing.JComboBox<>();
        btnSave = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        taNotes = new javax.swing.JTextArea();
        btnAddBill = new javax.swing.JButton();
        btnAddBill1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        tfSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Appointments");
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        btnPatientSearch.setText("Search");
        btnPatientSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPatientSearchActionPerformed(evt);
            }
        });

        jLabel2.setText("Patient Selection");

        cbPatient.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECT" }));

        jLabel3.setText("Doctor Selection");

        btnDoctorSearch.setText("Search");
        btnDoctorSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoctorSearchActionPerformed(evt);
            }
        });

        cbDoctor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECT" }));

        jLabel4.setText("Title");

        tfTitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTitleActionPerformed(evt);
            }
        });

        jLabel5.setText("Start Time");

        jLabel6.setText("End Time");

        jLabel7.setText("Facility");

        cbFacility.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbFacility.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbFacilityItemStateChanged(evt);
            }
        });

        jLabel8.setText("Room");

        cbRoom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel9.setText("Type");

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jLabel10.setText("Notes");

        taNotes.setColumns(20);
        taNotes.setRows(5);
        jScrollPane2.setViewportView(taNotes);

        btnAddBill.setText("Add a Bill");
        btnAddBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddBillActionPerformed(evt);
            }
        });

        btnAddBill1.setText("<Back");
        btnAddBill1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddBill1ActionPerformed(evt);
            }
        });

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
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(tfPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnPatientSearch))
                                    .addComponent(jLabel2)
                                    .addComponent(cbPatient, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(tfDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnDoctorSearch))
                                    .addComponent(jLabel3)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(tfTitle))
                                    .addComponent(cbDoctor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dateTimePickerStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dateTimePickerEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbFacility, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbRoom, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAddBill, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAddBill1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPatientSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDoctorSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(tfTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(cbType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(dateTimePickerStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(dateTimePickerEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(cbFacility, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(cbRoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(btnSave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClear)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAddBill)
                .addGap(10, 10, 10)
                .addComponent(btnAddBill1)
                .addContainerGap())
        );

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Title ", "Patient", "Doctor", "Start Time", "End Time", "Facility", "Room", "Notes", "Type", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, false, false, false, false
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(tfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSearch)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1)
                .addContainerGap())
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

    private void cbFacilityItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbFacilityItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            String name = cbFacility.getSelectedItem().toString();

            if ("SELECT".equals(name)) {
                cbRoom.removeAllItems();
                roomMap.clear();
                cbRoom.addItem("SELECT FACILITY FIRST");
            } else {
                Integer facilityId = facilityMap.get(name);
                if (facilityId != null) {
                    loadRoom(facilityId);
                } else {
                    System.out.println("Facility not found in map: " + name);
                    cbRoom.removeAllItems();
                    cbRoom.addItem("ERROR LOADING ROOMS");
                }
            }
        }
    }//GEN-LAST:event_cbFacilityItemStateChanged

    private void tfTitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfTitleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfTitleActionPerformed

    private void btnPatientSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPatientSearchActionPerformed
        String patientSearch = tfPatient.getText().trim();

        try {
            List<Patient> searchPatients = patientService.searchPatients(patientSearch, SessionManager.getInstance().getCurrentStaff());
            loadPatient(searchPatients);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }//GEN-LAST:event_btnPatientSearchActionPerformed

    private void btnDoctorSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoctorSearchActionPerformed
        String doctorSearch = tfDoctor.getText().trim();

        try {
            List<Staff> searchDoctor = staffService.searchDoctor(doctorSearch);
            loadDoctor(searchDoctor);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnDoctorSearchActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        String patient = cbPatient.getSelectedItem().toString();
        String doctor = cbDoctor.getSelectedItem().toString();
        String title = tfTitle.getText();
        String type = cbType.getSelectedItem().toString();
        LocalDateTime startTime = dateTimePickerStart.getDateTimePermissive();
        LocalDateTime endTime = dateTimePickerEnd.getDateTimePermissive();
        String facility = cbFacility.getSelectedItem().toString();
        String room = cbRoom.getSelectedItem().toString();
        String notes = taNotes.getText();

        if (patient.equals("SELECT")) {
            JOptionPane.showMessageDialog(this, "Please Enter a Patient", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (doctor.equals("SELECT")) {
            JOptionPane.showMessageDialog(this, "Please Enter a Doctor", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (title.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter a Title", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (type.equals("SELECT")) {
            JOptionPane.showMessageDialog(this, "Please Enter a Password", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (startTime == null) {
            JOptionPane.showMessageDialog(this, "Please Enter Starting Time", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (endTime == null) {
            JOptionPane.showMessageDialog(this, "Please Enter Ending Time", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (facility.equals("SELECT")) {
            JOptionPane.showMessageDialog(this, "Please Select a Facility", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (room == null) {
            JOptionPane.showMessageDialog(this, "Please Select a Room", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            if (endTime.isBefore(startTime) || endTime.isEqual(startTime)) {
                JOptionPane.showMessageDialog(this, "End time must be after start time", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Appointment appointment = new Appointment(patientMap.get(patient), doctorMap.get(doctor), SessionManager.getInstance().getCurrentStaff().getId(), facilityMap.get(facility), roomMap.get(room),
                    AppointmentType.valueOf(type), title, notes, startTime, endTime, AppointmentStatus.PENDING);

            try {
                String responseText = appointmentScheduler.scheduleAppointment(appointment);

                if (responseText.equals("Success")) {
                    JOptionPane.showMessageDialog(this, "Appointment Added Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    clear();
                } else {
                    JOptionPane.showMessageDialog(this, responseText, "Warning", JOptionPane.WARNING_MESSAGE);

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() == 2) {

           
            btnSave.setEnabled(false);
            btnDelete.setEnabled(true);
            btnAddBill.setEnabled(true);

            int selectedRow = jTable1.getSelectedRow();

            String id = String.valueOf(jTable1.getValueAt(selectedRow, 0));
            appointmentId = Integer.parseInt(id);

        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        try {
            boolean success = appointmentScheduler.cancelAppointment(appointmentId);
            if (success) {
                JOptionPane.showMessageDialog(this, "Appointment Deleted", "Success", JOptionPane.INFORMATION_MESSAGE);
                clear();
            } else {
                JOptionPane.showMessageDialog(this, "Fail to Deleted Appointment", "Warning", JOptionPane.WARNING_MESSAGE);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String searchText = tfSearch.getText();
        loadAppointments(searchText);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnAddBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddBillActionPerformed
        try {
            new BillFrame(appointmentScheduler.getAppointmentById(appointmentId)).setVisible(true);
            this.dispose();
                    } catch (Exception ex) {
           ex.printStackTrace();
        }
    }//GEN-LAST:event_btnAddBillActionPerformed

    private void btnAddBill1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddBill1ActionPerformed
        new HomeFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAddBill1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatGradiantoNatureGreenIJTheme.setup();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AppointmentFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddBill;
    private javax.swing.JButton btnAddBill1;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDoctorSearch;
    private javax.swing.JButton btnPatientSearch;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cbDoctor;
    private javax.swing.JComboBox<String> cbFacility;
    private javax.swing.JComboBox<String> cbPatient;
    private javax.swing.JComboBox<String> cbRoom;
    private javax.swing.JComboBox<String> cbType;
    private com.github.lgooddatepicker.components.DateTimePicker dateTimePickerEnd;
    private com.github.lgooddatepicker.components.DateTimePicker dateTimePickerStart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea taNotes;
    private javax.swing.JTextField tfDoctor;
    private javax.swing.JTextField tfPatient;
    private javax.swing.JTextField tfSearch;
    private javax.swing.JTextField tfTitle;
    // End of variables declaration//GEN-END:variables
}
