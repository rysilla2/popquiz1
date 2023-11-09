package uap.storeapp.models;

public class Receipt {

    private int transactionId;
    private Double total;

    @Override
    public String toString() {
        return "Receipt{" +
                "transactionId=" + transactionId +
                ", total=" + total +
                '}';
    }

    public Receipt(int transactionId, Double total) {
        this.transactionId = transactionId;
        this.total = total;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
