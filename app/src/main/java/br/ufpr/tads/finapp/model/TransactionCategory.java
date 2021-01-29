package br.ufpr.tads.finapp.model;

public class TransactionCategory {
    private Double value;
    private String typeName;

    public TransactionCategory() {
    }

    public Double getValue() {
        return value;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
