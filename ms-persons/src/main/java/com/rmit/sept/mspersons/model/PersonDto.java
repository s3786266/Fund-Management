package com.rmit.sept.mspersons.model;

import lombok.*;
import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "persons")
public class PersonDto {

    @Id
    @GeneratedValue
    @Column(name = "person_id")
    private Integer personId;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "postcode")
    private String postcode;

    @Column(name = "age")
    private String age;

    @Column(name = "job")
    private String job;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "accountDto.accountHolderId")
//    private AccountDto accounts;
}
