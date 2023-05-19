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
@Table(name = "TB_PRODUTO")
@SequenceGenerator(name = "SEQ_PRODUTOS", sequenceName = "SEQ_PRODUTOS", allocationSize = 1)
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PRODUTOS")
	@Column(name = "ID_PRODUTO")
	private Long idProduct;
	@Column(name = "NOME")
	private String name;
	@Column(name = "DESCRICAO")
	private String description;
	@Column(name = "QUANTIDADE")
	private Long quantity;
	@Column(name = "DISPONIBILIDADE")
	private String availability;
	@Column(name = "NOME_IMAGEM")
	private String imageName;
	@Column(name = "IMAGEM")
	private byte[] image;
	
}
