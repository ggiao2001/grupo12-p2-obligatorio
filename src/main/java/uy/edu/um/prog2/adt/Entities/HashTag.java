package uy.edu.um.prog2.adt.Entities;

import java.util.Objects;

public class HashTag {
    private String id;
    private String text;
    private int usosDia;

    public HashTag() {
        usosDia = 0;
    }

    public HashTag(String id, String text) {
        this.id = id;
        text = text.trim();
        text = text.toLowerCase();
        this.text = text;
        this.usosDia = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        // saco espacios antes y despues
        text = text.trim();

        // transformo en lowercase
        text = text.toLowerCase();

        // Set el texto
        this.text = text;
    }

    public int getUsosDia() {
        return usosDia;
    }

    public void setUsosDia(int usosDia) {
        this.usosDia = usosDia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HashTag hashTag = (HashTag) o;
        return text.equals(hashTag.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }
}
