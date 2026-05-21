package patterns.mediator;

import java.time.LocalDate;
import model.Appointment;
import model.Room;
import model.Staff;

import java.util.List;

public interface SchedulerMediator {
    String scheduleAppointment(Appointment appointment) throws Exception;
    boolean updateAppointment(Appointment appointment) throws Exception;
    boolean cancelAppointment(int appointmentId) throws Exception;
    Appointment getAppointmentById(int appointmentId)throws Exception;
    List<Appointment> getAppointmentsForStaff(Staff staff) throws Exception;
    List<Appointment> getAppointmentsForStaffAndDate(Staff staff,LocalDate date) throws Exception;
    List<Appointment> getAppointmentsForPatient(int patientId) throws Exception;
    List<Appointment> searchAppointments(String search) throws Exception;
    public List<Appointment> getAppointments() throws Exception;
    
}

