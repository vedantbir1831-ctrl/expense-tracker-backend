package com.expensetracker.dto;

import com.expensetracker.model.Expense;
import java.time.LocalDate;

public class ExpenseRequest {
    private String description;
    private Double amount;
    private Expense.Category category;
    private LocalDate date;
    private String paymentMode;
    private String note;
    private Boolean isRecurring = false;

    public String getDescription() { return description; }
    public Double getAmount() { return amount; }
    public Expense.Category getCategory() { return category; }
    public LocalDate getDate() { return date; }
    public String getPaymentMode() { return paymentMode; }
    public String getNote() { return note; }
    public Boolean getIsRecurring() { return isRecurring; }

    public void setDescription(String description) { this.description = description; }
    public void setAmount(Double amount) { this.amount = amount; }
    public void setCategory(Expense.Category category) { this.category = category; }
    public void setDate(LocalDate date) { this.date = date; }
    public void setPaymentMode(String paymentMode) { this.paymentMode = paymentMode; }
    public void setNote(String note) { this.note = note; }
    public void setIsRecurring(Boolean isRecurring) { this.isRecurring = isRecurring; }
}