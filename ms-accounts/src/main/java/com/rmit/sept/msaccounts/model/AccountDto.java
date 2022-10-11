package com.rmit.sept.msaccounts.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "accounts")
public class AccountDto {

    public AccountDto() {
        this.date = LocalDate.now();
    }

    @Column(name = "account_id")
    private String accountHolderId;

    @Id
    @GeneratedValue
    @Column(name = "account_number")
    private Integer accountNumber;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "account_balance")
    private String balance;

    @Column(name = "account_date")
    private LocalDate date;

    @Column(name="account_type", nullable=false)
    @Enumerated(EnumType.STRING)
    private AccountType accountType;


    //    @OneToMany(mappedBy = "accounts", fetch = FetchType.LAZY)
//    private List<PersonDto> listPeople = new ArrayList<>();
}
