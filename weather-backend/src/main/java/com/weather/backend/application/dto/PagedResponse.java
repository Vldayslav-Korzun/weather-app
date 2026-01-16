package com.weather.backend.application.dto;

import java.util.List;

public class PagedResponse<T> {
    public List<T> items;
    public int page;
    public int size;
    public long totalItems;
    public int totalPages;

    public PagedResponse(List<T> items, int page, int size, long totalItems, int totalPages) {
        this.items = items;
        this.page = page;
        this.size = size;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
    }
}
