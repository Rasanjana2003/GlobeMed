package model;

import java.time.LocalDateTime;
import model.enums.AppointmentStatus;
import model.enums.AppointmentType;

public class Appointment {

    private int id;
    private int patientId;
    private int staffId;
    private int createdBy;
    private int facilityId;
    private Integer roomId;
    private AppointmentType type;
    private String title;
    private String notes;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private AppointmentStatus status;

    public Appointment() {
    }

    public Appointment(int id, int patientId, int staffId, int createdBy,
            int facilityId, Integer roomId, AppointmentType type,
            String title, String notes,
            LocalDateTime startTime, LocalDateTime endTime,
            AppointmentStatus status) {
        this.id = id;
        this.patientId = patientId;
        this.staffId = staffId;
        this.createdBy = createdBy;
        this.facilityId = facilityId;
        this.roomId = roomId;
        this.type = type;
        this.title = title;
        this.notes = notes;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public Appointment(int patientId, int staffId, int createdBy,
            int facilityId, Integer roomId, AppointmentType type,
            String title, String notes,
            LocalDateTime startTime, LocalDateTime endTime,
            AppointmentStatus status) {
        this.patientId = patientId;
        this.staffId = staffId;
        this.createdBy = createdBy;
        this.facilityId = facilityId;
        this.roomId = roomId;
        this.type = type;
        this.title = title;
        this.notes = notes;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getPatientId() {
        return patientId;
    }

    public int getStaffId() {
        return staffId;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public int getFacilityId() {
        return facilityId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public AppointmentType getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getNotes() {
        return notes;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public void setFacilityId(int facilityId) {
        this.facilityId = facilityId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public void setType(AppointmentType type) {
        this.type = type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }
}
