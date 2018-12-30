package com.mrgreenstar.litclub.Repositories;

import com.mrgreenstar.litclub.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
