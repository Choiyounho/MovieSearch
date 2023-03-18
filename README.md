# MovieSearchApp
Naver Search Api를 이용한 영화 검색 앱 입니다.

## Architecture
- Clean & MVVM

## Async
- Coroutine & Flow

## Library
- DataBinding
- Coil
- Hilt
- StateFlow
- Retrofit2
- Room



## 요구 사항

### 메인화면
- 영화명을 검색 시 영화 포스터, 제목, 출시익, 평점이 노출된다. 또한 검색 기록은 10개까지 저장된다.
- 영화를 클릭 시 웹 검색을 하게 된다. (네이버에 해당 영화 검색화면)
- 20개가 넘을 시 페이징 처리를 해야한다.
- 최근 검색 버튼이 있다. 버튼을 클릭 시 최근 검색 목록 10개가 노출된다.


### 검색 기록 화면
- 10개 까지만 노출 된다.
- 클릭 시 메인 화면으로 해당 키워드 검색 상태가 된다.

