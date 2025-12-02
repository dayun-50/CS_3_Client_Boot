package com.kedu.project.daily_record;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DailyRecordDAO {
    @Autowired
	private SqlSession mybatis;   
    
    
    //1. 하루 일기형 리스트 가져오기
    public List<DailyRecordDTO> getDailyRecord(String baby_seq,String start, String end){
    	System.out.println("dao "+start);
    	System.out.println("dao "+end);
    	return mybatis.selectList("DailyRecord.getDailyRecord",Map.of(
                "start", start,
                "end", end,
                "baby_seq",baby_seq));
    }
    
    
    
}
