package com.codegym.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Embeddable
@Data
public class OrderProductPK implements Serializable {
    @JsonBackReference
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Orders order;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;

    @Override
    public String toString() {
        return "OrderProductPK{" +
                "order=" + order +
                ", food=" + food +
                '}';
    }
}
