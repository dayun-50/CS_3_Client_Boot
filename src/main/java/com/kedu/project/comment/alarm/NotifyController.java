package com.kedu.project.comment.alarm;

import org.springframework.stereotype.Controller;
import com.kedu.project.comment.CommentDTO;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;

@Controller
public class NotifyController {

    private final SimpMessagingTemplate messagingTemplate;
    private final AlarmService alarmService;

    public NotifyController(SimpMessagingTemplate messagingTemplate, AlarmService alarmService) {
        this.messagingTemplate = messagingTemplate;
        this.alarmService = alarmService;
    }

    @MessageMapping("/notify")
    public void handleNotify(CommentDTO dto, StompPrincipal principal) {
        System.out.println("쓰는이 : "+dto.getUser_id());
        String writerId = alarmService.getWriterId(dto);
        System.out.println("받는이 : "+writerId);

        if (writerId.equals(principal.getName())) return;

        AlarmDTO alarm = new AlarmDTO();
        alarm.setUser_id(writerId);
        alarm.setBorad_seq(String.valueOf(dto.getBoard_seq()));
        alarm.setComment_seq(String.valueOf(dto.getComment_seq()));
        alarm.setType("C");

        System.out.println("알람 보내기: " + writerId + " -> " + alarm.getType());

        // 사용자 전용 알람
        messagingTemplate.convertAndSendToUser(writerId, "/queue/notify", alarm);

        // 브로드캐스트 (선택)
        messagingTemplate.convertAndSend("/topic/board/" + dto.getBoard_seq(), alarm);
    }
}
