package com.hifood.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)
@Getter
@Setter
@Builder
public class Problem {

    private Integer status;
    private String type;
    private String title;
    private String detail;

    private String userMessage;
    private OffsetDateTime timestamp;
    private List<Object> objects;

    @Getter
    @Setter
    @Builder
    public static class Object {
        private String name;
        private String userMessage;
    }

}
