--회차 좋아요 Episode Heart
CREATE TABLE IF NOT EXISTS joara_basic.episode_heart (
    id                  UUID                                PRIMARY KEY     DEFAULT uuid_generate_v4(),
    member_id           UUID                                NOT NULL,
    epi_id              UUID                                NOT NULL,
    nickname            VARCHAR(255)
);