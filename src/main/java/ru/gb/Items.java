package ru.gb;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "items")
@Data
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;
}
