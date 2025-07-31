package com.suman.EBookStore.Entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Books {
    int bookId;
    String bookName;
    double price;
}
