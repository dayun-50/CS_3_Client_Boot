package com.kedu.project.comment;

import java.security.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDTO {
    /*
        댓글  DTO
    */
    private int comment_seq;
    private int board_seq; // 부모 시퀀스 ( 게시글 번호 )
    private String user_id;
    private int parent_comment_seq;
    private int is_deleted;  // 삭제 여부
    private boolean is_reported; // 신고 횟수
    private Timestamp created_at; // 작성 날짜
}
