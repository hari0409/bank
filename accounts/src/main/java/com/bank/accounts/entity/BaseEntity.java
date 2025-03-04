package com.bank.accounts.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@MappedSuperclass
public class BaseEntity {
    @Column(updatable = false) // Cannot be updated
    private LocalDateTime createdAt;

    @Column(updatable = false) // Cannot be updated
    private String createdBy;

    @Column(insertable = false) // Cannot be inserted
    private LocalDateTime updatedAt;

    @Column(insertable = false) // Cannot be inserted
    private String updatedBy;

}
