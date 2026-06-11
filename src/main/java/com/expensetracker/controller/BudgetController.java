package com.expensetracker.controller;

import com.expensetracker.dto.BudgetRequest;
import com.expensetracker.model.*;
import com.expensetracker.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/budget")
@RequiredArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;
    private final UserService userService;

    private User getUser(UserDetails ud) {
        return userService.findByEmail(ud.getUsername());
    }

    @GetMapping
    public ResponseEntity<Budget> get(@AuthenticationPrincipal UserDetails ud) {
        return ResponseEntity.ok(budgetService.getOrCreate(getUser(ud)));
    }

    @PostMapping
    public ResponseEntity<Budget> save(@AuthenticationPrincipal UserDetails ud,
                                        @RequestBody BudgetRequest req) {
        return ResponseEntity.ok(budgetService.save(getUser(ud), req));
    }
}