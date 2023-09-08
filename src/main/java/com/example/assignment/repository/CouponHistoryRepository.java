package com.example.assignment.repository;


import com.example.assignment.domain.CouponHistory;
import com.example.assignment.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CouponHistoryRepository extends JpaRepository<CouponHistory, Long> {
    List<CouponHistory> findByUsers(Users user);
}
