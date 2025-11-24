package com.kedu.project.comment;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDAO {
    @Autowired
    private SqlSession mybatis;
    
    //1. 부모시퀀스+타입으로 댓글 전부 지우기
    public int deleteAllComment(Map<String, Object> params) {
    	return mybatis.delete("Comment.deleteAllComment", params);
    }
    
}
