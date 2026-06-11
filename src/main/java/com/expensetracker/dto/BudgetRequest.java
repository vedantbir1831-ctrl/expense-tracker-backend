package com.expensetracker.dto;

public class BudgetRequest {
    private Double totalBudget;
    private Double foodLimit;
    private Double travelLimit;
    private Double booksLimit;
    private Double entertainmentLimit;
    private Double miscellaneousLimit;
    private Integer alertThreshold;

    public Double getTotalBudget() { return totalBudget; }
    public Double getFoodLimit() { return foodLimit; }
    public Double getTravelLimit() { return travelLimit; }
    public Double getBooksLimit() { return booksLimit; }
    public Double getEntertainmentLimit() { return entertainmentLimit; }
    public Double getMiscellaneousLimit() { return miscellaneousLimit; }
    public Integer getAlertThreshold() { return alertThreshold; }

    public void setTotalBudget(Double totalBudget) { this.totalBudget = totalBudget; }
    public void setFoodLimit(Double foodLimit) { this.foodLimit = foodLimit; }
    public void setTravelLimit(Double travelLimit) { this.travelLimit = travelLimit; }
    public void setBooksLimit(Double booksLimit) { this.booksLimit = booksLimit; }
    public void setEntertainmentLimit(Double e) { this.entertainmentLimit = e; }
    public void setMiscellaneousLimit(Double m) { this.miscellaneousLimit = m; }
    public void setAlertThreshold(Integer alertThreshold) { this.alertThreshold = alertThreshold; }
}