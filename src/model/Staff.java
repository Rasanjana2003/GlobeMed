package model;

import model.enums.Role;
import patterns.composite.rbac.Grant;

public class Staff {

    private int id;
    private String name;
    private String role;  
    private String email;
    private String phone;
    private String passwordHash;
    private int facilityId; 
    private String speciality;

    private Grant roleGrant;

    public Staff(int id, String name, String role, Grant roleGrant) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.roleGrant = roleGrant;
    }

   
    public Staff(int id, String name, String role,String speciality, String email, String phone, String passwordHash, int facilityId) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.email = email;
        this.phone = phone;
        this.passwordHash = passwordHash;
        this.facilityId = facilityId;
    }

    
    public Staff(String name, String role,String speciality, String email, String phone, String passwordHash, int facilityId) {
        this.name = name;
        this.role = role;
        this.speciality = speciality;
        this.email = email;
        this.phone = phone;
        this.passwordHash = passwordHash;
        this.facilityId = facilityId;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public int getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(int facilityId) {
        this.facilityId = facilityId;
    }
    
     public Grant getRoleGrant() {
        return roleGrant;
    }

    public void setRoleGrant(Grant roleGrant) {
        this.roleGrant = roleGrant;
    }
   

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    
    @Override
    public String toString() {
        return "Staff{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", role='" + role + '\''
                + ", email='" + email + '\''
                + ", phone='" + phone + '\''
                + ", facilityId=" + facilityId
                + '}';
    }

   

   

}
