package com.espacoverde.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.espacoverde.entity.Purchase;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
	
	Purchase findByIdPurchase(Long idPurchase);
	List<Purchase> findByEmail(String email);
}
