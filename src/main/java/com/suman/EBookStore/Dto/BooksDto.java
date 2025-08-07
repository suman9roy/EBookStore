package com.suman.EBookStore.Dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BooksDto {
    private int bookId;
    private String bookName;
    private double price;
}
