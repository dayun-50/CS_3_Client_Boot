package com.kedu.project.comment.alarm;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AlarmDTO {
    private int alarm_seq;
    private String user_id;
    private String board_seq;
    private Integer comment_seq;
    private String type;
}
