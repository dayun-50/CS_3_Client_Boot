package com.kedu.project.report;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReportDTO {
    private int report_seq;
    private String user_id;
    private int board_seq; 
    private int comment_seq;
    private String report_type;
    
}
