package com.bank.accounts.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @Column(updatable = false) // Cannot be updated
    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(updatable = false) // Cannot be updated
    private String createdBy;

    @LastModifiedDate
    @Column(insertable = false) // Cannot be inserted
    private LocalDateTime updatedAt;

    @LastModifiedBy
    @Column(insertable = false) // Cannot be inserted
    private String updatedBy;

}
