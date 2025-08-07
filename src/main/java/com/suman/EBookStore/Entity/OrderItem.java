package com.suman.EBookStore.Entity;

import jakarta.persistence.Entity;
import lombok.*;

import java.util.Map;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderItem {
    private long orderItemId;
    private Map<Books,Integer> booksIteam;

}
