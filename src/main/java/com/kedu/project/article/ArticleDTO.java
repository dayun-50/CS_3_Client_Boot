

package com.kedu.project.article;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
// Lombok 등을 사용하지 않는다면 Getter/Setter를 추가해야 합니다.


@JsonIgnoreProperties(ignoreUnknown = true)	
public class ArticleDTO {
    
    @JsonProperty("BIZ_ID")
    private String BIZ_ID; 

  

	@JsonProperty("BIZ_NM")
    private String BIZ_NM; // 사업명/정책 제목 (키워드 필터링 대상)

    @JsonProperty("BIZ_CN")
    private String BIZ_CN; // 사업 내용 (키워드 필터링 대상)
    
    
    @JsonProperty("DEVIW_SITE_ADDR") 
    private String DEVIW_SITE_ADDR;
    
    @JsonProperty("TRGT_CHILD_AGE") 
    private String TRGT_CHILD_AGE;
    
    
    
    public String getTRGT_CHILD_AGE() {
		return TRGT_CHILD_AGE;
	}

	public void setTRGT_CHILD_AGE(String TRGT_CHILD_AGE) {
		this.TRGT_CHILD_AGE = TRGT_CHILD_AGE;
	}

	public String getDEVIW_SITE_ADDR() {
		return DEVIW_SITE_ADDR;
	}

	public void setDEVIW_SITE_ADDR(String dEVIW_SITE_ADDR) {
		this.DEVIW_SITE_ADDR = dEVIW_SITE_ADDR;
	}

	public void setBIZ_NM(String bIZ_NM) {
		this.BIZ_NM = bIZ_NM;
	}

	public String getBIZ_ID() {
  		return BIZ_ID;
  	}

  	public void setBIZ_ID(String BIZ_ID) {
  		this.BIZ_ID = BIZ_ID;
  	}
    
    
    public String getBIZ_NM() {
        return BIZ_NM;
    }

    public void setBizNm(String BIZ_NM) {
        this.BIZ_NM = BIZ_NM;
    }

    public String getBIZ_CN() {
        return BIZ_CN;
    }

    public void setBIZ_CN(String BIZ_CN) {
        this.BIZ_CN = BIZ_CN;
    }
    
    // Getter/Setter 및 toString() 메서드... 
    // ...
}