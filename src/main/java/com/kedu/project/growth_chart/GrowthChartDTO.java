package com.kedu.project.growth_chart;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GrowthChartDTO {
    private int growth_seq;
    private int baby_seq;
    private String user_id;
    private Timestamp measure_date;
    private String measure_type;
    private float measure_value; 
    private Timestamp created_at;
    private Timestamp update_at;
}
