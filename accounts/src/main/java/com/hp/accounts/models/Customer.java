package com.hp.accounts.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @Column(name="customer_id")
    private Long customerId;

    private  String name;

    private  String email;

    @Column(name = "mobile_number")
    private  String mobileNumber;
}
