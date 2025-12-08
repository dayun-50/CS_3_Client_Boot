
package com.kedu.project.article;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

// ArticleApiCaller, Article 클래스는 같은 패키지에 있으므로 import 불필요
// com.kedu.project.article.*


@Service
public class ArticleService {

    private final ArticleAPICaller apiCaller;
    
    
 
    	public ArticleService(ArticleAPICaller apiCaller) {
            this.apiCaller = apiCaller; 
            System.out.println("DEBUG: ArticleAPICaller 인스턴스 주입 성공.");
        }
       
    	
    	
    	
        public List<ArticleDTO> getFilteredPolicyArticles() throws IOException {
            
            System.out.println("INFO: 데이터 수집 및 필터링 시작...");
            List<ArticleDTO> allArticles = apiCaller.fetchAllArticles();
            
            if (allArticles.isEmpty()) {
                return allArticles;
            }

            List<ArticleDTO> finalArticles = allArticles.stream()
              
                .collect(Collectors.toList());

            System.out.printf("INFO: 필터링 및 수량 제한 완료. 총 %d건 중 최종 %d건 추출됨.%n", 
                              allArticles.size(), finalArticles.size());
                              
            return finalArticles;
        }

        
    }