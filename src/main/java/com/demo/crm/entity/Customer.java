package com.demo.crm.entity;

import com.demo.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@Builder
public class Customer extends BaseEntity<String> {

    // The surrogate key (id) is inherited from BaseEntity. The migration's
    // `id bigint DEFAULT nextval('customer_seq')` column supplies the value on insert.

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;

    public Customer(String name, BigDecimal balance) {
        this.name = name;
        this.balance = balance;
    }
}
