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
  -in workspace5 folder
  -retrofit.js : client로부터 신호를 받으면 필요한 정보를 주는 파일
  -app.js : retrofit.js를 관리하는 파일

## Simulation

### login
![login](https://user-images.githubusercontent.com/80109309/149049735-6ffb47e7-8b69-4c0c-afcb-601dc9f3097b.gif)

### restaurants list
![list](https://user-images.githubusercontent.com/80109309/149049773-157fb48c-1f22-4edf-9f17-6d13eb56e2d1.gif)

### write review
![review](https://user-images.githubusercontent.com/80109309/149049855-1098ce70-cb59-41d3-a2fe-619ef54a3be8.gif)

### updated list
![updated](https://user-images.githubusercontent.com/80109309/149050271-19dbf2e4-f85d-4aa7-b706-05b5b20b1910.png)


## 추가 구현
- 리뷰 작성 후 ReviewFragment 에 recycler view 가 나타나지 않는 문제
