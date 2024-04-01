package edu.pnu.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Product;
import edu.pnu.domain.dto.ProductDetailListDTO;
import edu.pnu.domain.dto.ProductDetatilInputDTO;
import edu.pnu.domain.dto.ProductFilterDTO;
import edu.pnu.domain.dto.ProductHomeDTO;
import edu.pnu.service.ProductService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ProductController {

	private final ProductService productService;
	
	@GetMapping("/product/{productCode}")
	public Optional<Product> getProductDetail (@PathVariable String productCode ) {
		
		return productService.getProductDetail(productCode);
	}
	@GetMapping("/product/items/{pageNum}")
	public Page<ProductHomeDTO> getProductHome(@PathVariable Integer pageNum) {
		
		return productService.getProductHome(pageNum);
	}
//	상품 추가작업
	@GetMapping("/product/items1")
	public List<ProductHomeDTO> getProductHome1() {
		
		return productService.getProductHome1();
	}
	@GetMapping("/product/filter/{kindId}/{pageNum}")
	public Page<ProductFilterDTO> getProductFiltered(@PathVariable Integer pageNum,@PathVariable String kindId) {
		
		return productService.getProductFiltered(pageNum, kindId);
	}
//	상품디테일 추가상품
	@PostMapping("/product/detail")
	public Page<Product> getProductDetailList(@RequestBody ProductDetatilInputDTO dto){
		
		return productService.getProductDetailList(dto);
	}
}

