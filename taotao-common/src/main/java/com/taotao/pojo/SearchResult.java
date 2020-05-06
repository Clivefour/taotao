package com.taotao.pojo;

import java.io.Serializable;
import java.util.List;

public class SearchResult implements Serializable{
    private List<SearchItem> itemList;
    private Long totalPages;
    private Long totalCount;

    public List<SearchItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<SearchItem> itemList) {
        this.itemList = itemList;
    }

    public Long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "itemList=" + itemList +
                ", totalPages=" + totalPages +
                ", totalCount=" + totalCount +
                '}';
    }
}
