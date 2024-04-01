package edu.pnu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Product;
import edu.pnu.domain.dto.ProductDetailListDTO;
import edu.pnu.domain.dto.ProductDetatilInputDTO;
import edu.pnu.domain.dto.ProductFilterDTO;
import edu.pnu.domain.dto.ProductHomeDTO;
import edu.pnu.persistence.ProductRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductService {
	
	private final ProductRepository productRepository;

	public Optional<Product> getProductDetail(String productCode) {
		Optional<Product> productDTO = java.util.Optional.empty();
		try {
			 productDTO = productRepository.findById(productCode);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productDTO;
		
	}

	public Page<ProductHomeDTO> getProductHome(Integer pageNum) {
		
		Pageable pageable = PageRequest.of((pageNum-1), 40);
		Page<ProductHomeDTO> productPage = productRepository.getProductHome(pageable);
		return productPage;
	}

	public List<ProductHomeDTO> getProductHome1() {
		
		Pageable pageable = PageRequest.of(0, 40);
		Page<ProductHomeDTO> productPage = productRepository.getProductHome1(pageable);
		return productPage.getContent();
	}

	public Page<ProductFilterDTO> getProductFiltered(Integer pageNum,String kindId) {
		Pageable pageable = PageRequest.of((pageNum-1), 40);
		System.out.println(pageable);
		Page<ProductFilterDTO> productPage = productRepository.getProductFiltered(pageable,kindId);
		System.out.println(productPage);
		
		return productPage;
		
		
	}

	public Page<Product> getProductDetailList(ProductDetatilInputDTO dto) {
		Integer pageNum = 1;
		Pageable pageable = PageRequest.of((pageNum-1), 15);
		String print = dto.getPrint();
		String detail = dto.getDetail();
		String texture = dto.getTexture();
		String season = dto.getSeason();
		String kindId = dto.getKindId();
		return productRepository.getProductDetailList(print,detail,texture,season,kindId,pageable);
	}
	
	
	
	
}
