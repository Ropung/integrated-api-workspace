# # SQlAlchemy 사용에 기본적인 부분
# from sqlalchemy import create_engine
# from sqlalchemy.orm import sessionmaker
#
# from sqlalchemy.ext.declarative import declarative_base
#
# # 이미 만들어진 데이터베이스의 테이블을 사용하는데 필요한 부분
# from sqlalchemy import Table, MetaData
# from sqlalchemy import insert, delete
#
#
# # SQLALCHEMY_DATABASE_URL = "postgresql://db id:db pw@ip:port/db 이름"
# SQLALCHEMY_DATABASE_URL = 'postgresql://scott:tiger@localhost:5432/mydatabase'
#
# # create_engine > DB엔진 생성
# engine = create_engine(
#     SQLALCHEMY_DATABASE_URL, connect_args={"check_same_thread": False}
# )
#
# # SessionLocal > 데이터 베이스 세션 클래스, 이를 이용해 생성한 인스턴스가 실제 데이터 베이스 세션이 됨
# # autocommit=False 커밋해야만 변경사항 적용
# SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)
#
# # Base > DB모델이나 클래스를 만들기 위해 선언한 클래스(상속해서 사용)
# Base = declarative_base()