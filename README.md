# Android-Client-Test [![GitHub release (latest by date)](https://img.shields.io/github/v/release/noti-dropper/android-client-test)](https://github.com/noti-dropper/android-client-test/releases) ![GitHub](https://img.shields.io/github/license/noti-dropper/android-client-test)

안드로이드와 백엔드 API 서버 비동기 통신 예제입니다.



## 설명 

- `POST`: `http://noti-drawer.run.goorm.io/api/analyze-sentence`

- `object`*(body)* : 

  ```javascript
  {"sentence": "입력할 문장"}
  ```

- 서버 repo: [Link]( https://github.com/noti-dropper/backend )

- 서버와의 **통신이 실패**했을 경우, **빈 문자열`""`을 반환**함 :arrow_right: 에러 처리 시 활용



## 참고

- [HttpURLConnection+비동기 처리 예제]( http://webs.co.kr/index.php?mid=http&document_srl=3314894 )

- [AsyncTask 설명]( https://youngest-programming.tistory.com/11 )

- [안드로이드 Network StrictMode 이슈 해결]( https://stackoverflow.com/questions/22395417/error-strictmodeandroidblockguardpolicy-onnetwork )
