
# UrlShortenging(단축 url)
긴 url을 단축시켜, 짧은 url형태로 변환  
ex) https://en.wikipedia.org/wiki/URL_shortening => http://localhost/JZfOQNro  



## 구현
-Url Short 관련 자료 Search  
-ShortKey(8 Character이내)구현조건 맞추기 위해 System.nanoTime() 사용, 64비트 연산  
>64비트 연산중, 64의 8승 초과범위 예외처리(/10)  

-DB 사용없이 개발  
>Short url과 Original url 데이터 저장을 위해 Singleton 방식으로 공유  


## 실행 
-jUnitTest  
>MainControllerTest.java 실행  
>내부 strOriginUrl(OriginalUrl)변경 가능  
>실행결과  
  
![이미지 8](https://user-images.githubusercontent.com/35560053/63237454-0d383a00-c27d-11e9-8990-53fe7c0c74bb.jpg)



## web 실행 
1.textbox에 Original url 입력  
2.'변환'버튼 클릭  
3.하단에 생성된 Short url 클릭  

![이미지 11](https://user-images.githubusercontent.com/35560053/63228192-ab4ee480-c22a-11e9-9085-8cebe2241dda.jpg)



