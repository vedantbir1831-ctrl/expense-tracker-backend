package com.expensetracker.controller;

import com.expensetracker.model.Expense;
import com.expensetracker.model.Income;
import com.expensetracker.model.User;
import com.expensetracker.repository.ExpenseRepository;
import com.expensetracker.repository.IncomeRepository;
import com.expensetracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private UserService userService;

    private User getUser(UserDetails ud) {
        return userService.findByEmail(ud.getUsername());
    }

    @GetMapping("/monthly")
    public ResponseEntity<Map<String, Object>> monthly(
            @AuthenticationPrincipal UserDetails ud,
            @RequestParam(defaultValue = "0") int year,
            @RequestParam(defaultValue = "0") int month) {

        User user = getUser(ud);
        LocalDate now = LocalDate.now();
        int y = year == 0 ? now.getYear() : year;
        int m = month == 0 ? now.getMonthValue() : month;

        LocalDate start = LocalDate.of(y, m, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        List<Expense> expenses =
            expenseRepository.findByUserAndDateBetweenOrderByDateDesc(user, start, end);
        Double totalExp = expenseRepository.sumByUserAndYearAndMonth(user, y, m);
        Double totalInc = incomeRepository.sumByUserAndYearAndMonth(user, y, m);
        List<Object[]> catBreakdown =
            expenseRepository.sumByCategoryForMonth(user, y, m);

        double te = totalExp != null ? totalExp : 0.0;
        double ti = totalInc != null ? totalInc : 0.0;

        Map<String, Object> result = new HashMap<>();
        result.put("expenses", expenses);
        result.put("totalExpenses", te);
        result.put("totalIncome", ti);
        result.put("savings", ti - te);
        result.put("categoryBreakdown", catBreakdown);
        result.put("year", y);
        result.put("month", m);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/summary")
    public ResponseEntity<Map<String, Object>> summary(
            @AuthenticationPrincipal UserDetails ud) {

        User user = getUser(ud);
        List<Expense> allExpenses =
            expenseRepository.findByUserOrderByDateDesc(user);
        List<Income> allIncome =
            incomeRepository.findByUserOrderByDateDesc(user);

        double totalExp = allExpenses.stream()
            .mapToDouble(Expense::getAmount).sum();
        double totalInc = allIncome.stream()
            .mapToDouble(Income::getAmount).sum();

        Map<String, Object> result = new HashMap<>();
        result.put("totalExpenses", totalExp);
        result.put("totalIncome", totalInc);
        result.put("savings", totalInc - totalExp);
        result.put("expenseCount", allExpenses.size());
        result.put("incomeCount", allIncome.size());

        return ResponseEntity.ok(result);
    }
}