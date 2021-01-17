package br.ufpr.tads.finapp.model;

import java.io.Serializable;

public class TransactionType implements Serializable {
    private Long id;
    private String description;

    public TransactionType(Long id, String type) {
        this.id = id;
        this.description = type;
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
