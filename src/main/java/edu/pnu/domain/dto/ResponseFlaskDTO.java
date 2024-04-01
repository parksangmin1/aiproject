package edu.pnu.domain.dto;

import java.util.ArrayList;
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
public class ResponseFlaskDTO {

//	private String categories;
//	private String nukki_image;
//	private Data    data;
	private Upper   upper;
	private Skirt   skirt;
	private Pants   pants;
	private Dress   dress;
	private List<List<Integer>> predSeg;
//	private Recommend recommend;
//	private Recommend recommend;
	
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
	    
	    public static class Upper {
	        private float percentage;
	        private int style;
	        private String season;
	        private String kindId;
	        private List<String> similar;
	        private List<Product> recommend;
	        private String print;
	        private String detail;
	        private String texture;
	        public Upper() {
	            this.recommend = new ArrayList<>();
	        }
	    }
	 	
//	 	@Setter
//	    @Getter
//	    @ToString
//	    @Builder
//	    public static class Recommend {
//	        private String productCode;
//	        private String name;
//	        private String kindId;
//	        private Integer price;
//	        private String season;
//	        
//	    }

	    @Setter
	    @Getter
	    @ToString
	    public static class Skirt {
	        private float percentage;
	        private int style;
	        private String season;
	        private String kindId;
	        private List<String> similar;
	        private List<Product> recommend;
	        private String print;
	        private String detail;
	        private String texture;
	        public Skirt() {
	            this.recommend = new ArrayList<>();
	        }
	    }

	    @Setter
	    @Getter
	    @ToString	   
	    public static class Pants {
	        private float percentage;
	        private int style;
	        private String season;
	        private String kindId;
	        private List<String> similar;
	        private List<Product> recommend;
	        private String print;
	        private String detail;
	        private String texture;
	        public Pants() {
	            this.recommend = new ArrayList<>();
	        }
	    }

	    @Setter
	    @Getter
	    @ToString
	    public static class Dress {
	        private float percentage;
	        private int style;
	        private String season;
	        private String kindId;
	        private List<String> similar;
	        private List<Product> recommend;
	        private String print;
	        private String detail;
	        private String texture;
	        public Dress() {
	            this.recommend = new ArrayList<>();
	        }
	    }
//	    
//		public void setDress(Optional<Product> product) {
//			if(product.isPresent()) {
//				Product p = product.get();
//				
//				this.dress.getRecommend().add(
//			            Recommend.builder()
////			            .kindId(null)
//		                .productCode(p.getProductCode())
//		                .name(p.getName())
//		                .price(p.getPrice())
//		                .season(p.getSeason())
//		                .build());        
//			}					
//		}
//		public void setUpper(List<Recommend> recom,Product product) {
////			if(product.isPresent()) {
//				Product p = product.get();
////				  if (this.upper.getRecommend() == null) { // 만약 recommend 리스트가 null이면 초기화
////			            this.upper.setRecommend(new ArrayList<>());
////			        }
////				this.upper.getRecommend().add(recom);
//				
//				this.upper.getRecommend().add(
//						Recommend.builder()
//						.productCode(p.getProductCode())
//						.name(p.getName())
//						.price(p.getPrice())
//						.season(p.getSeason())
//						.build());			
////			}					
//		}
//		public void setPants(Optional<Product> product) {
//			if(product.isPresent()) {
//				Product p = product.get();
//				
//				this.pants.getRecommend().add(
//						Recommend.builder()
//						.productCode(p.getProductCode())
//						.name(p.getName())
//						.price(p.getPrice())
//						.season(p.getSeason())
//						.build());			
//			}					
//		}
//		public void setSkirt(Optional<Product> product) {
//			if(product.isPresent()) {
//				Product p = product.get();
//				
//				this.skirt.getRecommend().add(
//						Recommend.builder()
////						.kindId(p.getKind())
//						.productCode(p.getProductCode())
//						.name(p.getName())
//						.price(p.getPrice())
//						.season(p.getSeason())
//						.build());			
//			}					
//		}
	    
}
