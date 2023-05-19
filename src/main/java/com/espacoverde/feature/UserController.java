package com.espacoverde.feature;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.espacoverde.entity.Product;
import com.espacoverde.entity.Purchase;
import com.espacoverde.entity.Review;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
	private final ProductService 	productService;
	private final PurchaseService 	purchaseService;
	
	
	@GetMapping("/getAllProducts")
	public ResponseEntity<List<Product>> getAllProducts() throws IOException{
    	List<Product> products = productService.findAll();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }
	@GetMapping("getProduct/{idProduct}")
	public ResponseEntity<Product> getProductById(@PathVariable (value = "idProduct") Long idProduct){
		Product product = productService.getProduct(idProduct);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
		
	}
	@GetMapping("getProductByName/{productName}")
	public ResponseEntity<Product> getProductByName(@PathVariable (value = "productName") String productName){
		Product product = productService.getProductByName(productName);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
		
	}
	@PostMapping("/postPurchase")
	public ResponseEntity<Purchase> postPurchase(@RequestBody Purchase newPurchase){
		Purchase purchase = purchaseService.newPurchase(newPurchase);
		return new ResponseEntity<Purchase>(purchase, HttpStatus.CREATED);
	}
	@PostMapping("/postReview")
	public ResponseEntity<Review> postReview(@RequestBody Review newReview){
		Review review = purchaseService.newReview(newReview);
		return new ResponseEntity<Review>(review, HttpStatus.CREATED);
	}
	@GetMapping("getReview/{idReview}")
	public ResponseEntity<Review> getReview(@PathVariable (value = "idReview") Long idReview){
		Review review = purchaseService.getReview(idReview);
		return new ResponseEntity<Review>(review, HttpStatus.OK);
	}
	@GetMapping("getReviewByProduct/{productName}")
	public ResponseEntity<List<Review>> getReviewByProduct(@PathVariable (value = "productName") String productName){
	    List<Review> productReviews = purchaseService.getReviewByProduct(productName);
	    return new ResponseEntity<List<Review>>(productReviews, HttpStatus.OK);
	}
}
