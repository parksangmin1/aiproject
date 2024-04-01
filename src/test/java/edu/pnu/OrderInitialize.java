package edu.pnu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Sales;
import edu.pnu.persistence.OrdersRepository;

@SpringBootTest
public class OrderInitialize {

//	@Autowired
//	OrdersRepository ordersRepository;
//	
//	@Test
//	public void doinit() throws Exception {
//		BufferedReader br = new BufferedReader(new FileReader("data.csv"));
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		List<List<String>> list = new ArrayList<>();
//		Set<List<String>> set = new HashSet<>();
//		
//		int i = 0;
////		String str = 
//		br.readLine();
//		while(true) {
//			// ﻿⇅,판매일자,전표번호,매장코드,상품코드,상품명,칼라,칼라명,사이즈,고객번호,판매금액,수량
//			// 0  1      2      3      4    5    6   7    8    9     10   11
//			String str = br.readLine();
//			if (str == null) break;
//
//			String[] arr = str.split(",");
//			// 상품목록 : product_id, 4, 5, 7, 8
////			List<String> plist = new ArrayList<>();
////			plist.add(arr[4]);
////			plist.add(arr[5]);
////			plist.add(arr[7]);
////			plist.add(arr[8]);
//			
//			
//			
//			// 구매목록 : 1, 2, 3, 9, 10, 11, + product_id
//			List<String> tlist = new ArrayList<>();
//			Date parsedDate = dateFormat.parse(arr[1]);
//			ordersRepository.save(Orders.builder()				
//					.saleDate(parsedDate)
//					.slip(Integer.parseInt(arr[2]))
//					.storeCode(arr[3])
//					.price(Integer.parseInt(arr[10]))
//					.customerNum(Integer.parseInt(arr[9]))
//					.quantity(Integer.parseInt(arr[11]))
//					.build());
////			tlist.add(arr[1]);
////			tlist.add(arr[2]);
////			tlist.add(arr[3]);
////			tlist.add(arr[9]);
////			tlist.add(arr[10]);
////			tlist.add(arr[11]);
//			
//			
////			System.out.println(str);
////			list.add(tlist);
////			list.add(plist);
//			if (i++ == 200) break;
//		}
//		br.close();
//		
//		// 구매목록 Write
//		System.out.println(list);
//		
//		// 상품목록 Write
////		System.out.println(list);
//		
//		System.out.println("Done");
	}
//}
