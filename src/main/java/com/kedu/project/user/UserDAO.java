package com.kedu.project.user;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {
    @Autowired
    private SqlSession mybatis;

    public int idChack(UserDTO dto) {
        return mybatis.selectOne("user.idChack", dto.getUser_id());
    }

    public int nickNameChack(UserDTO dto) {
        return mybatis.selectOne("user.nickNameChack", dto.getNickname());
    }

    public int signup(UserDTO dto){
        return mybatis.insert("user.signup", dto);
    }
}
