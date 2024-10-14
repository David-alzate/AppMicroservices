package com.davidalzate.inventory_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.davidalzate.inventory_service.model.dtos.ProductRequest;
import com.davidalzate.inventory_service.model.dtos.ProductoResponse;
import com.davidalzate.inventory_service.services.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService ProductService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void addProduct(@RequestBody ProductRequest productRequest) {
		this.ProductService.addProduct(productRequest);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ProductoResponse> getAllProducts() {
		return this.ProductService.getAllProducts();
	}

}
