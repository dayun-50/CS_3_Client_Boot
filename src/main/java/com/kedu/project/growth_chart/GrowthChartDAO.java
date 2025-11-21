package com.kedu.project.growth_chart;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GrowthChartDAO {
    @Autowired
	private SqlSession mybatis;    

    // 혜빈 - 베이비 페이지 몸무게 출력
    public String getWeightByBabypage(int baby_seq){
        return mybatis.selectOne("chart.getWeightByBabypage", baby_seq);
    }
}
