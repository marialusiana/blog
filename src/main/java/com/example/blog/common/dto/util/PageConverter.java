package com.example.blog.common.dto.util;


import com.example.blog.common.dto.MyPage;

import org.springframework.data.domain.Page;

/**
 * PageConverter
 */
public class PageConverter<T> {
    public MyPage<T> convert(Page<T> page, String baseUrl, String searchKey) {
        MyPage<T> thisPage = new MyPage<>();

        int currentPage = page.getPageable().getPageNumber() + 1;
        int perPage = page.getPageable().getPageSize();
        int lastPage = page.getTotalPages();
        long total = page.getTotalElements();

        long from = ((perPage * currentPage) - perPage) + 1;
        long to = perPage * currentPage;
        if (currentPage == lastPage) {
            to = total;
        }

        if (currentPage > lastPage) {
            from = 0;
            to = 0;
        }

        String prev = null;
        if (currentPage > 1 && currentPage <= lastPage) {
            int prevI = currentPage - 1;
            prev = baseUrl + "?page=" + prevI + "&size=" + page.getPageable().getPageSize()+searchKey;
        }


        String next = null;
        if (currentPage < lastPage) {
            int nextI = currentPage + 1;
            next = baseUrl + "?page=" + nextI + "&size=" + page.getPageable().getPageSize()+searchKey;
        }

        thisPage.setCurrentPage(currentPage);
        thisPage.setTotal(total);
        thisPage.setPerPage(perPage);
        thisPage.setLastPage(page.getTotalPages());
        thisPage.setData(page.getContent());
        thisPage.setFrom(from);
        thisPage.setTo(to);
        thisPage.setNextPageUrl(next);
        thisPage.setPrevPageUrl(prev);

        return thisPage;
    }
}