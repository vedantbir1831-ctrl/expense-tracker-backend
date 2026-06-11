package com.expensetracker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "budgets")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double totalBudget = 0.0;

    private Double foodLimit;
    private Double travelLimit;
    private Double booksLimit;
    private Double entertainmentLimit;
    private Double miscellaneousLimit;
    private Integer alertThreshold = 80;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    @PreUpdate
    protected void onUpdate() { updatedAt = LocalDateTime.now(); }

    public Budget() {}

    public Long getId() { return id; }
    public Double getTotalBudget() { return totalBudget; }
    public Double getFoodLimit() { return foodLimit; }
    public Double getTravelLimit() { return travelLimit; }
    public Double getBooksLimit() { return booksLimit; }
    public Double getEntertainmentLimit() { return entertainmentLimit; }
    public Double getMiscellaneousLimit() { return miscellaneousLimit; }
    public Integer getAlertThreshold() { return alertThreshold; }
    public User getUser() { return user; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public void setId(Long id) { this.id = id; }
    public void setTotalBudget(Double t) { this.totalBudget = t; }
    public void setFoodLimit(Double f) { this.foodLimit = f; }
    public void setTravelLimit(Double t) { this.travelLimit = t; }
    public void setBooksLimit(Double b) { this.booksLimit = b; }
    public void setEntertainmentLimit(Double e) { this.entertainmentLimit = e; }
    public void setMiscellaneousLimit(Double m) { this.miscellaneousLimit = m; }
    public void setAlertThreshold(Integer a) { this.alertThreshold = a; }
    public void setUser(User u) { this.user = u; }
    public void setUpdatedAt(LocalDateTime u) { this.updatedAt = u; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Double totalBudget = 0.0;
        private Double foodLimit;
        private Double travelLimit;
        private Double booksLimit;
        private Double entertainmentLimit;
        private Double miscellaneousLimit;
        private Integer alertThreshold = 80;
        private User user;

        public Builder totalBudget(Double t) { this.totalBudget = t; return this; }
        public Builder foodLimit(Double f) { this.foodLimit = f; return this; }
        public Builder travelLimit(Double t) { this.travelLimit = t; return this; }
        public Builder booksLimit(Double b) { this.booksLimit = b; return this; }
        public Builder entertainmentLimit(Double e) { this.entertainmentLimit = e; return this; }
        public Builder miscellaneousLimit(Double m) { this.miscellaneousLimit = m; return this; }
        public Builder alertThreshold(Integer a) { this.alertThreshold = a; return this; }
        public Builder user(User u) { this.user = u; return this; }

        public Budget build() {
            Budget b = new Budget();
            b.totalBudget = this.totalBudget;
            b.foodLimit = this.foodLimit;
            b.travelLimit = this.travelLimit;
            b.booksLimit = this.booksLimit;
            b.entertainmentLimit = this.entertainmentLimit;
            b.miscellaneousLimit = this.miscellaneousLimit;
            b.alertThreshold = this.alertThreshold;
            b.user = this.user;
            return b;
        }
    }
}