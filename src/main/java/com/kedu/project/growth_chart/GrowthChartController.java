package com.kedu.project.growth_chart;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kedu.project.baby.BabyDTO;
import com.kedu.project.baby.BabyService;

@RequestMapping("/chart")
@RestController
public class GrowthChartController {

	@Autowired
	private GrowthChartService growthChartService;

	@Autowired
	private BabyService babyService;
	
	
	//baby info 조회
	
	@GetMapping("/{babySeq}")
	public ResponseEntity<BabyDTO> getBabyInfoForChart(@PathVariable int babySeq) {
		// BabyService를 호출하여 BabyDTO (status, birthDate) 반환
		// Service는 int를 사용하므로 Long으로 변환할 필요 없음
		BabyDTO babyInfo = babyService.getBabyInfo(babySeq); 
		if (babyInfo == null) {
			//  DB에 아기가 없으면 404 NOT FOUND를 반환하여 안전하게 처리합니다.
			return ResponseEntity.notFound().build(); 
		}
		// 200 OK와 함께 BabyDTO를 반환
		return ResponseEntity.ok(babyInfo);
	}

	// total chart 데이터 조회
	
	@GetMapping("/total") 
	public ResponseEntity<Map<String, Float>> getTotalChartData(
			@RequestParam("babyId") int babyId,
	        @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
	        @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate)
			{
		try {
            // Service 호출: 계산 로직 없이 바로 DAO에 필요한 데이터를 요청합니다.
            Map<String, Float> actualDataMap = growthChartService.getActualDataByRange(babyId, startDate, endDate);
            
            return ResponseEntity.ok(actualDataMap);
            
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
	}
	
//	@GetMapping("/history/{babySeq}")
//    public ResponseEntity<List<MeasurementGroupedResponse>> getHistoricalChartData(@PathVariable int babySeq) {
//        
//        try {
//            // Service를 호출하여 모든 기록을 조회하고 주차 계산 및 가공을 수행합니다.
//            List<MeasurementGroupedResponse> data = growthChartService.getAllHistoricalMeasurements(babySeq);
//            
//            return ResponseEntity.ok(data);
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().body(null);
//        }
//    }

}
