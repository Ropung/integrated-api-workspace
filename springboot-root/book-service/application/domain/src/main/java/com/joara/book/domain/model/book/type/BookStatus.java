package com.joara.book.domain.model.book.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum BookStatus {
    PENDING(false, true),
    ACTIVE(true, true),
    SUSPEND(false, false),
    REMOVED(false, false);

    public final boolean readable;
    public final boolean writable;
}
