package dao;

import config.MySQL;
import model.Facility;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FacilityDAO {

    
    public void add(Facility facility) throws Exception {
        String query = "INSERT INTO facilities(name, type, address, city, phone) VALUES("
                + "'" + facility.getName() + "',"
                + "'" + facility.getType() + "',"
                + "'" + facility.getAddress() + "',"
                + "'" + facility.getCity() + "',"
                + "'" + facility.getPhone() + "')";
        MySQL.update(query);
    }

    
    public void update(Facility facility) throws Exception {
        String query = "UPDATE facilities SET "
                + "name='" + facility.getName() + "', "
                + "type='" + facility.getType() + "', "
                + "address='" + facility.getAddress() + "', "
                + "city='" + facility.getCity() + "', "
                + "phone='" + facility.getPhone() + "' "
                + "WHERE facility_id=" + facility.getId();
        MySQL.update(query);
    }

    
    public void delete(int id) throws Exception {
        String query = "DELETE FROM facilities WHERE facility_id=" + id;
        MySQL.update(query);
    }

    
    public Facility findById(int id) throws Exception {
        String query = "SELECT * FROM facilities WHERE facility_id=" + id;
        ResultSet rs = MySQL.query(query);
        if (rs.next()) {
            return new Facility(
                    rs.getInt("facility_id"),
                    rs.getString("name"),
                    rs.getString("type"),
                    rs.getString("address"),
                    rs.getString("city"),
                    rs.getString("phone")
            );
        }
        return null;
    }

    
    public List<Facility> findAll() throws Exception {
        String query = "SELECT * FROM facilities";
        ResultSet rs = MySQL.query(query);
        List<Facility> list = new ArrayList<>();
        while (rs.next()) {
            Facility f = new Facility(
                    rs.getInt("facility_id"),
                    rs.getString("name"),
                    rs.getString("type"),
                    rs.getString("address"),
                    rs.getString("city"),
                    rs.getString("phone")
            );
            list.add(f);
        }
        return list;
    }
}
