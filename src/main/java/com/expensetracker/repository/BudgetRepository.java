package com.expensetracker.repository;

import com.expensetracker.model.Budget;
import com.expensetracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    Optional<Budget> findByUser(User user);
}