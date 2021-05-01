package com.example.collegeproject.Adapters;

public class itemModel {
    String name,issueDate,returnDate,quantity;

    public itemModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public itemModel(String name, String issueDate, String returnDate, String quantity) {
        this.name = name;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
        this.quantity = quantity;
    }
}
