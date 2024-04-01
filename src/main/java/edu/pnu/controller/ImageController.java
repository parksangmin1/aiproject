package edu.pnu.controller;


import java.io.IOException;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.pnu.domain.dto.ResponseFlaskDTO;
import edu.pnu.service.ImageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ImageController {

	private final ImageService imageService;
	
//	@Value("${file.dir}")
//	private String fileDir; 
	
//	@PostMapping("/upload")
//	public String insertImage(
////			@Valid @RequestParam("")
//			@AuthenticationPrincipal User user,
//			@Valid @RequestParam("file") MultipartFile file )throws Exception{
//		
//		String username = user.getUsername();
//		System.out.println(username);
//		
//		
//		
//		return imageService.insertImage(file,username);
//		
//		
//	}
	
	@PostMapping("/giveimage")
	public ResponseFlaskDTO giveImage(@AuthenticationPrincipal User user,@Valid @RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
//		System.out.println(user);
		String username = user.getUsername();
		    
//				System.out.println(username);
		return imageService.insertImage11(file,username);
		
	}
	
	@GetMapping("/rerender")
	public ResponseFlaskDTO rerenderImage(@AuthenticationPrincipal User user, @RequestParam String requestId ) {
	Long requestIdd = Long.parseLong(requestId);	
		
		String username = user.getUsername();
		return imageService.rerenderImage(username,requestIdd);
	}
	
	@GetMapping("/segment")
	public ResponseFlaskDTO segmentImage(@AuthenticationPrincipal User user, @Valid @RequestParam("file") MultipartFile file ) throws IOException {
		
		String username = user.getUsername();
		return imageService.segmentImage(file,username);
	}
	
}
