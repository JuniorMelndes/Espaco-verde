package com.espacoverde.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "TB_VALIDA_AVALIACAO")
@SequenceGenerator(name = "SEQ_VALIDA_AVALIACAO", sequenceName = "SEQ_VALIDA_AVALIACAO", allocationSize = 1)
public class ReviewValidator {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VALIDA_AVALIACAO")
	@Column(name = "ID_VALIDA_AVALIACAO")
	private Long idValidatorReview;
	@Column(name = "ID_PRODUTO")
	private Long idProduct;
	@Column(name = "ID_GERADO")
	private int idGenerate;
	@Column(name = "BIT_EXCL_LOGIC")
	private String logicExclusion;
	
	public ReviewValidator() {
		this.logicExclusion = "0";
	}
}