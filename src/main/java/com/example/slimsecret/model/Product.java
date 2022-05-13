package com.example.slimsecret.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Product {
    @Id
    private Long id;
    private String name;
    private String calories;
    private String fat;
    private String protein;
    private String carbohydrates;
}
