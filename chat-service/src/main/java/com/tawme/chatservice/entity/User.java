package com.tawme.chatservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @Column(name="ID")
    private UUID id;

    @Column(name="NAME")
    @NonNull
    private String name;

    @Column(name="PHONE")
    @NonNull
    private String phoneNumber;
}
