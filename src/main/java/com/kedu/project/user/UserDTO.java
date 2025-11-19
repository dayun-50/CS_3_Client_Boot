package com.kedu.project.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    private String user_id;
    private String email;
    private String password;
    private String contact; // 연락처
    private String nickname;
    private String parent_role; // 부 / 모
    private String birth_date; // 생년월일
    private String family_code;
    private String created_at; // 가입 날짜
    private int last_baby; // 마지막 접속 아이 seq
}
