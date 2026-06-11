package com.expensetracker.dto;

import java.time.LocalDate;

public class IncomeRequest {
    private String source;
    private Double amount;
    private LocalDate date;
    private String note;

    public String getSource() { return source; }
    public Double getAmount() { return amount; }
    public LocalDate getDate() { return date; }
    public String getNote() { return note; }

    public void setSource(String source) { this.source = source; }
    public void setAmount(Double amount) { this.amount = amount; }
    public void setDate(LocalDate date) { this.date = date; }
    public void setNote(String note) { this.note = note; }
}