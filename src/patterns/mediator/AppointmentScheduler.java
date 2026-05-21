package patterns.mediator;

import dao.AppointmentDAO;
import dao.RoomDAO;
import java.time.LocalDate;
import model.Appointment;
import model.Room;
import model.Staff;

import java.util.List;

public class AppointmentScheduler implements SchedulerMediator {

    @Override
    public String scheduleAppointment(Appointment appointment) throws Exception {
       
        List<Appointment> conflicts = AppointmentDAO.findOverlappingAppointments(
                appointment.getStaffId(),
                appointment.getRoomId(), 
                appointment.getStartTime(),
                appointment.getEndTime()
        );

        if (!conflicts.isEmpty()) {
            System.out.println(" Overlapping appointment.");
            return " Overlapping appointment.";
        }

        
        if (appointment.getRoomId() == null) {
            Room availableRoom = RoomDAO.findAvailableRoom(
                    appointment.getFacilityId(),
                    appointment.getStartTime(),
                    appointment.getEndTime()
            );

            if (availableRoom == null) {
                System.out.println(" No rooms available.");
                return " No rooms available.";
            }
            appointment.setRoomId(availableRoom.getId());
        }

        
        int id = AppointmentDAO.insert(appointment);
        if (id > 0) {
            return "Success";
        }else{
            return "Fail for an Appointment";
            
        }
    }

    @Override
    public boolean cancelAppointment(int appointmentId) throws Exception {
        int result = AppointmentDAO.delete(appointmentId);
        return result > 0;
    }

    @Override
    public List<Appointment> getAppointments() throws Exception {
        return AppointmentDAO.findAll();
    }

    @Override
    public List<Appointment> getAppointmentsForStaff(Staff staff) throws Exception {
        return AppointmentDAO.findByStaff(staff.getId());
    }

    @Override
    public List<Appointment> getAppointmentsForPatient(int patientId) throws Exception {
        return AppointmentDAO.findByPatient(patientId);
    }



    @Override
    public List<Appointment> searchAppointments(String search) throws Exception {
        return AppointmentDAO.searchAppointment(search);
    }

    @Override
    public List<Appointment> getAppointmentsForStaffAndDate(Staff staff, LocalDate date) throws Exception {
        return AppointmentDAO.findByStaffAndDate(staff.getId(), date);
    }

    @Override
    public Appointment getAppointmentById(int appointmentId) throws Exception {
            return AppointmentDAO.findById(appointmentId);
    }

    @Override
    public boolean updateAppointment(Appointment appointment) throws Exception {
        int result = AppointmentDAO.update(appointment);
        return result > 0;
    }
}
