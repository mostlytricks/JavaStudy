package com.mostlytricks.study.domain.transactions;

import com.mostlytricks.study.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Transactions extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private BigInteger amount;

    @Column(nullable = false)
    private long debitAccountId;

    @Column(length = 500)
    private String debitAccount;

    @Column(nullable = false)
    private long creditAccountId;

    @Column(length = 500)
    private String creditAccount;

    private long incomeStatementId;

    private String transactionType;

    private LocalDate transactionTime;

    private boolean closed;

    private LocalDateTime closedTime;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comments;

    @Builder(toBuilder = true) // 이 경우 생성자 포함된 필드만 빌더
    public Transactions (BigInteger amount, long debitAccountId, long creditAccountId, String comments){
        this.amount = amount;
        this.debitAccountId= debitAccountId;
        this.creditAccountId = creditAccountId;
        this.comments = comments;
    }

    public void update(BigInteger amount,String comments){
        // 금액과 주석만 우선 갱신토록.Id등의 세부 시나리오는 추후 구상 필요.
        this.comments= comments;
        this.amount = amount;
    }

//    uid : long
//    amount : BigIneteger -> 트랜잭션별 금액
//    debitAccountUid : long
//    debitAccount : String -> 수익/비용은 Income, Cost의 계정으로 우선 처리할 것
//    creditAccountUid : long
//    creditAccount :String
//    incomeStatementUid -> 순수익/비용 인식을 위한 incomeStatement schema와 relation key -> 지출 내역 등은 상세 출처, 유형등 스키마의 확장 가능성이 보다 큼
//    transactionType : String -> 정산(수정분개), 통상거래, 자산재분배, 부채대금 청산 -> 내부 자본 이동은 소득, 지출에 계상치 않기 위한 방안
//    transactionTime : LocalDateTime | TimeStamp
//    closed : booelan -> 해당 기록에 대한 무결성 확정, 수정
//    closedTime :
//    Comments : 트랜잭션에 대한 메모
}
