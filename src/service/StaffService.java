package service;

import dao.StaffDAO;
import model.Staff;
import java.util.List;

public class StaffService {

    private final StaffDAO staffDAO;

    public StaffService() {
        this.staffDAO = new StaffDAO();
    }

    public void addStaff(Staff staff) throws Exception {

        staffDAO.insert(staff);
    }

    public void updateStaff(Staff staff) throws Exception {
        staffDAO.update(staff);
    }

    public boolean deleteStaff(int staffId) throws Exception {
        return staffDAO.delete(staffId) > 0;
    }

    public Staff getStaffById(int staffId) throws Exception {
        return staffDAO.findById(staffId);
    }

    public Staff getStaffByEmail(String email) throws Exception {
        return staffDAO.findByEmail(email);
    }

    public List<Staff> getAllStaff() throws Exception {
        return staffDAO.findAll();
    }

    public List<Staff> searchStaff(String keyword) throws Exception {
        return staffDAO.findByKeyword(keyword);
    }

    public List<Staff> searchDoctor(String keyword) throws Exception {
        return staffDAO.findDoctorByKeyword(keyword);
    }
}
