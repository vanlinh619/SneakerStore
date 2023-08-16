package com.ale.sneakerstoreapi.repository;

import com.ale.sneakerstoreapi.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("select r from Review r where r.content like %:keyword% or r.title like %:keyword%")
    List<Review> searchKeyword(String keyword);
}