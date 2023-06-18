-- Refresh Token
CREATE TABLE IF NOT EXISTS joara_basic.refresh_token (
    id                  UUID                                PRIMARY KEY     DEFAULT uuid_generate_v4(),
    nickname            VARCHAR(255)                        NOT NULL,
    refresh_token       VARCHAR(255)                        ,
    device_info         VARCHAR(255)                        ,
    created_at          DATE                                NOT NULL,
    expired_at          DATE                                NOT NULL
);