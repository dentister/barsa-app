package com.example.barsa.core.entities.consts;

public enum Status {
    OPEN(1),
    IN_PROGRESS(2),
    RESOLVED(3),
    CLOSED(4);

    private final long id;

    private Status(long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public static Status getValue(Long id) {
        if (id != null) {
            for (Status value: values()) {
                if (value.id == id) {
                    return value;
                }
            }
        }

        return null;
    }
}
