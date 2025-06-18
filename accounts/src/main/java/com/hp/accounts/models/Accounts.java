package com.hp.accounts.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
public class Accounts extends BaseEntity {


    @Column(name="customer_id")
    private Long customerId;

    @Id
    private  Long accountNumber;

    @Column(name = "account_type")
    private  String accountType;

    @Column(name = "branch_address")
    private  String branchAddress;
}
