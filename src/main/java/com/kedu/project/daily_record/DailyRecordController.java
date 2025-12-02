package com.kedu.project.daily_record;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/dailyrecord")
@RestController
public class DailyRecordController {
    @Autowired
    private DailyRecordService dailyRecordService;    
    
    
    
    @GetMapping
    public ResponseEntity<Map<String,Object>> getDailyRecord (
    		@RequestParam("start") String start,
    		@RequestParam("end") String end,
    		@RequestParam("baby_seq") String baby_seq,
    		@AuthenticationPrincipal String id
    		){
    	System.out.println(start+":"+end+":"+baby_seq+":"+ id);
    	
    	//하루일기형 리스트
    	List<DailyRecordDTO> rDTOList = dailyRecordService.getDailyRecord(start,end, baby_seq, id);
    	 
    	Map result = new HashMap<>();
    	result.put("rDTOList", rDTOList);
    	
    	return ResponseEntity.ok(result);
    }
    
}
