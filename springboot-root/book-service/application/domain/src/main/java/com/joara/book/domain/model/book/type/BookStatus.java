package com.joara.book.domain.model.book.type;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public enum BookStatus {
    PENDING(false, true),
    ACTIVE(true, true),
    SUSPEND(false, false),
    REMOVED(false, false);

    public final boolean readable;
    public final boolean writable;

    public static List<BookStatus> readableItems() {
        return Arrays.stream(values())
                .filter((status) -> status.readable)
                .toList();
    }
}
