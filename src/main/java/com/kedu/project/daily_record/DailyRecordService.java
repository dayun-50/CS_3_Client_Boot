package com.kedu.project.daily_record;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kedu.project.baby.BabyDAO;
import com.kedu.project.baby.BabyDTO;
import com.kedu.project.user.UserDAO;

@Service
public class DailyRecordService {
    @Autowired
    private DailyRecordDAO dao;
    @Autowired
    private UserDAO userdao;
    @Autowired
    private BabyDAO babydao;
    
    //1. 하루 일기형 리스트 가져오기
    public List<DailyRecordDTO> getDailyRecord(String start, String end, String baby_seq, String id){
    	//1. 내아기 맞는지 확인
    	//유저 아이디로 패밀리 코드 가져오기
    	String familyCode = userdao.familyCode(id); 
    	//아기가 패밀리코드로 유효한지 확인
    	BabyDTO bDto = new BabyDTO();
    	bDto.setBaby_seq(Integer.parseInt(baby_seq));
    	bDto.setFamily_code(familyCode);
    	BabyDTO result =babydao.babyMypage(bDto);
    	
    	if(result != null) { //아기가 유효하다면
    		return dao.getDailyRecord(baby_seq,start,end); //아기시퀀스, 시작일, 끝나는일 가져오기
    	}else {
    		return null;
    	}
    	
    }
    
    
    
    
}
