package com.expensetracker.service;

import com.expensetracker.dto.ExpenseRequest;
import com.expensetracker.model.*;
import com.expensetracker.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public List<Expense> getAll(User user) {
        return expenseRepository.findByUserOrderByDateDesc(user);
    }

    public Expense create(User user, ExpenseRequest req) {
        Expense expense = Expense.builder()
                .description(req.getDescription())
                .amount(req.getAmount())
                .category(req.getCategory())
                .date(req.getDate())
                .paymentMode(req.getPaymentMode())
                .note(req.getNote())
                .isRecurring(req.getIsRecurring())
                .user(user)
                .build();
        return expenseRepository.save(expense);
    }

    public Expense update(Long id, User user, ExpenseRequest req) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
        if (!expense.getUser().getId().equals(user.getId()))
            throw new RuntimeException("Unauthorized");
        expense.setDescription(req.getDescription());
        expense.setAmount(req.getAmount());
        expense.setCategory(req.getCategory());
        expense.setDate(req.getDate());
        expense.setPaymentMode(req.getPaymentMode());
        expense.setNote(req.getNote());
        expense.setIsRecurring(req.getIsRecurring());
        return expenseRepository.save(expense);
    }

    public void delete(Long id, User user) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
        if (!expense.getUser().getId().equals(user.getId()))
            throw new RuntimeException("Unauthorized");
        expenseRepository.delete(expense);
    }

    public List<Expense> getByDateRange(User user, java.time.LocalDate start, java.time.LocalDate end) {
        return expenseRepository.findByUserAndDateBetweenOrderByDateDesc(user, start, end);
    }
}