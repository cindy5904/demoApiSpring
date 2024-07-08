package org.example.demospringapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoListDoPost {
    private String titre;
    private String description;
    private String date;
    private boolean isvalidate;
}
