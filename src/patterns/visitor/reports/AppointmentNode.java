package patterns.visitor.reports;

import model.Appointment;


public class AppointmentNode implements ReportNode {

    private final Appointment appointment;

    public AppointmentNode(Appointment appointment) {
        this.appointment = appointment;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    @Override
    public void accept(ReportVisitor visitor) {
        visitor.visit(this);
    }
}
