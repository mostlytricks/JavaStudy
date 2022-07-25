# JavaStudy

--- 
## 목적 : fin App

### 필요 기능 : 
1. 내부 기능
(1) 거래기록 (Domain : Transaction)
  - 거래기록 자동 집계 (모바일 / 메일 또는 금융권 api활용)
  - 수동 거래기록 등록
  - 각 기록별 수정/삭제 후 갱신
(2) 계정별 관리 (Domain : Account)
  - 수동 자산유형 등록
  - 키변경에 따른 기존 데이터 정합성 검증
(3) 비용/수익 유형 관리 ()
(4) 계정별 테이블 조회
  - 특정 은행 계좌 현황
  - 카드 결제 대금 현황 및 최근 이력 조회
(5) 월별 마감 
- 마감된 기록에 대한 수정 제한 -> 데이터 계층 이동

2. 주요 메뉴
- 월별 정산 -> 정산에 따른 Report 제공화면
- BS -> BalanceSheet, 
- IS -> IncomeSheet
  - 투자 변동 현황
    -> 일자, 6시간마다 단가
    -> 문자 알림, 경고
- Cost tracking
  -> 일/월 지출 패턴
  -> 유형별 소모 금액 비율 (차트)
  -> 일자별 지출계획에 따른 구매 경고
    -> 일평균 a원, 7일차에 a * 7 < 총소비 금액 인 경우 결재후 알람
- Projection 
  -> 저축 계획, 정산 대비 저축 실적, 예상 금액 확보 Projection
  -> 청산 시나리오
  -> 이자 확보 시나리오
  -> 목돈 필요시점 등록 -> 유동자산 관리
  
---
## 개발 이력
- 기본 템플릿 구성   <<< 
- 도메인 정의

---
## Schema 고려
### TRANSACTION
- uid : long
- amount : BigIneteger -> 트랜잭션별 금액
- debitAccountUid : long
- debitAccount : String -> 수익/비용은 Income, Cost의 계정으로 우선 처리할 것
- creditAccountUid : long
- creditAccount :String
- incomeStatementUid
  -> 순수익/비용 인식을 위한 incomeStatement schema와 relation key
  -> 지출 내역 등은 상세 출처, 유형등 스키마의 확장 가능성이 보다 큼
- transactionType : String
  -> 정산(수정분개), 통상거래, 자산재분배, 부채대금 청산
  -> 내부 자본 이동은 소득, 지출에 계상치 않기 위한 방안
- transactionTime : LocalDateTime | TimeStamp
- closed : booelan -> 해당 기록에 대한 무결성 확정, 수정 
- closedTime : 
- Comments : 트랜잭션에 대한 메모

### Account
- uid :long
- type : String -> debit / credit
- accountTypeUid : long -> Account의 세부 정보 관리 위한 relation
- transactionTime : LocalDateTime | TimeStamp 
- transactionAmount : BigInteger -> 해당 트랜잭션으로 인한 금액 copy
- trasactionRemainAmount :BigInteger-> 계정별 해당 트랜잭션 후 잔여액

### AccountType
- uid : long
- type : debit /credit
- subtype 1: String -> 계층에 따른 정보 : 유형/무형자산
- subtype 2: String -> 현금성 자산, 매출채권
- subtype 3: String -> 복잡도 증가시 활용
- physicalSource : String -> 은행/계좌/창구 명칭 등 분할 시 이용.

## AccountLedger -> 정산 완료된 내용을 담는 분개장


### IncomeCostLedger
- uid : long
- type : String -> income | cost
- amount : BigInteger
- categoryId : long -> 비용 유형의 확장성 위한 테이블 분리
- transactionUid :long
  -> 트랜잭션과 상호 1:1 맵핑이 필요할까?
- transactionTime
- Comments 

### IncomeCostType
- uid :long
- type : String -> income/cost
- subtype : String -> fixed/non-fixed
- segment : String -> 의, 식, 주, 교육, 경조사, 교우, 화장품 등
- focused : boolean -> 주시대상인지 마킹하는걸로
- comments/name :String -> 이름또는 세부 항목 기재 필요한 건은 직접 작성하는게 낫겠다. 주류 기록이라던지.
