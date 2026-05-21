package model;

import model.enums.FacilityType;

public class Facility {

    private int id;
    private String name;
    private String type;
    private String address;
    private String city;
    private String phone;

    public Facility(int id, String name, String type, String address, String city, String phone) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.address = address;
        this.city = city;
        this.phone = phone;
    }

    public Facility(String name, String type, String address, String city, String phone) {
        this.name = name;
        this.type = type;
        this.address = address;
        this.city = city;
        this.phone = phone;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Facility{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", type=" + type
                + ", address='" + address + '\''
                + ", city='" + city + '\''
                + ", phone='" + phone + '\''
                + '}';
    }
}
