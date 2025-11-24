package com.kedu.project.healthy_record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kedu.project.baby.BabyDAO;
import com.kedu.project.baby.BabyDTO;
import com.kedu.project.user.UserDAO;

@Service
public class HealthyRecordService {
    @Autowired
    private HealthyRecordDAO dao;    

    @Autowired
    private UserDAO userdao;

    @Autowired
    private BabyDAO babydao;

    public BabyDTO getBabyData(int babySeq, String id){
        BabyDTO dto = new BabyDTO();
        dto.setFamily_code(userdao.familyCode(id));
        dto.setBaby_seq(babySeq);

        return babydao.babyMypage(dto);
    }
}
