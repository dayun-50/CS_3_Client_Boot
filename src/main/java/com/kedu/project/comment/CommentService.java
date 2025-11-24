package com.kedu.project.comment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private CommentDAO dao;
    
  //1. 부모시퀀스+타입으로 댓글 전부 지우기
    public int deleteAllComment (int board_seq) {
    	Map<String, Object> params = new HashMap<>();
    	params.put("board_seq", board_seq);
    	
    	return dao.deleteAllComment(params);
    }
    
    
}
