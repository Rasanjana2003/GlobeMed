package service;

import dao.FacilityDAO;
import model.Facility;
import java.util.List;

public class FacilityService {

    private FacilityDAO facilityDAO;

    public FacilityService() {
        this.facilityDAO = new FacilityDAO();
    }

    public void addFacility(Facility facility) throws Exception {
        facilityDAO.add(facility);
    }

    public void updateFacility(Facility facility) throws Exception {
        facilityDAO.update(facility);
    }

    public void deleteFacility(int id) throws Exception {
        facilityDAO.delete(id);
    }

    public Facility getFacilityById(int id) throws Exception {
        return facilityDAO.findById(id);
    }

    public List<Facility> getAllFacilities() throws Exception {
        return facilityDAO.findAll();
    }
}
