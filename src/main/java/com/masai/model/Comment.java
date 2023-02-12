package com.masai.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue
    private Integer id;
    private String comment;
//    private LocalDateTime timeStamp;

    @JsonIgnore
    @ManyToOne
    private Blog blog;

    @JsonIgnore
    @ManyToOne
    private User user;
}
