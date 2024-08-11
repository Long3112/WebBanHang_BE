package com.codegym.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@Data
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private boolean status;
    private String type;
    private Date startDate;
    private Date endDate;
    private Integer quantity;
    private Double discount;

    @ManyToOne
    private Shop shop;
}
