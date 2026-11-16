package com.mecerintered.springbootprojecttwo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "students")
@Entity
public class Students {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",nullable = false)
    private String firstName;
    @Column(name = "surname",nullable = false)
    private String lastName;
    @Column(name = "email_ref",nullable = false,unique = true)
    private String email;


}
