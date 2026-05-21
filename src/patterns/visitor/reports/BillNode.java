package patterns.visitor.reports;

import model.Bill;


public class BillNode implements ReportNode {

    private final Bill bill;

    public BillNode(Bill bill) {
        this.bill = bill;
    }

    public Bill getBill() {
        return bill;
    }

    @Override
    public void accept(ReportVisitor visitor) {
        visitor.visit(this);
    }
}
