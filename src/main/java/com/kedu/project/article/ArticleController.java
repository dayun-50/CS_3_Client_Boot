package com.kedu.project.article;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList; // 스레드 안전한 리스트

// 이 코드는 Spring Boot 환경을 가정합니다.
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

@RestController
@RequestMapping("/article") // 클라이언트 API의 기본 경로 설정
public class ArticleController {

    private final ArticleService articleService;
    
    // 💡 캐싱 전략: 수집된 데이터를 메모리에 저장하여 재사용
    // volatile 키워드와 CopyOnWriteArrayList를 사용하여 스레드 안전성을 확보합니다.
    private volatile List<ArticleDTO> cachedArticles = new CopyOnWriteArrayList<>();

    // 생성자 주입 (Dependency Injection)
    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
        // 서버 시작 시 초기 데이터를 로드합니다.
        // runInitialLoad(); // 실제 구현 시 초기 로드 메서드 호출 필요
        
        
     //  이 부분이 서버가 켜지는 순간 데이터 수집 로직을 '강제 실행'합니다.
        try {
            System.out.println("INFO: [Initial Load] 서버 구동 시 초기 데이터 수집 강제 시작.");
            scheduleDataUpdate(); // @Scheduled 메서드를 직접 호출하여 즉시 실행
        } catch (Exception e) {
            System.err.println(" ERROR: 초기 데이터 로드 실패 - " + e.getMessage());
        }
    
        
        
        
    }

    
    
    
    
    
    @Scheduled(cron = "0 0 4 * * *") 
    public void scheduleDataUpdate() {
        System.out.println("INFO: [Scheduler] 정책 데이터 갱신 작업 시작...");
        try {
            // Service를 통해 Open API 호출 -> 필터링된 최신 데이터 획득
            List<ArticleDTO> latestArticles = articleService.getFilteredPolicyArticles();
            
            // 캐시 업데이트 (스레드 안전)
            this.cachedArticles = new CopyOnWriteArrayList<>(latestArticles);
            
            System.out.printf("INFO: [Scheduler] 정책 데이터 갱신 완료. 총 %d건 캐시됨.%n", latestArticles.size());
            
        } catch (IOException e) {
            System.err.println("ERROR: 스케줄링 중 데이터 갱신 실패. " + e.getMessage());
            // 로깅 후 이전 캐시 데이터 유지
        }
    }

    /**
     * 2. [클라이언트 API] React 클라이언트가 호출할 엔드포인트
     * React의 API_URL을 이곳으로 지정합니다. (예: http://localhost:8080/api/v1/articles)
     */
    @GetMapping("/select")
    public List<ArticleDTO> getArticlesForClient() {
        System.out.println("INFO: 클라이언트 요청 수신. 캐시된 데이터 반환.");
        // 캐시된 데이터를 즉시 반환하여 Open API 호출 지연을 피합니다.
        return this.cachedArticles;
    }
}