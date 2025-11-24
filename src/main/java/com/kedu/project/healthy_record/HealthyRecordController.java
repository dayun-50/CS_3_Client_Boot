package com.kedu.project.healthy_record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kedu.project.baby.BabyDTO;
import com.kedu.project.user.UserDTO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/checkList")
@RestController
public class HealthyRecordController {
    @Autowired
    private HealthyRecordService healthyRecordService;    

    @GetMapping("/getBabyData")
    public ResponseEntity<BabyDTO> getBabyData(@RequestParam("baby_seq") int babySeq, @AuthenticationPrincipal String id) {
        System.out.println("앵"+id);
        System.out.println("앵"+babySeq);
        System.out.println("앵"+healthyRecordService.getBabyData(babySeq, id));
        System.out.println("=======================");
        return ResponseEntity.ok(healthyRecordService.getBabyData(babySeq, id));
    }
    
}
