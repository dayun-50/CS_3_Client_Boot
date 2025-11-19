package com.kedu.project.baby;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BabyDTO {
    /*
        클라이언트의 리퀘스트 값을 받을 DTO
    */

    private int seq;
    private String name;
    private String birthDate; // 출산예정일 또는 생일
    private String familyCode; // 가족 코드
    private String status; // 출산 여부
    private String updateAt; // 최근 업데이트 날짜
    private String gender; // 남 / 녀 / 아직 모름
}
