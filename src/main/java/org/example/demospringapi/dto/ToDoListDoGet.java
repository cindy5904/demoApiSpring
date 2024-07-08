package org.example.demospringapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToDoListDoGet {
    private Long id;
    private String titre;
    private String description;
    private LocalDate date;
    private boolean isvalidate;
}
