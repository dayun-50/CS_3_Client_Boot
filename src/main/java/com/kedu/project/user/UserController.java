package com.kedu.project.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/idChack")
    public ResponseEntity<Integer> idChack(@RequestBody UserDTO dto) {
        return ResponseEntity.ok(userService.idChack(dto));
    }

    @PostMapping("/nickNameChack")
    public ResponseEntity<Integer> nickNameChack(@RequestBody UserDTO dto) {
        return ResponseEntity.ok(userService.idChack(dto));
    }

    @PostMapping("/signup")
    public ResponseEntity<Integer> signup(@RequestBody UserDTO dto) {
        return ResponseEntity.ok(userService.signup(dto));
    }

}
