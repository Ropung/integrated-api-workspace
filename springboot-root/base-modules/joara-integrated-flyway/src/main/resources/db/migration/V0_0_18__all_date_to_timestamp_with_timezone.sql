ALTER TABLE joara_basic.member
    ALTER COLUMN    created_at    TYPE TIMESTAMPTZ,
    ALTER COLUMN    updated_at    TYPE TIMESTAMPTZ,
    ALTER COLUMN    deleted_at    TYPE TIMESTAMPTZ;

ALTER TABLE joara_basic.refresh_token
    ALTER COLUMN    created_at    TYPE TIMESTAMPTZ,
    ALTER COLUMN    expired_at    TYPE TIMESTAMPTZ;

ALTER TABLE joara_basic.book
    ALTER COLUMN    created_at    TYPE TIMESTAMPTZ,
    ALTER COLUMN    updated_at    TYPE TIMESTAMPTZ,
    ALTER COLUMN    deleted_at    TYPE TIMESTAMPTZ;

ALTER TABLE joara_basic.member_favor_genre
    ALTER COLUMN    created_at    TYPE TIMESTAMPTZ,
    ALTER COLUMN    updated_at    TYPE TIMESTAMPTZ,
    ALTER COLUMN    deleted_at    TYPE TIMESTAMPTZ;

ALTER TABLE joara_basic.member_favor_book
    ALTER COLUMN    created_at    TYPE TIMESTAMPTZ,
    ALTER COLUMN    updated_at    TYPE TIMESTAMPTZ,
    ALTER COLUMN    deleted_at    TYPE TIMESTAMPTZ;

ALTER TABLE joara_basic.episode
    ALTER COLUMN    created_at    TYPE TIMESTAMPTZ,
    ALTER COLUMN    updated_at    TYPE TIMESTAMPTZ,
    ALTER COLUMN    deleted_at    TYPE TIMESTAMPTZ;

ALTER TABLE joara_basic.episode_comment
    ALTER COLUMN    created_at    TYPE TIMESTAMPTZ,
    ALTER COLUMN    updated_at    TYPE TIMESTAMPTZ,
    ALTER COLUMN    deleted_at    TYPE TIMESTAMPTZ;

ALTER TABLE joara_basic.episode_reply
    ALTER COLUMN    created_at    TYPE TIMESTAMPTZ,
    ALTER COLUMN    updated_at    TYPE TIMESTAMPTZ,
    ALTER COLUMN    deleted_at    TYPE TIMESTAMPTZ;

ALTER TABLE joara_basic.board
    ALTER COLUMN    created_at    TYPE TIMESTAMPTZ,
    ALTER COLUMN    updated_at    TYPE TIMESTAMPTZ,
    ALTER COLUMN    deleted_at    TYPE TIMESTAMPTZ;

ALTER TABLE joara_basic.board_comment
    ALTER COLUMN    created_at    TYPE TIMESTAMPTZ,
    ALTER COLUMN    updated_at    TYPE TIMESTAMPTZ,
    ALTER COLUMN    deleted_at    TYPE TIMESTAMPTZ;

ALTER TABLE joara_basic.board_reply
    ALTER COLUMN    created_at    TYPE TIMESTAMPTZ,
    ALTER COLUMN    updated_at    TYPE TIMESTAMPTZ,
    ALTER COLUMN    deleted_at    TYPE TIMESTAMPTZ;
