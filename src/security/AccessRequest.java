package security;

import model.Staff;
import model.enums.Action;

public class AccessRequest {
    private String username;
    private String role;       
    private Action action;     
    private int patientId;     
    private Staff staff;

    public AccessRequest(String username, String role, Action action, int patientId) {
        this.username = username;
        this.role = role;
        this.action = action;
        this.patientId = patientId;
    }
    
     public AccessRequest(int patientId, Staff staff) {
        this.patientId = patientId;
        this.staff = staff;
    }

    public AccessRequest(int id, Staff staff, Action action) {
        this.patientId = id;
        this.staff = staff;
        this.action = action;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public Action getAction() {
        return action;
    }

    public int getPatientId() {
        return patientId;
    }
    
    public Staff getStaff() {
        return staff;
    }
}
