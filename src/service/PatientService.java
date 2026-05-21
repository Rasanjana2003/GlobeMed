package service;

import dao.PatientDAO;
import model.Patient;
import model.Staff;
import java.util.List;
import security.AccessRequest;
import security.AccessResult;
import security.LoginCheckHandler;
import security.RoleCheckHandler;
import security.SecurityHandler;
import model.enums.Action;

public class PatientService {

    private SecurityHandler securityChain;
    private PatientDAO patientDAO;

    public PatientService() {
        this.patientDAO = new PatientDAO();

        
        SecurityHandler loginHandler = new LoginCheckHandler();
        SecurityHandler roleHandler = new RoleCheckHandler();

        loginHandler.setNext(roleHandler);
        this.securityChain = loginHandler;
    }

    public Patient createPatient(Patient p) throws Exception {
        int newId = PatientDAO.insert(p);
        p.setId(newId);
        return p;
    }

    public void updatePatient(Patient patient, Staff staff) throws Exception {
        
        AccessRequest request = new AccessRequest(patient.getId(), staff, Action.UPDATE_PATIENT);
        AccessResult result = securityChain.handle(request);

        if (!result.isGranted()) {
            throw new Exception(result.getMessage());
        } else {
            patientDAO.update(patient);
        }

    }

    public boolean deletePatient(int patientId, Staff staff) throws Exception {
       
        AccessRequest request = new AccessRequest(patientId, staff, Action.DELETE_PATIENT);
        AccessResult result = securityChain.handle(request);

        if (!result.isGranted()) {
            throw new Exception(result.getMessage());
        }

        int rows = PatientDAO.delete(patientId);
        return rows > 0;
    }

    public Patient getPatientById(int patientId, Staff staff) throws Exception {
       
        AccessRequest request = new AccessRequest(patientId, staff, Action.VIEW_PATIENT_SENSITIVE);
        AccessResult result = securityChain.handle(request);

        if (!result.isGranted()) {
            throw new Exception(result.getMessage());
        }

        Patient patient = patientDAO.findById(patientId);

        if (result.shouldMaskSensitiveData()) {
            patient.setMedicalHistory("REDACTED");
            patient.setTreatmentPlans("REDACTED");
        }

        return patient;
    }


    public List<Patient> searchPatients(String q, Staff staff) throws Exception {
        AccessRequest request = new AccessRequest(0, staff, Action.VIEW_PATIENT_LIST);
        AccessResult result = securityChain.handle(request);

        if (!result.isGranted()) {
            throw new Exception(result.getMessage());
        }

        List<Patient> patients;
        if (q == null || q.trim().isEmpty()) {
            patients = PatientDAO.findAll();
        } else {
            patients = PatientDAO.findByNameOrNic(q.trim());
        }

       
        if (result.shouldMaskSensitiveData()) {
            for (Patient p : patients) {
                p.setMedicalHistory("REDACTED");
                p.setTreatmentPlans("REDACTED");
            }
        }

        return patients;
    }

    public List<Patient> getAllPatients(Staff staff) throws Exception {
        AccessRequest request = new AccessRequest(0, staff, Action.VIEW_PATIENT_LIST);
        AccessResult result = securityChain.handle(request);

        if (!result.isGranted()) {
            throw new Exception(result.getMessage());
        }

        List<Patient> patients = PatientDAO.findAll();

        if (result.shouldMaskSensitiveData()) {
            for (Patient p : patients) {
                p.setMedicalHistory("REDACTED");
                p.setTreatmentPlans("REDACTED");
            }
        }

        return patients;
    }

}
