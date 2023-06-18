ALTER TABLE joara_basic.book
ADD COLUMN score DOUBLE PRECISION;

COMMENT ON COLUMN joara_basic.book.score IS '평점 평균(비정규화)';
