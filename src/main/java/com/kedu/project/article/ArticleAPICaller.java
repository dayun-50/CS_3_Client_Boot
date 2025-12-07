package com.kedu.project.article;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component 
public class ArticleAPICaller {

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();
    private final ArticleAPIConfig config; // 주입받은 Config 인스턴스
    
    private static final int PAGE_SIZE = 1000; 

    /**
     * Spring이 ArticleAPIConfig 객체를 찾아 자동으로 주입합니다.
     */
    @Autowired
    public ArticleAPICaller(ArticleAPIConfig config) {
        this.config = config;
    }
    
    /**
     * API를 반복 호출하여 모든 데이터를 수집합니다.
     */
    public List<ArticleDTO> fetchAllArticles() throws IOException {
        
        int totalCount = getTotalDataCount();
        if (totalCount <= 0) {
            System.err.println("WARN: 총 데이터 건수가 0이거나 조회에 실패했습니다.");
            return Collections.emptyList();
        }

        List<ArticleDTO> allArticles = new ArrayList<>();
        int currentStart = 1;
        
        while (currentStart <= totalCount) {
            int currentEnd = Math.min(currentStart + PAGE_SIZE - 1, totalCount);
            
            System.out.printf("DEBUG: 요청 중 - 시작: %d, 종료: %d%n", currentStart, currentEnd);

            List<ArticleDTO> pageArticles = fetchAndParse(currentStart, currentEnd);
            allArticles.addAll(pageArticles);

            currentStart += PAGE_SIZE;

            // API 부하를 줄이기 위해 짧은 지연 시간 부여
            try { Thread.sleep(100); } catch (InterruptedException ignored) {} 
        }
        return allArticles;
    }
    
    /**
     * 지정된 범위의 데이터를 가져와 DTO 객체로 파싱합니다.
     */
    public List<ArticleDTO> fetchAndParse(int startIndex, int endIndex) throws IOException { 
        String jsonResponse = fetchPolicyJson(startIndex, endIndex);
        return parseJsonToArticles(jsonResponse);
    }
    
    /**
     * API에 HTTP 요청을 보내고 JSON 문자열을 받습니다. (핵심 로직 복원)
     */
    private String fetchPolicyJson(int startIndex, int endIndex) throws IOException {
        
        // 주입받은 config 인스턴스를 통해 인증키를 가져옵니다.
        String serviceKey = config.getServiceKey(); 
        
        if (serviceKey == null || serviceKey.isEmpty()) {
            // 인증키가 없으면 예외 발생 (FATAL ERROR 방지)
            throw new IllegalStateException("인증키(api.seoul.serviceKey)가 application.properties에 설정되지 않았거나 주입에 실패했습니다.");
        }
        
        // 1. URL 구성
        String encodedKey = ArticleAPIConfig.urlEncode(serviceKey);
        String url = String.format("%s/%s/%s/%s/%d/%d/",
                ArticleAPIConfig.BASE_URL,
                encodedKey,
                ArticleAPIConfig.RESPONSE_TYPE,
                ArticleAPIConfig.SERVICE_NAME,
                startIndex,
                endIndex
        );

        // 2. HTTP 요청 실행
        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                // API 서버 오류 또는 잘못된 요청 시 에러 처리
                throw new IOException("API 요청 실패: HTTP 상태 코드 " + response.code() + ". 응답: " + response.body().string());
            }
            // 3. JSON 응답 문자열 반환
            return response.body().string();
        }
    }

    /**
     * JSON 응답을 DTO 객체 리스트로 변환합니다.
     */
    private List<ArticleDTO> parseJsonToArticles(String jsonResponse) throws IOException {
        // ... (기존 로직 유지) ...
        JsonNode rootNode = mapper.readTree(jsonResponse);
        JsonNode dataNode = rootNode.path(ArticleAPIConfig.SERVICE_NAME).path("row");
        
        if (dataNode.isMissingNode() || !dataNode.isArray()) {
            System.err.println("WARN: JSON에서 'row' 데이터 배열을 찾을 수 없습니다.");
            return Collections.emptyList();
        }
        
        return mapper.readValue(
                dataNode.traverse(), // JsonNode를 파서가 읽을 수 있도록 변환
                new TypeReference<List<ArticleDTO>>() {} // 👈 List<ArticleDTO>임을 명시
        );
    }
    
    /**
     * API 전체 데이터 건수를 조회합니다.
     */
    private int getTotalDataCount() {
        try {
            // 1건만 요청하여 전체 건수를 파악
            String jsonResponse = fetchPolicyJson(1, 1); 
            
            System.out.println("DEBUG: 1건 요청 응답 전문: " + jsonResponse);
            
            
            JsonNode rootNode = mapper.readTree(jsonResponse);
            JsonNode serviceNode = rootNode.path(ArticleAPIConfig.SERVICE_NAME);
            JsonNode countNode = serviceNode.path("list_total_count"); 
            
            if (!countNode.isMissingNode() && countNode.isInt()) {
                return countNode.asInt();
            }
        } catch (IOException e) {
            System.err.println("총 데이터 건수 조회 실패: " + e.getMessage());
            // 디버깅을 위해 API 응답 전문을 보고 싶다면 e.printStackTrace()를 추가하세요.
        }
        return 0;
    }
}