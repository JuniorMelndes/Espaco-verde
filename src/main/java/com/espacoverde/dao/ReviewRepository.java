package com.espacoverde.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.espacoverde.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
	
	Review findByIdReview(Long idReview);
	List<Review> findByIdProduct(Long idProduct);
}
