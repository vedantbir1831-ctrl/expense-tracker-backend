package com.expensetracker.controller;

import com.expensetracker.dto.ExpenseRequest;
import com.expensetracker.model.*;
import com.expensetracker.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;
    private final UserService userService;

    private User getUser(UserDetails ud) {
        return userService.findByEmail(ud.getUsername());
    }

    @GetMapping
    public ResponseEntity<List<Expense>> getAll(@AuthenticationPrincipal UserDetails ud) {
        return ResponseEntity.ok(expenseService.getAll(getUser(ud)));
    }

    @PostMapping
    public ResponseEntity<Expense> create(@AuthenticationPrincipal UserDetails ud,
                                           @RequestBody ExpenseRequest req) {
        return ResponseEntity.ok(expenseService.create(getUser(ud), req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> update(@AuthenticationPrincipal UserDetails ud,
                                           @PathVariable Long id,
                                           @RequestBody ExpenseRequest req) {
        return ResponseEntity.ok(expenseService.update(id, getUser(ud), req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@AuthenticationPrincipal UserDetails ud,
                                        @PathVariable Long id) {
        expenseService.delete(id, getUser(ud));
        return ResponseEntity.noContent().build();
    }
}