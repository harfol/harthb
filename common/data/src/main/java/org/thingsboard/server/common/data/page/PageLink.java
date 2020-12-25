package org.thingsboard.server.common.data.page;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class PageLink {

    private final String textSearch;
    private final int pageSize;
    private final int page;
    private final SortOrder sortOrder;

    public PageLink(PageLink pageLink) {
        this.pageSize = pageLink.getPageSize();
        this.page = pageLink.getPage();
        this.textSearch = pageLink.getTextSearch();
        this.sortOrder = pageLink.getSortOrder();
    }

    public PageLink(int pageSize) {
        this(pageSize, 0);
    }

    public PageLink(int pageSize, int page) {
        this(pageSize, page, null, null);
    }

    public PageLink(int pageSize, int page, String textSearch) {
        this(pageSize, page, textSearch, null);
    }

    public PageLink(int pageSize, int page, String textSearch, SortOrder sortOrder) {
        this.pageSize = pageSize;
        this.page = page;
        this.textSearch = textSearch;
        this.sortOrder = sortOrder;
    }

    @JsonIgnore
    public PageLink nextPageLink() {
        return new PageLink(this.pageSize, this.page+1, this.textSearch, this.sortOrder);
    }

}
