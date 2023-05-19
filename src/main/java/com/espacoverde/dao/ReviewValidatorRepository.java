package com.espacoverde.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.espacoverde.entity.ReviewValidator;

@Repository
public interface ReviewValidatorRepository extends JpaRepository<ReviewValidator, Long> {
	
	@Query(
			value = "SELECT * FROM TB_VALIDA_AVALIACAO u WHERE u.ID_PRODUTO = :idProduct AND u.ID_GERADO = :idAuthenticator", 
			nativeQuery = true)
	ReviewValidator findAuthenticator(Long idProduct, int idAuthenticator);
}
