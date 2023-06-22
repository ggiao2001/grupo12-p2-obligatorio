package uy.edu.um.prog2.adt.Entities;

public class HashTag {
    private Long id;
    private String text;
    private int usosDia;

    public HashTag() {
        usosDia = 0;
    }

    public HashTag(long id, String text) {
        this.id = id;
        this.text = text;
        this.usosDia = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getUsosDia() {
        return usosDia;
    }

    public void setUsosDia(int usosDia) {
        this.usosDia = usosDia;
    }

}
