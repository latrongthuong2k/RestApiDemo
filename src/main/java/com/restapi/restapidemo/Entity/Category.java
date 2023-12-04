package com.restapi.restapidemo.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "catalog_id")
    private int id;

    @Column(name = "catalog_name", length = 100, nullable = false, unique = true)
    private String name;

    @Column(name = "descriptions")
    private String description;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "catalog_status")
    private Boolean status;

    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();

}