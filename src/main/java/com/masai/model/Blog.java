package com.masai.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    @Id
    @GeneratedValue
    Integer id;
    @Enumerated(EnumType.STRING)
    private Category category;
    private String content;
//    private LocalDateTime dateTime;

    @JsonIgnore
    @ManyToOne
    private User user;

    @JsonIgnore
    @OneToMany
    private List<Comment> comments;
}
