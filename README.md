# kpassignment
kpassignment

김진협(zeraf29@gmail.com) - 카카오페이 - 과제 

========================================

1. 과제유형: 사전과제2(생태 정보 서비스 API 개발)
2. 개발 프레임워크 : Spring Boot (Spring Tool Suite 4 사용)
3. 개발 언어: Java (OpenJdk 11.0.2)
4. 과제 Github 주소: https://github.com/zeraf29/kpassignment
5. 문제해결전략
6. 빌드 및 실행 방법
   1. Gradle 사용 - Gradle 프로젝트로 소스 설정 및 Dependency 설정
   2. IDE(이클립스)에 import 후 Sping-Boot 기본 Web서비스로 구동(Spring Boot App) - 테스트 과제 이므로 별도 WAS 설정 안함.
   3. 사용 DBMS - MaraiDB (해당 DBMS에 종속되는 Repository 사용을 안했으므로, 필요에 따라 application.properties에서 타 DBMS로 변경후 사용 가능)
   4. 해당 구현 항목에 대해 Postman을 통해 GET/POST API 기능 확인
   5. 각 구현 항목별 API 주소는 하기와 같음
      1. http://localhost:8080/api/insert/csv : [GET] 로컬 static 경로상의 CSV를 DB Table로 insert
      2. http://127.0.0.1:8080/api/search/regcode/{지역코드} : [GET] 지역코드로 검색 시 상세 데이터 조회
      3. http://127.0.0.1:8080/api/insert/record : [POST] 단일 관광정보 데이터 추가 
      4. http://127.0.0.1:8080/api/update/record : [POST] 데이터 수정 항목, 전체 데이터 항목(column)이 모두 필수
      5. http://127.0.0.1:8080/api/search/region/{검색지역명} : [GET] 지역명으로 검색 시 프로그램명/테마 리턴
      6. http://127.0.0.1:8080/api/search/introduce/{프로그램 소개에서 검색할 키워드} : [GET] 프로그램 소개 컬럼에서 특정 문자열이 포함된 레코드에서 서비스 지역 개수 리턴
      7. http://127.0.0.1:8080/api/count/detail/{상세정보에서 검색할 키워드} : [GET] 모든 레코드의 프로그램 상세 정보에서 입력 단어 출현빈도수 계산/출력
7. 비고
   1. 공공데이터CSV 등 일부 한글 CSV에 대해 맥북에서는 한글이 깨지는 현상 발생.
      과제 데이터를 본 과제 프로그램으로 load할 때 한글이 깨질 경우 insertCsv() 메서드에서 CSVReader를 UTF-8에 맞게 변형 후 진행
      (현재 주석처리 되어 있음)
   2. 관광 데이터는 eco_tourism(EcoTourism 엔티티)에 저장되어 있음.
      해당 테이블의 PK는 idx와 code의 복합키임 (idx = pk, code = pk)
   3. 지역 코드의 경우 서비스 지역명이 같을 경우 같은 값을, 다를 경우 다른 값을 임의 컬럼에 부여
      (임의 구분 없이 글자수 등이 다르면 다른 지역으로 간주)
