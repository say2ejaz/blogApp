package com.masai.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "_users")
public class User {

    @Id
    @GeneratedValue
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
    private String gender;
    private String password;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<Blog> blogs;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> comments;
}
