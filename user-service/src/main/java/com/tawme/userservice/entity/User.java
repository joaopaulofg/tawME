package com.tawme.userservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    @Column(name="ID")
    private UUID id;

    @Column(name="NAME")
    @NonNull
    private String name;

    @Column(name="PHONE")
    @NonNull
    private String phoneNumber;

}
