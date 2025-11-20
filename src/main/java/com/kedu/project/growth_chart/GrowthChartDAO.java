package com.kedu.project.growth_chart;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GrowthChartDAO {
    @Autowired
	private SqlSession mybatis;    
    
    private static String NAMESPACE = "chart";
    

    
}
