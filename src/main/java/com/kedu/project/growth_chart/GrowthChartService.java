package com.kedu.project.growth_chart;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GrowthChartService {
	@Autowired
    private GrowthChartDAO growthChartDAO;

    
public Map<String, Float> getActualDataByRange(int babyId, LocalDate startDate, LocalDate endDate) {
        
        // 1. 🔍 DAO 호출 준비
        Map<String, Object> daoParams = new HashMap<>();
        // DAO에 전달할 DB 타입(java.sql.Date)으로 변환
        daoParams.put("baby_seq", babyId);
        daoParams.put("startDate", java.sql.Date.valueOf(startDate)); 
        daoParams.put("endDate", java.sql.Date.valueOf(endDate));    
        
        // 2. DAO 호출 및 데이터 조회
        // 🚨 growthChartDAO.selectLatestMeasurementsByDateRange 메소드는 이미 구현되어 있어야 합니다.
        List<GrowthChartDTO> records = growthChartDAO.selectLatestMeasurementsByDateRange(daoParams);
        
        // 3. 📊 기록을 Map<String, Float>으로 가공 (React actualData props 형태)
        if (records.isEmpty()) {
            return new HashMap<>(); // 실측 데이터 없으면 빈 맵 반환
        }
        
        Map<String, Float> actualDataMap = records.stream()
            .collect(Collectors.toMap(
                GrowthChartDTO::getMeasure_type, 
                GrowthChartDTO::getMeasure_value
            ));

        return actualDataMap;
    }
    
}
