package com.joara.book.domain.model.book.type;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum SearchType {
    @JsonProperty("title")
    TITLE,
    @JsonProperty("content")
    CONTENT,
    @JsonProperty("nickname")
    MEMBER_NAME,
    @JsonProperty("none")
    NONE;
}
