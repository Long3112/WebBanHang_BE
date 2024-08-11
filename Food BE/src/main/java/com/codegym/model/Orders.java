package com.codegym.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "orders_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private Double total;
    private String status;
    private String shippingAddress;

    private String note;

    @ManyToOne
    private User user;
    @ManyToMany
    private Set<Shop> shops;

    @ManyToOne
    private Delivery delivery;

    @ManyToMany
    @JoinTable(
            name = "coupons_orders",
            joinColumns = @JoinColumn(name = "orders_id"),
            inverseJoinColumns = @JoinColumn(name = "coupons_id"))
    private Set<Coupon> coupons;


    @OneToMany(mappedBy = "orderProductPK.order")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Set<OrderProduct> foods;
}
