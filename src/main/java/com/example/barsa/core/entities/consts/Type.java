package com.example.barsa.core.entities.consts;

public enum Type {
    PROJECT(1),
    PROJECT_RELEASE(2),
    SPRINT(3),
    EPIC(4),
    STORY(5),
    DEV_TASK(6),
    BA_TASK(7),
    QA_TASK(8),
    DEFECT(9),
    ;

    private final long id;

    private Type(long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public static Type getValue(Long id) {
        if (id != null) {
            for (Type value: values()) {
                if (value.id == id) {
                    return value;
                }
            }
        }

        return null;
    }
}
