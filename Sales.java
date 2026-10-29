
package model;

import java.time.LocalDateTime;

public class Sales {
    
    private int saleId;
    private int staffId;
    private LocalDateTime saleDate;
    private double total;
    private double moneyPaid;
    private double changeAmount;

    // Constructor for a new sale
    public Sales(int staffId,
                 LocalDateTime saleDate,
                 double total,
                 double moneyPaid,
                 double changeAmount) {

        this.staffId = staffId;
        this.saleDate = saleDate;
        this.total = total;
        this.moneyPaid = moneyPaid;
        this.changeAmount = changeAmount;
    }

    // Constructor for an existing sale from database
    public Sales(int saleId,
                 int staffId,
                 LocalDateTime saleDate,
                 double total,
                 double moneyPaid,
                 double changeAmount) {

        this.saleId = saleId;
        this.staffId = staffId;
        this.saleDate = saleDate;
        this.total = total;
        this.moneyPaid = moneyPaid;
        this.changeAmount = changeAmount;
    }

    // Constructor without staff ID
    public Sales(LocalDateTime saleDate,
                 double total,
                 double moneyPaid,
                 double changeAmount) {

        this.saleDate = saleDate;
        this.total = total;
        this.moneyPaid = moneyPaid;
        this.changeAmount = changeAmount;
    }

    public int getSaleId() {
        return saleId;
    }

    public int getStaffId() {
        return staffId;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public double getTotal() {
        return total;
    }

    public double getMoneyPaid() {
        return moneyPaid;
    }

    public double getChangeAmount() {
        return changeAmount;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setMoneyPaid(double moneyPaid) {
        this.moneyPaid = moneyPaid;
    }

    public void setChangeAmount(double changeAmount) {
        this.changeAmount = changeAmount;
    }
}
