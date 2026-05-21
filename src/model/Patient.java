package model;

public class Patient {

    private int id;
    private String name;
    private String dob;
    private String gender;
    private String address;
    private String phone;
    private String email;
    private String nic;
    private String medicalHistory;
    private String treatmentPlans;

    public Patient(int id, String name, String dob, String gender, String address,
            String phone, String email, String nic, String medicalHistory, String treatmentPlans) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.nic = nic;
        this.medicalHistory = medicalHistory;
        this.treatmentPlans = treatmentPlans;
    }

    public Patient(String name, String dob, String gender, String address,
            String phone, String email, String nic, String medicalHistory, String treatmentPlans) {
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.nic = nic;
        this.medicalHistory = medicalHistory;
        this.treatmentPlans = treatmentPlans;
    }

    public Patient(String name, String dob, String gender, String address,
            String phone, String email, String nic) {
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.nic = nic;

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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getTreatmentPlans() {
        return treatmentPlans;
    }

    public void setTreatmentPlans(String treatmentPlans) {
        this.treatmentPlans = treatmentPlans;
    }

    @Override
    public String toString() {
        return "Patient{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", dob='" + dob + '\''
                + ", gender='" + gender + '\''
                + ", address='" + address + '\''
                + ", phone='" + phone + '\''
                + ", email='" + email + '\''
                + ", nic='" + nic + '\''
                + ", medicalHistory='" + medicalHistory + '\''
                + ", treatmentPlans='" + treatmentPlans + '\''
                + '}';
    }
}
