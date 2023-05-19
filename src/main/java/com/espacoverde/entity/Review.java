package com.espacoverde.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "TB_AVALIACAO")
@SequenceGenerator(name = "SEQ_AVALIACAO", sequenceName = "SEQ_AVALIACAO", allocationSize = 1)
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_AVALIACAO")
	@Column(name = "ID_AVALIACAO")
	private Long idReview;
	@Column(name = "TEXTO")
	private String text;
	@Column(name = "NOTA")
	private String rating;
	@Column(name = "ID_PRODUTO")
	private Long idProduct;
	@Column(name = "ID_AUTENTICACAO")
	private int idAuthenticator;
}