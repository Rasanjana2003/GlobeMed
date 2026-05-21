package service;

import dao.BillDAO;
import model.Bill;
import model.enums.BillStatus;
import model.enums.PaymentMethod;

import java.util.List;

public class BillService {

    
    public int createBill(int patientId, int appointmentId, double amount,
                          String serviceType, PaymentMethod paymentMethod) throws Exception {
        Bill bill = new Bill();
        bill.setPatientId(patientId);
        bill.setAppointmentId(appointmentId);
        bill.setAmount(amount);
        bill.setServiceType(serviceType);
        bill.setPaymentMethod(paymentMethod);
        bill.setStatus(BillStatus.PENDING);
        bill.setCreatedAt(java.time.LocalDateTime.now().toString());

        return BillDAO.insert(bill);
    }

    
    public boolean updateBill(Bill bill) throws Exception {
        return BillDAO.update(bill) > 0;
    }

    
    public boolean deleteBill(int billId) throws Exception {
        return BillDAO.delete(billId) > 0;
    }

    
    public Bill getBillById(int billId) throws Exception {
        return BillDAO.findById(billId);
    }
    
    
    public Bill getBillByAppointmentId(int appointmentId) throws Exception {
        return BillDAO.findByAppointmentId(appointmentId);
    }

    
    public List<Bill> getAllBills() throws Exception {
        return BillDAO.findAll();
    }

    
    public boolean markBillAsPaid(int billId, PaymentMethod method) throws Exception {
        Bill bill = BillDAO.findById(billId);
        if (bill == null) return false;
        bill.setStatus(BillStatus.PAID);
        bill.setPaymentMethod(method);
        return BillDAO.update(bill) > 0;
    }
}
