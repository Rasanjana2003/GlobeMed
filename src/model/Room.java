package model;

public class Room {

    private int id;
    private int facilityId;
    private String name;
    private String type;
    private Integer capacity;

    public Room() {
    }

    public Room(int id, int facilityId, String name, String type, Integer capacity) {
        this.id = id;
        this.facilityId = facilityId;
        this.name = name;
        this.type = type;
        this.capacity = capacity;
    }

    public Room(int facilityId, String name, String type, Integer capacity) {
        this.facilityId = facilityId;
        this.name = name;
        this.type = type;
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public int getFacilityId() {
        return facilityId;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFacilityId(int facilityId) {
        this.facilityId = facilityId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
