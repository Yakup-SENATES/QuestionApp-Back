package com.example.fs.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    private Long id;


    String userName;
    String password;

}
