package edu.pnu.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;

import edu.pnu.domain.Member;
import edu.pnu.domain.Product;
import edu.pnu.domain.Request;
import edu.pnu.domain.dto.RequestFlaskDTO;
import edu.pnu.domain.dto.ResponseDTO;
import edu.pnu.domain.dto.ResponseFlaskDTO;
import edu.pnu.persistence.ProductRepository;
import edu.pnu.persistence.RecommendRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImageService {

	private static final String FLASK_PREDICT_URL = "http://10.125.121.182:5000/photo_to_flask" ;
	private static final String FLASK_SEGMENT_URL = "http://10.125.121.182:5000/photo_to_segment" ;
//	private static final String FRONT_RESPONSE_URL = "http://10.125.121.182:"
	private final Environment environment;
	private final RecommendRepository recommendRepository;
	private final ProductRepository productRepository;
	
	
	public ResponseFlaskDTO insertImage11(@Valid MultipartFile file, String username) throws IllegalStateException, IOException {
		Timestamp currentTimeStamp = new Timestamp(System.currentTimeMillis());
		byte[] fileByte = file.getBytes();
		String fileDir = environment.getProperty("file.dir");
		String fullPath = "";
		ResponseFlaskDTO responsedto = null;
//		ResponseDTO resdto = null;
		if(!file.isEmpty()) {		
		    String userFolderPath =fileDir +"/"+ username;
//		    System.out.println(userFolderPath);
		    fullPath = userFolderPath +"/"+ file.getOriginalFilename();
		    File folder = new File(userFolderPath);
		   
		        folder.mkdir(); // 해당 경로에 폴더가 존재하지 않으면 폴더를 생성합니다.
		 
		    file.transferTo(new File(fullPath));	      			
			try {
				RequestFlaskDTO requestdto = RequestFlaskDTO.builder().path(fullPath)
						.image(fileByte)
						.build();
				 responsedto = sendToFlask(requestdto, FLASK_PREDICT_URL);
				 //스타일이 -1이 아닌것만 추천 해줄거고 upper안에 recommend가 있다
//				 Recommend rrecommend = Recommend.builder().kindId(null).name(null)
//						 .price(null).productCode(null).season(null).build();
				 
//				 resdto = ResponseDTO.builder().responseFlaskDTO(responsedto).build();
				 
				Member member = new Member();
				member.setCustomerNum(username);
				Request recommendd = Request.builder()
							.originalFilename(file.getOriginalFilename())							
							.filename(currentTimeStamp+"_"+file.getOriginalFilename())
							.path(fullPath)
							.requestDate(new Date())
							.member(member)
							.build();
				recommendRepository.save(recommendd);
//				return responsedto;
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}		
		return responsedto;
	}

private ResponseFlaskDTO sendToFlask(Object object, String url) throws JsonProcessingException {
	  RestTemplate restTemplate = new RestTemplate();
	  HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      
      HttpEntity<Object> requestEntity = new HttpEntity<>(object, headers);
      ResponseFlaskDTO response = restTemplate.postForObject(url, requestEntity, ResponseFlaskDTO.class);
      edu.pnu.domain.dto.ResponseFlaskDTO.Dress dress = response.getDress();
      edu.pnu.domain.dto.ResponseFlaskDTO.Upper upper = response.getUpper();
      edu.pnu.domain.dto.ResponseFlaskDTO.Pants pants = response.getPants();
      edu.pnu.domain.dto.ResponseFlaskDTO.Skirt skirt = response.getSkirt();
      
      
      List<String> innSimi = null;
      List<String> botSimi = null;
    
//      ResponseDTO resdto = null;
//      ResponseFlaskDTO resdto = null;
      if(dress.getStyle() != -1) {
    	  innSimi = dress.getSimilar();
    	  String season = dress.getSeason();
//    	  System.out.println(dressSimi);
    	  List<Product> recom = dress.getRecommend();
    	  dress.setRecommend(null);
    	  String texture = dress.getTexture();
    	  String print = dress.getPrint();
    	  String detail = dress.getDetail();
//    	  Integer style = dress.getStyle();
//    	  String season = dress.getSeason();
    	  System.out.println(innSimi);
    	  recom = productRepository.getRecommendInn(innSimi, season,detail);
    	  System.out.println(innSimi);  	
    	  dress.setRecommend(recom);
      }
      if(upper.getStyle() != -1) {
    	  innSimi = upper.getSimilar();
    	  String season =upper.getSeason();
    	  List<Product> recom = upper.getRecommend();
    	  upper.setRecommend(null);
    	  String texture = upper.getTexture();
    	  String print = upper.getPrint();
    	  String detail = upper.getDetail();
    	  recom = productRepository.getRecommendInn(innSimi, season,detail);
    	  if(recom.size()<10) {
    		  
    	  }
    	  System.out.println(innSimi);
		 
			 upper.setRecommend(recom);   		 	 
      }
      if(pants.getStyle() != -1) {
    	  botSimi = pants.getSimilar();
    	  List<Product> recom = pants.getRecommend();
    	  pants.setRecommend(null);
    	  String texture = pants.getTexture();
    	  String print = pants.getPrint();
    	  String detail = pants.getDetail();
//    	  Integer style = pants.getStyle();
//    	  String season = pants.getSeason();
    	  recom = productRepository.getRecommendBot(botSimi, texture, print,detail);
			 pants.setRecommend(recom);			
    	  
      }
      if(skirt.getStyle() != -1) {
    	  botSimi = skirt.getSimilar();
    	  String texture = skirt.getTexture();
    	  String print = skirt.getPrint();
    	  String detail = skirt.getDetail();
    	  List<Product> recom = skirt.getRecommend();
    	  skirt.setRecommend(null);
    	  recom = productRepository.getRecommendBot(botSimi, texture, print,detail);
    	  
    	  skirt.setRecommend(recom);

      }
    return response;
}

public ResponseFlaskDTO rerenderImage(String username, Long requestIdd) {
	ResponseFlaskDTO responsedto = null;
	Request request = recommendRepository.rerenderImage(username, requestIdd).get(0);
	try {
		byte[] fileByte = Files.readAllBytes((new File(request.getPath()).toPath()));
		System.out.println("file :" +fileByte);
		RequestFlaskDTO requestdto = RequestFlaskDTO.builder()
				.image(fileByte)
				.build();
		responsedto = sendToFlask(requestdto, FLASK_PREDICT_URL);	
	} catch (IOException e) {
		e.printStackTrace();
	}
	return responsedto;
}

public ResponseFlaskDTO segmentImage(@Valid MultipartFile file, String username) throws IOException {
	Timestamp currentTimeStamp = new Timestamp(System.currentTimeMillis());
	byte[] fileByte = file.getBytes();
	String fileDir = environment.getProperty("file.dir");
	String fullPath = "";
	ResponseFlaskDTO responsedto = null;
//	ResponseDTO resdto = null;
	if(!file.isEmpty()) {		
	    String userFolderPath =fileDir +"/"+ username;
//	    System.out.println(userFolderPath);
	    fullPath = userFolderPath +"/"+ file.getOriginalFilename();
	    File folder = new File(userFolderPath);
	   
	        folder.mkdir(); // 해당 경로에 폴더가 존재하지 않으면 폴더를 생성합니다.
	 
	    file.transferTo(new File(fullPath));	      			
		try {
			RequestFlaskDTO requestdto = RequestFlaskDTO.builder().path(fullPath)
					.image(fileByte)
					.build();
			 responsedto = sendToFlask(requestdto, FLASK_SEGMENT_URL);
			 //스타일이 -1이 아닌것만 추천 해줄거고 upper안에 recommend가 있다
//			 Recommend rrecommend = Recommend.builder().kindId(null).name(null)
//					 .price(null).productCode(null).season(null).build();
			 
//			 resdto = ResponseDTO.builder().responseFlaskDTO(responsedto).build();
			 
			Member member = new Member();
			member.setCustomerNum(username);
			Request recommendd = Request.builder()
						.originalFilename(file.getOriginalFilename())							
						.filename(currentTimeStamp+"_"+file.getOriginalFilename())
						.path(fullPath)
						.requestDate(new Date())
						.member(member)
						.build();
			recommendRepository.save(recommendd);
//			return responsedto;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}		
	return responsedto;
}

}
