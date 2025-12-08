

package com.kedu.project.article;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;



@Component
public class ArticleAPIConfig {
	
	@Value("${api.seoul.serviceKey}")
	private String serviceKey;
	
    public static final String BASE_URL = "http://openAPI.seoul.go.kr:8088"; 
    public static final String SERVICE_NAME = "VwSmpBizInfo";
    public static final String RESPONSE_TYPE = "json"; 

    
    public String getServiceKey() {
        return serviceKey;
    }
    
    public static String urlEncode(String value) {
        try {
            return java.net.URLEncoder.encode(value, "UTF-8");
        } catch (java.io.UnsupportedEncodingException e) {
            throw new RuntimeException("URL 인코딩 오류", e);
        }
    }
}