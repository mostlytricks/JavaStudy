package com.mostlytricks.study.domain.accounts;

import com.mostlytricks.study.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor // 기본 생성자 추가
@Entity // JPA, className to TABLE_NAME
public class Accounts extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String type; // debit or credit

    @Column(nullable = false)
    private long accountTypeId;

    private LocalDateTime transactionTime;
    private BigInteger remainedAmount;

    @Builder
    public Accounts(String type, long accountTypeId, BigInteger remainedAmount){
        this.type = type;
        this.accountTypeId= accountTypeId;
        this.remainedAmount= remainedAmount;
    }

//    uid :long
//    type : String -> debit / credit
//    accountTypeUid : long -> Account의 세부 정보 관리 위한 relation
//    transactionTime : LocalDateTime | TimeStamp
//    transactionAmount : BigInteger -> 해당 트랜잭션으로 인한 금액 copy
//    trasactionRemainAmount :BigInteger-> 계정별 해당 트랜잭션 후 잔여액

}
