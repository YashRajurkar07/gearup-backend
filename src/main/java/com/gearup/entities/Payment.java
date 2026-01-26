package com.gearup.entities;

import java.time.LocalDateTime;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "payments")
@AttributeOverride(name="id", column = @Column(name="payment_ids"))
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Payment extends BaseEntity{

    @Column(name="transaction_id",nullable = false, unique = true)
    private Long transactionId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionMode transactionMode;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private LocalDateTime paymentDateTime;
    
}
