package com.suman.EBookStore.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BooksDto {
    private int bookId;
    @NotBlank
    private String bookName;
    private double price;
}
