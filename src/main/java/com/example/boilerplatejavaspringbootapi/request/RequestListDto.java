package com.example.boilerplatejavaspringbootapi.request;

import java.io.Serializable;

/**
 *
 * @author ardyardyaaan
 */

public class RequestListDto implements Serializable {

    private Integer page = 1;
    
    private Integer per_page = Integer.MAX_VALUE;

    private String keyword;

    private Integer is_active;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPer_page() {
        return per_page;
    }

    public void setPer_page(Integer per_page) {
        this.per_page = per_page;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getIs_active() {
        return is_active;
    }

    public void setIs_active(Integer is_active) {
        this.is_active = is_active;
    }
    
}
