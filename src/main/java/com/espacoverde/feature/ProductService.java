package com.espacoverde.feature;

import java.util.List;

import org.springframework.stereotype.Service;

import com.espacoverde.dao.ProductRepository;
import com.espacoverde.entity.Product;
import com.espacoverde.feature.exception.InvalidAttributeException;
import com.espacoverde.feature.exception.AlreadyExistsException;
import com.espacoverde.feature.exception.NotFoundException;
import com.espacoverde.util.Utils;

import lombok.RequiredArgsConstructor;


/**
 * Classe de serviço para manipulação de produtos.
 */
@Service
@RequiredArgsConstructor
public class ProductService {
	
	private final ProductRepository productRepository;
	private final Utils 			util;
	
	/**
     * Adiciona um novo produto.
     *
     * @param newProduct O novo produto a ser adicionado.
     * @return O produto adicionado.
     * @throws AlreadyExistsException     Se já existir um produto com o mesmo nome.
     * @throws InvalidAttributeException  Se o produto não tiver um nome ou a quantidade for negativa.
     * @throws RuntimeException          Se ocorrer um erro durante a execução do método.
     */
	public Product addNewProduct(Product newProduct) {
		try {
			if (productRepository.findByName(newProduct.getName()) != null) {
				 throw new AlreadyExistsException("Produto com o nome '" + newProduct.getName() + "' já existente");
			}
			if (newProduct.getName() == null) {
				throw new InvalidAttributeException("Produto precisa ter um Nome");
			}
			if (newProduct.getQuantity() < 0) {
				throw new InvalidAttributeException("Não é possível cadastrar um produto com quantidade negativa");
			}
			newProduct.setImage(util.imageToByteArray(newProduct.getImageName()));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return productRepository.save(newProduct);
	}
	
	/**
     * Obtém um produto pelo nome.
     *
     * @param productName O nome do produto a ser pesquisado.
     * @return O produto encontrado com o nome especificado.
     * @throws NotFoundException    Se não for encontrado um produto com o nome especificado.
     * @throws RuntimeException     Se ocorrer um erro durante a execução do método.
     */
	public Product getProductByName(String productName) {
		Product researchedProduct = new Product();
		try {
			researchedProduct = productRepository.findByName(productName);
			if (researchedProduct == null) {
				throw new NotFoundException("Produto com este nome não encontrado");
			}
			researchedProduct = productRepository.findByName(productName);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return researchedProduct;
	}
	
	/**
     * Obtém todos os produtos.
     *
     * @return Uma lista contendo todos os produtos existentes.
     */
	public List<Product> findAll(){
		return productRepository.findAll();
	}
	
	 /**
     * Obtém um produto pelo ID.
     *
     * @param productId O ID do produto a ser pesquisado.
     * @return O produto encontrado com o ID especificado.
     * @throws NotFoundException    Se não for encontrado um produto com o ID especificado.
     * @throws RuntimeException     Se ocorrer um erro durante a execução do método.
     */
	public Product getProduct(Long productId) {
		Product researchedProduct = new Product();
		try {
			if (productRepository.findByIdProduct(productId) == null) {
				throw new NotFoundException("Produto com este Id não encontrado");
			}
			researchedProduct = productRepository.findByIdProduct(productId);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return researchedProduct;	
	}
	
	/**
     * Atualiza um produto existente.
     *
     * @param productId  O ID do produto a ser atualizado.
     * @param newProduct O novo objeto de produto contendo as informações atualizadas.
     * @return O produto atualizado.
     * @throws NotFoundException         Se não for encontrado um produto com o ID especificado.
     * @throws InvalidAttributeException  Se o produto não tiver um nome válido, ou se já existir um produto com o mesmo nome.
     * @throws RuntimeException          Se ocorrer um erro durante a execução do método.
     */
	public Product putProduct(Long productId, Product newProduct) {
		Product oldProduct = productRepository.findByIdProduct(productId);
		try {
			if (productRepository.findByIdProduct(productId) == null) {
				throw new NotFoundException("Produto com este Id não encontrado");
			}
			oldProduct = productRepository.findByIdProduct(productId);
			if (newProduct.getName() == null) {
				throw new InvalidAttributeException("Produto precisa ter um Nome");
			}
			if (!newProduct.getName().equals(oldProduct.getName()) && productRepository.findByName(newProduct.getName()) != null) {
				throw new AlreadyExistsException("Já existe um produto cadastrado com o nome '" + newProduct.getName() + "'");
			}
			if (newProduct.getQuantity() < 0) {
				throw new InvalidAttributeException("Não é possível cadastrar um produto com quantidade negativa");
			}
			oldProduct.setName(newProduct.getName());
			oldProduct.setDescription(newProduct.getDescription());
			oldProduct.setQuantity(newProduct.getQuantity());
			oldProduct.setAvailability(newProduct.getAvailability());
			if (!(newProduct.getImageName() == null)) {
				oldProduct.setImageName(newProduct.getImageName());
				oldProduct.setImage(util.imageToByteArray(newProduct.getImageName()));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		productRepository.save(oldProduct);
		return oldProduct;
	}
	
	/**
     * Exclui um produto pelo ID.
     *
     * @param productId O ID do produto a ser excluído.
     * @throws NotFoundException    Se não for encontrado um produto com o ID especificado.
     * @throws RuntimeException     Se ocorrer um erro durante a execução do método.
     */
	public void deleteProduct(Long productId) {
		try {
			if (productRepository.findByIdProduct(productId) == null) {
				throw new NotFoundException("Produto com este Id não encontrado");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		productRepository.deleteById(productId);
	}
}
