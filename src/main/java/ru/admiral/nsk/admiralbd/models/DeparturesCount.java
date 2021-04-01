package ru.admiral.nsk.admiralbd.models;

public class DeparturesCount {

    private String key;
    private Integer value;


    public DeparturesCount() {
    }

    public DeparturesCount(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public Integer getValue() {
        return value;
    }


}
