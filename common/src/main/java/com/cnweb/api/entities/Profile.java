package com.cnweb.api.entities;

import com.cnweb.api.models.DateOfBirth;
import com.cnweb.api.models.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private DateOfBirth dateOfBirth;
    private Gender gender;
    private Long createdAt;
    private Long updatedAt;
    private Long deletedAt;
    private Boolean isActive;

    @PrePersist
    private void prePersist() {
        createdAt = System.currentTimeMillis();
        updatedAt = System.currentTimeMillis();
        isActive = true;
    }
}
