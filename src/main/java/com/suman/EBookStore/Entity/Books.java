package com.suman.EBookStore.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;
    private String bookName;
    private double price;
}
