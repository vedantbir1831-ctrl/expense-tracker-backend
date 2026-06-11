package com.expensetracker.controller;

import com.expensetracker.dto.IncomeRequest;
import com.expensetracker.model.*;
import com.expensetracker.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/income")
@RequiredArgsConstructor
public class IncomeController {

    private final IncomeService incomeService;
    private final UserService userService;

    private User getUser(UserDetails ud) {
        return userService.findByEmail(ud.getUsername());
    }

    @GetMapping
    public ResponseEntity<List<Income>> getAll(@AuthenticationPrincipal UserDetails ud) {
        return ResponseEntity.ok(incomeService.getAll(getUser(ud)));
    }

    @PostMapping
    public ResponseEntity<Income> create(@AuthenticationPrincipal UserDetails ud,
                                          @RequestBody IncomeRequest req) {
        return ResponseEntity.ok(incomeService.create(getUser(ud), req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Income> update(@AuthenticationPrincipal UserDetails ud,
                                          @PathVariable Long id,
                                          @RequestBody IncomeRequest req) {
        return ResponseEntity.ok(incomeService.update(id, getUser(ud), req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@AuthenticationPrincipal UserDetails ud,
                                        @PathVariable Long id) {
        incomeService.delete(id, getUser(ud));
        return ResponseEntity.noContent().build();
    }
}