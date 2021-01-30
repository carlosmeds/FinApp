package br.ufpr.tads.finapp.model;

public class TransactionCategory {
    private Double value;
    private String typeName;
    private int typeImage;

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

    public int getTypeImage() {
        return typeImage;
    }

    public void setTypeImage(int typeImage) {
        this.typeImage = typeImage;
    }
}
