package com.kedu.project.growth_chart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/chart")
@RestController
public class GrowthChartController {
	
    @Autowired
    private GrowthChartService growthChartService;
    
    
    
}
