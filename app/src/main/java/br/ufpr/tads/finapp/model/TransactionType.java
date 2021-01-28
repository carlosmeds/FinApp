package br.ufpr.tads.finapp.model;

import java.io.Serializable;

public class TransactionType implements Serializable {
    private Long id;
    private String description;

    public TransactionType() {
    }

    public TransactionType(Long id, String description) {
        this.id = id;
        this.description=description;
    }

    public TransactionType(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return description;
    }

    public void setType(String type) {
        this.description = type;
    }
}
