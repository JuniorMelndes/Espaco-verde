package com.espacoverde.entity;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "TB_COMPRAS")
@SequenceGenerator(name = "SEQ_COMPRAS", sequenceName = "SEQ_COMPRAS", allocationSize = 1)
public class Purchase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_COMPRAS")
	@Column(name = "ID_COMPRA")
	private Long idPurchase;
	@Column(name = "DATA_COMPRA")
	private String date;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "VALIDADA")
	private String validated;
	@OneToMany
	@JoinTable(	name = "TB_COMPRA_PRODUTO",
				schema="XXXX",
				joinColumns = @JoinColumn(name = "ID_COMPRA"),
				inverseJoinColumns = @JoinColumn(name = "ID_PRODUTO"))
	private List<Product> products;
	
	public Purchase() {
		this.validated = "0";
	}
	
}
