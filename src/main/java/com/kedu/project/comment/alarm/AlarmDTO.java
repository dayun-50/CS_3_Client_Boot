package com.kedu.project.comment.alarm;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AlarmDTO {
    private String user_id;
    private String borad_seq;
    private String comment_seq;
    private String type;
}
