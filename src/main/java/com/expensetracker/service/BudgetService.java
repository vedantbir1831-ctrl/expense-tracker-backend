package com.expensetracker.service;

import com.expensetracker.dto.BudgetRequest;
import com.expensetracker.model.*;
import com.expensetracker.repository.BudgetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BudgetService {

    private final BudgetRepository budgetRepository;

    public Budget getOrCreate(User user) {
        return budgetRepository.findByUser(user).orElse(
            Budget.builder().user(user).totalBudget(0.0).alertThreshold(80).build()
        );
    }

    public Budget save(User user, BudgetRequest req) {
        Budget budget = budgetRepository.findByUser(user)
                .orElse(Budget.builder().user(user).build());
        budget.setTotalBudget(req.getTotalBudget());
        budget.setFoodLimit(req.getFoodLimit());
        budget.setTravelLimit(req.getTravelLimit());
        budget.setBooksLimit(req.getBooksLimit());
        budget.setEntertainmentLimit(req.getEntertainmentLimit());
        budget.setMiscellaneousLimit(req.getMiscellaneousLimit());
        if (req.getAlertThreshold() != null)
            budget.setAlertThreshold(req.getAlertThreshold());
        return budgetRepository.save(budget);
    }
}