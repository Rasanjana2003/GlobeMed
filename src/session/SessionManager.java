package session;

import model.Staff;


public class SessionManager {
    private static SessionManager instance;
    private Staff currentStaff;
    
    private SessionManager() {
        
    }
    
    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }
    
    public void setCurrentStaff(Staff staff) {
        this.currentStaff = staff;
    }
    
    public Staff getCurrentStaff() {
        return currentStaff;
    }
    
    public void clearSession() {
        this.currentStaff = null;
    }
    
    public boolean isLoggedIn() {
        return currentStaff != null;
    }
}