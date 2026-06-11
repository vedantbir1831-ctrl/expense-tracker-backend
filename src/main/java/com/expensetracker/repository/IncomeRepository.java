package com.expensetracker.repository;

import com.expensetracker.model.Income;
import com.expensetracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface IncomeRepository extends JpaRepository<Income, Long> {
    List<Income> findByUserOrderByDateDesc(User user);

    @Query("SELECT SUM(i.amount) FROM Income i WHERE i.user = :user " +
           "AND YEAR(i.date) = :year AND MONTH(i.date) = :month")
    Double sumByUserAndYearAndMonth(
        @Param("user") User user,
        @Param("year") int year,
        @Param("month") int month);
}