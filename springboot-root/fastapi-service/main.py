# Package Import
from fastapi import FastAPI
import uvicorn
from pydantic import BaseModel
from starlette.responses import JSONResponse
from sklearn.feature_extraction.text import CountVectorizer


import pickle
import numpy as np
import pandas as pd

# Request Body
class Item(BaseModel) :
    movieId: int


app = FastAPI()

@app.post(path="/items", status_code=201)
def recommend(item: Item) :
    # pickle 파일 로딩
    # 코사인 유사도 측정 후 정렬한 값을 저장한 피클 파일 열기
    with open('anime_sim_sorted_idx.pkl', 'rb') as f:

        # 피클 파일 로딩
        rec = pickle.load(f)

        dicted = dict(item)

        movieId = dicted['movieId']
        print('movieId',movieId)

        # 데이터 프레임
        anime = pd.read_csv('anime_py.csv')

        target_movie = anime[anime['id'] == movieId]

        title_index = target_movie.index.values

        similar_indexes = rec[title_index, :11]
        similar_indexes = similar_indexes.reshape(-1)

        # 기준이 되는 영화 제외
        similar_indexes = similar_indexes[similar_indexes != title_index]

        # 유사한 작품의 인덱스값을 데이터 프레임에 적용해서 작품 정보 담기
        similar_movies = anime.iloc[similar_indexes].sort_values('score', ascending=False)

        # 작품의 아이디값을 리스트로 스프링에 전달
        result = {"predict_result": similar_movies['id'].values.tolist()}
        print("=======예측결과 =======>", result)

    return JSONResponse(result)



if __name__ == '__main__' :
    uvicorn.run(app, host="127.0.0.1", port=8000)