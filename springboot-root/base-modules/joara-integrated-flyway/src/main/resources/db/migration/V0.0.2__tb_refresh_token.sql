-- Refresh Token
CREATE TABLE IF NOT EXISTS joara_basic.refresh_token (
    id                  UUID                                PRIMARY KEY     DEFAULT uuid_generate_v4(),
    member_id           UUID                                NOT NULL,
    nickname            VARCHAR(255)                        ,
    refresh_token       VARCHAR(255)                        ,
    device_info         VARCHAR(255)                        ,
    created_at          DATE                                NOT NULL,
    expired_at          DATE                                NOT NULL
);