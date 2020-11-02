package com.netcracker.edu.Contracts;

import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter(AccessLevel.PROTECTED)
public abstract class Contract {

    protected final int id;
    private static int nextId = 0;
    private LocalDate creationDate;
    private LocalDate expirationDate;

    /**
     * id field a nextId inner iterator
     * @param creationDate Contract creation date
     * @param expirationDate Contract expiration date
     */
    public Contract(LocalDate creationDate, LocalDate expirationDate)
    {
        this.id = nextId++;
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;
    }
}