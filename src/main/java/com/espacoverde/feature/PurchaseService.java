package com.espacoverde.feature;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.espacoverde.dao.ProductRepository;
import com.espacoverde.dao.PurchaseRepository;
import com.espacoverde.dao.ReviewRepository;
import com.espacoverde.dao.ReviewValidatorRepository;
import com.espacoverde.entity.Product;
import com.espacoverde.entity.Purchase;
import com.espacoverde.entity.Review;
import com.espacoverde.entity.ReviewValidator;
import com.espacoverde.feature.exception.AlreadyExistsException;
import com.espacoverde.feature.exception.InvalidAttributeException;
import com.espacoverde.feature.exception.NotFoundException;
import com.espacoverde.util.Utils;

import lombok.RequiredArgsConstructor;

/**
 * Classe de serviço para manipulação de compras e avaliações.
 */
@Service
@RequiredArgsConstructor
public class PurchaseService {
	private final PurchaseRepository 		purchaseRepository;
	private final ProductRepository 		productRepository;
	private final ReviewValidatorRepository	reviewValidatorRepository;
	private final ReviewRepository			reviewRepository;
	private final Utils						util;
	
	/**
     * Cria uma nova compra.
     *
     * @param purchase A compra a ser criada.
     * @return A compra criada.
     * @throws InvalidAttributeException  Se a compra não tiver um e-mail associado ou se o e-mail for inválido.
     * @throws AlreadyExistsException     Se já existir uma compra não validada com o mesmo e-mail.
     * @throws NotFoundException         Se algum produto da compra não for encontrado.
     * @throws RuntimeException          Se ocorrer um erro durante a execução do método.
     */
	public Purchase newPurchase(Purchase purchase) {
		Purchase newPurchase = purchase;
		try {
			List<Purchase> purchaseList = purchaseRepository.findByEmail(purchase.getEmail());
			if (newPurchase.getEmail() == null) {
				throw new InvalidAttributeException("Uma compra precisa ter um e-mail associado!");
			}
			if (!util.isValidEmail(newPurchase.getEmail())) {
				throw new InvalidAttributeException("E-mail inválido!");
			}
			if (!purchaseList.isEmpty()) {
				for (Purchase purchaseAux : purchaseList) {
					if (purchaseAux.getValidated().equals("0")) {
						throw new AlreadyExistsException("Já existe uma compra não validada com este e-mail");
					}
				}
			}
			for (Product product : purchase.getProducts()) {
				if (productRepository.findByIdProduct(product.getIdProduct()) == null) {
					throw new NotFoundException("Produto com Id " + product.getIdProduct() + " não encontrado");
				}
			}
			newPurchase.setDate(util.getCurrentTime());
			purchaseRepository.save(newPurchase);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return newPurchase;
	}
	
	 /**
     * Atualiza uma compra existente.
     *
     * @param newPurchase O novo objeto de compra contendo as informações atualizadas.
     * @param purchaseId  O ID da compra a ser atualizada.
     * @return A compra atualizada.
     * @throws NotFoundException         Se a compra com o ID especificado não for encontrada.
     * @throws InvalidAttributeException  Se a compra não tiver um e-mail associado, se for alterado o e-mail associado à compra ou se for alterado o horário da compra.
     * @throws RuntimeException          Se ocorrer um erro durante a execução do método.
     */
	public Purchase putPurchase(Purchase newPurchase, Long purchaseId) {
		Purchase oldPurchase = purchaseRepository.findByIdPurchase(purchaseId);
		try {
			if (oldPurchase == null) {
				throw new NotFoundException("Compra com este Id não encontrado");
			}
			if (newPurchase.getEmail() == null) {
				throw new InvalidAttributeException("Uma compra precisa ter um e-mail associado");
			}
			if (!newPurchase.getEmail().equals(oldPurchase.getEmail())) {
				throw new InvalidAttributeException("Não é permitido mudar o e-mail associado a uma compra");
			}
			if (!(newPurchase.getDate() == null)) {
				throw new InvalidAttributeException("Não é permitido mudar o horário da compra");
			}
			for (Product product : newPurchase.getProducts()) {
				if (productRepository.findByIdProduct(product.getIdProduct()) == null) {
					throw new NotFoundException("Produto com Id " + product.getIdProduct() + " não encontrado");
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		oldPurchase.setDate(newPurchase.getDate());
		oldPurchase.setValidated(newPurchase.getValidated());
		oldPurchase.setProducts(newPurchase.getProducts());
		purchaseRepository.save(oldPurchase);
		return oldPurchase;
	}
	
	/**
     * Exclui uma compra pelo ID.
     *
     * @param idPurchase O ID da compra a ser excluída.
     * @throws NotFoundException    Se a compra com o ID especificado não for encontrada.
     * @throws RuntimeException     Se ocorrer um erro durante a execução do método.
     */
	public void deletePurchase(Long idPurchase) {
		Purchase deletedPurchase = purchaseRepository.findByIdPurchase(idPurchase);
		try {
			if (deletedPurchase == null) {
				throw new NotFoundException("Compra com este Id não encontrado");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		purchaseRepository.deleteById(idPurchase);
	}
	
	/**
     * Obtém uma compra pelo ID.
     *
     * @param idPurchase O ID da compra a ser obtida.
     * @return A compra encontrada com o ID especificado.
     * @throws NotFoundException    Se a compra com o ID especificado não for encontrada.
     * @throws RuntimeException     Se ocorrer um erro durante a execução do método.
     */
	public Purchase getPurchase(Long idPurchase) {
		Purchase purchase = purchaseRepository.findByIdPurchase(idPurchase);
		try {
			if (purchase == null) {
				throw new NotFoundException("Compra com este Id não encontrado");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return purchase;
	}
	
	/**
     * Valida uma compra.
     *
     * @param idPurchase O ID da compra a ser validada.
     * @throws NotFoundException         Se a compra com o ID especificado não for encontrada.
     * @throws AlreadyExistsException     Se a compra já tiver sido validada.
     * @throws NotFoundException         Se algum produto da compra não estiver disponível.
     * @throws RuntimeException          Se ocorrer um erro durante a execução do método.
     */
	public void validatePurchase(Long idPurchase) {
		Purchase purchase = purchaseRepository.findByIdPurchase(idPurchase);
		try {
			if (purchase == null) {
				throw new NotFoundException("Compra com este Id não encontrado");
			}
			if (purchase.getValidated().equals("1")) {
				throw new AlreadyExistsException("Compra já foi validada");
			}
			for (Product product : purchase.getProducts()) {
				Long quantityProduct = product.getQuantity();
				if (quantityProduct <= 0) {
					product.setAvailability("0");
					productRepository.save(product);
					throw new NotFoundException("Produto '"+ product.getName() +"' não está disponível!");
				}
				product.setQuantity(quantityProduct - 1);
			}
			purchase.setValidated("1");
			purchaseRepository.save(purchase);
			HashSet<Product> conjunto = new HashSet<>(purchase.getProducts());
			for (Product product : conjunto) {
				ReviewValidator reviewValidator = new ReviewValidator();
				reviewValidator.setIdProduct(product.getIdProduct());
				Random random = new Random();
		        int randomId = random.nextInt(999999);
		        reviewValidator.setIdGenerate(randomId);
		        reviewValidatorRepository.save(reviewValidator);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
     * Cria uma nova avaliação.
     *
     * @param newReview A nova avaliação a ser criada.
     * @return A avaliação criada.
     * @throws NotFoundException     Se o código fornecido for inválido ou estiver associado ao produto errado.
     * @throws AlreadyExistsException Se o código fornecido já tiver sido usado.
     * @throws RuntimeException      Se ocorrer um erro durante a execução do método.
     */
	public Review newReview(Review newReview) {
		ReviewValidator reviewValidator = reviewValidatorRepository.findAuthenticator(newReview.getIdProduct(), newReview.getIdAuthenticator());
		try {
			if (reviewValidator == null) {
				throw new NotFoundException("Código é inválido ou está associado ao produto errado");
			}
			if (reviewValidator.getLogicExclusion().equals("1")) {
				throw new AlreadyExistsException("O código fornecido já foi usado");
			}
			reviewValidator.setLogicExclusion("1");
			reviewRepository.save(newReview);
			reviewValidatorRepository.save(reviewValidator);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return newReview;
	}
	
	/**
     * Obtém uma avaliação pelo ID.
     *
     * @param idReview O ID da avaliação a ser obtida.
     * @return A avaliação encontrada com o ID especificado.
     * @throws NotFoundException    Se a avaliação com o ID especificado não for encontrada.
     * @throws RuntimeException     Se ocorrer um erro durante a execução do método.
     */
	public Review getReview(Long idReview) {
		Review review = reviewRepository.findByIdReview(idReview);
		try {
			if (review == null) {
				throw new NotFoundException("Avaliação com este Id não encontrada");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return review;	
	}
	
	/**
     * Obtém todas as avaliações de um produto.
     *
     * @param productName O nome do produto.
     * @return Uma lista contendo todas as avaliações do produto especificado.
     * @throws NotFoundException    Se o produto com o nome especificado não for encontrado ou se o produto não tiver avaliações.
     * @throws RuntimeException     Se ocorrer um erro durante a execução do método.
     */
	public List<Review> getReviewByProduct(String productName){
		try {
			if (productRepository.findByName(productName) == null) {
				throw new NotFoundException("Este produto não existe!");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		Long idProduct = productRepository.findByName(productName).getIdProduct();
		List<Review> productReviews = reviewRepository.findByIdProduct(idProduct);
		try {
			
			if (productReviews.isEmpty()) {
				throw new NotFoundException("Este produto ainda não possui avaliações");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return productReviews;
	}
	
	
}
