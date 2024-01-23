package com.cimb.demo.service.dto.response;

import com.cimb.demo.service.dto.request.PagingRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class PagingResponse<T> {
    List<T> contents;
    PageableData paging;

    public PagingResponse(List<T> contents, long totalElement, int totalPage, PagingRequest paging) {
        PageableData pageableData = new PageableData();
        pageableData.setTotalRecord(totalElement);
        pageableData.setTotalPage(totalPage);
        this.contents = contents;
        this.paging = pageableData;

        if (Objects.nonNull(paging)) {
            pageableData.setPageNumber(paging.getPage());
            pageableData.setPageSize(paging.getSize());
        }
    }
}
