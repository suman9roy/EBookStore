package com.suman.EBookStore.Entity;

import jakarta.persistence.Entity;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Orders {
    private long orderId;
    private String address;
    private double amount;
    private OrderItem orderItem;

}
