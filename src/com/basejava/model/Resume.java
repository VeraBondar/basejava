package com.basejava.model;

/**
 * Initial resume class
 */
public class Resume {

    // Unique identifier
    private String uuid;

    @Override
    public String toString() {
        return getUuid();
    }

    public Resume(String uuid) {
        this.setUuid(uuid);
    }

    public Resume() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Resume)) return false;

        Resume resume = (Resume) o;

        return getUuid().equals(resume.getUuid());
    }

    @Override
    public int hashCode() {
        return getUuid().hashCode();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
