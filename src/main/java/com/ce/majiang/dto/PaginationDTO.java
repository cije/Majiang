package com.ce.majiang.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * @author c__e
 * @date 2020/12/30 10:02
 */
public class PaginationDTO implements Serializable {
    private List<QuestionDTO> questions;
    private Boolean showPrevious;
    private Boolean showFirstPage;
    private Boolean showNext;
    private Boolean showEndPage;
    private Integer page;
    private Integer totalPage;
    private Integer totalCount;
    private List<Integer> pages = new ArrayList<>();

    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public PaginationDTO setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
        return this;
    }

    public Boolean getShowPrevious() {
        return showPrevious;
    }

    public PaginationDTO setShowPrevious(Boolean showPrevious) {
        this.showPrevious = showPrevious;
        return this;
    }

    public Boolean getShowFirstPage() {
        return showFirstPage;
    }

    public PaginationDTO setShowFirstPage(Boolean showFirstPage) {
        this.showFirstPage = showFirstPage;
        return this;
    }

    public Boolean getShowNext() {
        return showNext;
    }

    public PaginationDTO setShowNext(Boolean showNext) {
        this.showNext = showNext;
        return this;
    }

    public Boolean getShowEndPage() {
        return showEndPage;
    }

    public PaginationDTO setShowEndPage(Boolean showEndPage) {
        this.showEndPage = showEndPage;
        return this;
    }

    public Integer getPage() {
        return page;
    }

    public PaginationDTO setPage(Integer page) {
        this.page = page;
        return this;
    }

    public List<Integer> getPages() {
        return pages;
    }

    public PaginationDTO setPages(List<Integer> pages) {
        this.pages = pages;
        return this;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public PaginationDTO setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
        return this;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public PaginationDTO setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PaginationDTO.class.getSimpleName() + "[", "]")
                .add("questions=" + questions)
                .add("showPrevious=" + showPrevious)
                .add("showFirstPage=" + showFirstPage)
                .add("showNext=" + showNext)
                .add("showEndPage=" + showEndPage)
                .add("page=" + page)
                .add("totalPage=" + totalPage)
                .add("totalCount=" + totalCount)
                .add("pages=" + pages)
                .toString();
    }

    public void setPagination(Integer page, Integer size) {
        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }
        // 是否展示上一页
        showPrevious = page != 1;
        // 是否展示下一页
        showNext = !page.equals(totalPage);
        // 是否展示第一页
        showFirstPage = !pages.contains(1);
        // 是否展示最后一页
        showEndPage = !pages.contains(totalPage);
    }
}
