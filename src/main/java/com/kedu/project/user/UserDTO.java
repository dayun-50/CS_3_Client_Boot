package com.kedu.project.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    private String id;
    private String email;
    private String pw;
    private String contact; // 연락처
    private String nickname;
    private String role; // 부 / 모
    private String birth_date; // 생년월일
    private String family_code;
    private String created_at; // 생성일자
    private int last_baby;
}
