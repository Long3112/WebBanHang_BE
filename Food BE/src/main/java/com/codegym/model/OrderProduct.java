package com.codegym.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.sql.In;


@Entity
@Data
@NoArgsConstructor
public class OrderProduct {
       @EmbeddedId
       private OrderProductPK orderProductPK;

       private Integer quantity;

       @Transient
       public Food getProduct() {
        return this.orderProductPK.getFood();
       }

       @Transient
       public Double getTotalPrice() {
        return getProduct().getPrice() * getQuantity();
    }
}
