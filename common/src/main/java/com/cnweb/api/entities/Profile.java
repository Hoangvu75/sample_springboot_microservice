package com.cnweb.api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String accountId;
    private String name;
    private String phone;
    private String address;
    private Date dateOfBirth;
    private String gender;
    private Long createdAt;
    private Long updatedAt;
    private Long deletedAt;

    @PrePersist
    private void prePersist() {
        createdAt = System.currentTimeMillis();
        updatedAt = System.currentTimeMillis();
    }
}
