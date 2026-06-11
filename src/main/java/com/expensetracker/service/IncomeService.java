package com.expensetracker.service;

import com.expensetracker.dto.IncomeRequest;
import com.expensetracker.model.*;
import com.expensetracker.repository.IncomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IncomeService {

    private final IncomeRepository incomeRepository;

    public List<Income> getAll(User user) {
        return incomeRepository.findByUserOrderByDateDesc(user);
    }

    public Income create(User user, IncomeRequest req) {
        Income income = Income.builder()
                .source(req.getSource())
                .amount(req.getAmount())
                .date(req.getDate())
                .note(req.getNote())
                .user(user)
                .build();
        return incomeRepository.save(income);
    }

    public Income update(Long id, User user, IncomeRequest req) {
        Income income = incomeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Income not found"));
        if (!income.getUser().getId().equals(user.getId()))
            throw new RuntimeException("Unauthorized");
        income.setSource(req.getSource());
        income.setAmount(req.getAmount());
        income.setDate(req.getDate());
        income.setNote(req.getNote());
        return incomeRepository.save(income);
    }

    public void delete(Long id, User user) {
        Income income = incomeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Income not found"));
        if (!income.getUser().getId().equals(user.getId()))
            throw new RuntimeException("Unauthorized");
        incomeRepository.delete(income);
    }
}