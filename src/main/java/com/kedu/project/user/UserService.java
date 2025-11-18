package com.kedu.project.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDAO dao;

    public int idChack(UserDTO dto) {
        return dao.idChack(dto);
    }

    public int nickNameChack(UserDTO dto) {
        return dao.nickNameChack(dto);
    }

    public int signup(UserDTO dto){
        return dao.signup(dto);
    }
}
