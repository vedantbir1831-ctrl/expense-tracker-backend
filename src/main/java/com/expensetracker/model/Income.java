package com.expensetracker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "income")
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String source;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private LocalDate date;

    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() { createdAt = LocalDateTime.now(); }

    public Income() {}

    public Long getId() { return id; }
    public String getSource() { return source; }
    public Double getAmount() { return amount; }
    public LocalDate getDate() { return date; }
    public String getNote() { return note; }
    public User getUser() { return user; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setId(Long id) { this.id = id; }
    public void setSource(String source) { this.source = source; }
    public void setAmount(Double amount) { this.amount = amount; }
    public void setDate(LocalDate date) { this.date = date; }
    public void setNote(String note) { this.note = note; }
    public void setUser(User user) { this.user = user; }
    public void setCreatedAt(LocalDateTime c) { this.createdAt = c; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String source;
        private Double amount;
        private LocalDate date;
        private String note;
        private User user;

        public Builder source(String s) { this.source = s; return this; }
        public Builder amount(Double a) { this.amount = a; return this; }
        public Builder date(LocalDate d) { this.date = d; return this; }
        public Builder note(String n) { this.note = n; return this; }
        public Builder user(User u) { this.user = u; return this; }

        public Income build() {
            Income i = new Income();
            i.source = this.source;
            i.amount = this.amount;
            i.date = this.date;
            i.note = this.note;
            i.user = this.user;
            return i;
        }
    }
}