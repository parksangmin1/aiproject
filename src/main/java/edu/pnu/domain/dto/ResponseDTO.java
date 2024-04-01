package edu.pnu.domain.dto;

import java.util.List;
import java.util.Optional;

import edu.pnu.domain.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class ResponseDTO {

//	private String categories;
//	private String nukki_image;
//	private Data    data;
	private Upper upper;
	private Skirt skirt;
	private Pants pants;
	private Dress dress;
	
	
//	  @Setter
//    @Getter
//    @ToString
//    @Builder
//    public static class Data {
//        private float percentage;
//        private int style;
//        private String season;
//        private String subCategory;
//        private List<String> similar;
//    }
	
	
	 	@Setter
	    @Getter
	    @ToString
	    @Builder
	    public static class Upper {
	        private float percentage;
	        private int style;
	        private String season;
	        private String subCategory;
	        private List<String> similar;
	        private Recommend recommend;
	    }
	 	@Setter
	    @Getter
	    @ToString
	    @Builder
	    public static class Recommend {
	        private String productCode;
	        private String name;
	        private String kindId;
	        private Integer price;
	        private String season;
	    }

	    @Setter
	    @Getter
	    @ToString
	    @Builder
	    public static class Skirt {
	        private float percentage;
	        private int style;
	        private String season;
	        private String subCategory;
	        private List<String> similar;
	        private Recommend recommend;
	    }

	    @Setter
	    @Getter
	    @ToString
	    @Builder
	    public static class Pants {
	        private float percentage;
	        private int style;
	        private String season;
	        private String subCategory;
	        private List<String> similar;
	        private Recommend recommend;
	    }

	    @Setter
	    @Getter
	    @ToString
	    @Builder
	    public static class Dress {
	        private float percentage;
	        private int style;
	        private String season;
	        private String subCategory;
	        private List<String> similar;
	        private Recommend recommend;
	    }

		public void setDress(Optional<Product> product) {
			if(product.isPresent()) {
				Product p = product.get();
				
				this.dress.setRecommend(
						Recommend.builder()
						.productCode(p.getProductCode())
						.name(p.getName())
						.price(p.getPrice())
						.season(p.getSeason())
						.build());			
			}					
		}
		public void setUpper(Optional<Product> product) {
			if(product.isPresent()) {
				Product p = product.get();
				
				this.dress.setRecommend(
						Recommend.builder()
						.productCode(p.getProductCode())
						.name(p.getName())
						.price(p.getPrice())
						.season(p.getSeason())
						.build());			
			}					
		}
		public void setPants(Optional<Product> product) {
			if(product.isPresent()) {
				Product p = product.get();
				
				this.dress.setRecommend(
						Recommend.builder()
						.productCode(p.getProductCode())
						.name(p.getName())
						.price(p.getPrice())
						.season(p.getSeason())
						.build());			
			}					
		}
		public void setSkirt(Optional<Product> product) {
			if(product.isPresent()) {
				Product p = product.get();
				
				this.dress.setRecommend(
						Recommend.builder()
						.productCode(p.getProductCode())
						.name(p.getName())
						.price(p.getPrice())
						.season(p.getSeason())
						.build());			
			}					
		}
}
