package com.demo.crm.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CustomerDTO {

    private String name;

    private BigDecimal balance;

    private String createdBy;

    private String lastModifiedBy;
}
