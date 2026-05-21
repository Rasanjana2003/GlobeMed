package patterns.visitor.reports;

import model.Appointment;
import model.Bill;
import model.Patient;

import java.util.Collections;
import java.util.List;


public class PatientNode implements ReportNode {

    private final Patient patient;
    private final List<Appointment> appointments;
    private final List<Bill> bills;

    public PatientNode(Patient patient, List<Appointment> appointments, List<Bill> bills) {
        this.patient = patient;
        this.appointments = appointments != null ? appointments : Collections.emptyList();
        this.bills = bills != null ? bills : Collections.emptyList();
    }

    public Patient getPatient() {
        return patient;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public List<Bill> getBills() {
        return bills;
    }

    @Override
    public void accept(ReportVisitor visitor) {
        visitor.visit(this);
    }
}
