# 우밤당낮아

# Introduction
**우밤당낮아** 는 http 통신을 사용하여 node js, mongodb & mongoose, kakao sdk 를 활용하여 사용자들이 카이스트 주변 맛집들에 대해 별점과 리뷰를 남기고, 서로의 리뷰를 확인할 수 있는 안드로이드 어플리케이션입니다.

## 개발환경
- **IDE** : Android Studio, VSCode
- **개발 언어** : Java, Java script

---
## Front-end 

- **IDE**
  - Android Studio

- **개발 언어**
  - Java

- **파일 구성**
  - MainActivity : Kakao SDK 를 활용하여 Kakao 계정으로 로그인 및 앱 실행
  - ResActivity : 3개의 fragment 를 연결 해주는 activity
  
   1. ResInfoFragment : 근처 맛집들의 리스트를 recycler view 로 보여줌
   2. ReviewFragment : 선택된 맛집의 리뷰들을 recycler view 로 보여줌
   3. CommentFragment : 사용자가 선택된 맛집에 대한 별점과 리뷰를 작성함


## Back-end

- **IDE**
  - VSCode

- **개발 언어**
  - Java script

- **파일 구성**
  -retrofit.js : client로부터 신호를 받으면 필요한 정보를 주는 파일
  -app.js : retrofit.js를 관리하는 파일

## Simulation

### login
![login](https://user-images.githubusercontent.com/80109309/149048901-4d086f9c-9d17-4865-a6ee-a04ca4489d37.gif)

### restaurants list
![list](https://user-images.githubusercontent.com/80109309/149049144-bacc02f6-16c8-4c77-9564-771118ac0e0a.gif)

### write review
![write_review](https://user-images.githubusercontent.com/80109309/149049355-246d43dc-9a7c-406a-8b39-edc4222b6ac9.gif)


## 추가 구현
- 리뷰 작성 후 ReviewFragment 에 recycler view 가 나타나지 않는 문제
