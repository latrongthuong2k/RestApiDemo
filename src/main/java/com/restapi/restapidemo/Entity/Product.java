package com.restapi.restapidemo.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id" , length = 5)
    private String id;

    @Column(name = "product_name", length = 100,nullable = false, unique = true)
    private String name;

    @Column(name = "price", nullable = false)
    private Float price;

    @CreatedDate
    @Column(name = "created", insertable = false, updatable = false)
    private Date created;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;


    @Column(name = "status")
    private Boolean status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "catalog_id")
    private Category category;
}