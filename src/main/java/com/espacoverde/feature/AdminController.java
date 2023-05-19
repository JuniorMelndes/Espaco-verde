package com.espacoverde.feature;


import org.apache.poi.ss.formula.functions.T;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.espacoverde.entity.Product;
import com.espacoverde.entity.Purchase;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
	
	private final ProductService 	productService;
	
	private final PurchaseService	purchaseService;
	
	@PostMapping("/postProduct")
    public ResponseEntity<Product> postProduct(@RequestBody Product newProduct){
    	Product product = productService.addNewProduct(newProduct);
		return new ResponseEntity<Product>(product, HttpStatus.CREATED);
    }
	@PutMapping("putProduct/{idProduct}")
	public ResponseEntity<Product> putProduct(@PathVariable (value = "idProduct") Long idProduct, @RequestBody Product newProduct){
		Product product = productService.putProduct(idProduct, newProduct);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
		
	}
	@DeleteMapping("deleteProduct/{idProduct}")
	public ResponseEntity<T> deleteProduct(@PathVariable (value = "idProduct") Long idProduct){
		productService.deleteProduct(idProduct);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	@PutMapping("putPurchase/{idPurchase}")
	public ResponseEntity<Purchase> putPurchase(@PathVariable (value = "idPurchase") Long idPurchase, @RequestBody Purchase newPurchase){
		Purchase purchase = purchaseService.putPurchase(newPurchase, idPurchase);
		return new ResponseEntity<Purchase>(purchase, HttpStatus.OK);
	}
	@DeleteMapping("deletePurchase/{idPurchase}")
	public ResponseEntity<T> deletePurchase(@PathVariable (value = "idPurchase") Long idPurchase){
		purchaseService.deletePurchase(idPurchase);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	@GetMapping("getPurchase/{idPurchase}")
	public ResponseEntity<Purchase> getPurchase(@PathVariable (value = "idPurchase") Long idPurchase){
		Purchase purchase = purchaseService.getPurchase(idPurchase);
		return new ResponseEntity<Purchase>(purchase, HttpStatus.OK);
	}
	@PutMapping("validatePurchase/{idPurchase}")
	public ResponseEntity<Purchase> validatePurchase(@PathVariable (value = "idPurchase") Long idPurchase){
		purchaseService.validatePurchase(idPurchase);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
