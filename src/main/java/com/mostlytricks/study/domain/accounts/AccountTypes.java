package com.mostlytricks.study.domain.accounts;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class AccountTypes {
    @Id
    @GeneratedValue
    private long id;

    private String type;
    private String subtype1;
    private String subtype2;
    private String subtype3;
    private String physicalSource; //은행

    @Builder
    public AccountTypes(String type, String physicalSource){
        this.type = type;
        this.physicalSource = physicalSource;
    }
//    AccountType
//    uid : long
//    type : debit /credit
//    subtype 1: String -> 계층에 따른 정보 : 유형/무형자산
//    subtype 2: String -> 현금성 자산, 매출채권
//    subtype 3: String -> 복잡도 증가시 활용
//    physicalSource : String -> 은행/계좌/창구 명칭 등 분할 시 이용.
}
