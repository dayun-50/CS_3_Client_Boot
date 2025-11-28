package com.kedu.project.comment.alarm;

import java.util.Map;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.kedu.project.security.jwt.JWTUtil;

@Component
public class JwtHandshakeInterceptor implements HandshakeInterceptor {

    private final JWTUtil jwtUtil;

    public JwtHandshakeInterceptor(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
                                   ServerHttpResponse response,
                                   WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {

        String token = null;

        // WebSocket 연결 시 전달된 token 추출
        if (request instanceof ServletServerHttpRequest req) {
            token = req.getServletRequest().getParameter("token");
            System.out.println("WebSocket 연결 시도, token: " + token);
            System.out.println("클라이언트 주소: " + req.getRemoteAddress());
        }

        // 토큰 검증
        if (token == null || !jwtUtil.validateToken(token)) {
            System.out.println("WebSocket 연결 실패: 유효하지 않은 토큰");
            return false;
        }

        // 토큰에서 userId 추출 후 STOMP Principal로 세팅
        String userId = jwtUtil.getIdFromToken(token);
        StompPrincipal principal = new StompPrincipal(userId);
        attributes.put("principal", principal);

        // 디버그용 출력
        System.out.println("WebSocket 연결 성공, Principal name: " + principal.getName());

        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request,
                               ServerHttpResponse response,
                               WebSocketHandler wsHandler,
                               Exception exception) {
        System.out.println("afterHandshake 호출됨");
    }
}
