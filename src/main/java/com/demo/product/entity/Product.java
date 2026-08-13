package com.demo.product.entity;

import com.demo.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
public class Product extends BaseEntity<String> {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;
}
