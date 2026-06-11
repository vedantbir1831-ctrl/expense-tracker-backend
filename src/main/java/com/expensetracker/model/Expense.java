package com.expensetracker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "expenses")
public class Expense {

    public enum Category {
        FOOD, TRAVEL, BOOKS, ENTERTAINMENT, MISCELLANEOUS
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(nullable = false)
    private LocalDate date;

    private String paymentMode;
    private String note;
    private Boolean isRecurring = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() { createdAt = LocalDateTime.now(); }

    public Expense() {}

    public Long getId() { return id; }
    public String getDescription() { return description; }
    public Double getAmount() { return amount; }
    public Category getCategory() { return category; }
    public LocalDate getDate() { return date; }
    public String getPaymentMode() { return paymentMode; }
    public String getNote() { return note; }
    public Boolean getIsRecurring() { return isRecurring; }
    public User getUser() { return user; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setId(Long id) { this.id = id; }
    public void setDescription(String description) { this.description = description; }
    public void setAmount(Double amount) { this.amount = amount; }
    public void setCategory(Category category) { this.category = category; }
    public void setDate(LocalDate date) { this.date = date; }
    public void setPaymentMode(String paymentMode) { this.paymentMode = paymentMode; }
    public void setNote(String note) { this.note = note; }
    public void setIsRecurring(Boolean isRecurring) { this.isRecurring = isRecurring; }
    public void setUser(User user) { this.user = user; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String description;
        private Double amount;
        private Category category;
        private LocalDate date;
        private String paymentMode;
        private String note;
        private Boolean isRecurring = false;
        private User user;

        public Builder description(String d) { this.description = d; return this; }
        public Builder amount(Double a) { this.amount = a; return this; }
        public Builder category(Category c) { this.category = c; return this; }
        public Builder date(LocalDate d) { this.date = d; return this; }
        public Builder paymentMode(String p) { this.paymentMode = p; return this; }
        public Builder note(String n) { this.note = n; return this; }
        public Builder isRecurring(Boolean r) { this.isRecurring = r; return this; }
        public Builder user(User u) { this.user = u; return this; }

        public Expense build() {
            Expense e = new Expense();
            e.description = this.description;
            e.amount = this.amount;
            e.category = this.category;
            e.date = this.date;
            e.paymentMode = this.paymentMode;
            e.note = this.note;
            e.isRecurring = this.isRecurring;
            e.user = this.user;
            return e;
        }
    }
}