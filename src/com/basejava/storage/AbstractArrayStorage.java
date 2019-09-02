package com.basejava.storage;

import com.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage {
    private final int storagelimit = 10000;
    protected static int size;
    protected Resume[] storage = new Resume[storagelimit];

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
        return storage[index];
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int number = getIndex(resume.getUuid());
        if (number < 0) {
            System.out.println("com.basejava.model.Resume is not found");
            return;
        }
        storage[number] = resume;
    }

    public int size() {
        return size;
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected abstract int getIndex(String uuid);

    public void save(Resume resume) {
        if (size == storage.length) {
            System.out.println("The storage is completely filled");
            return;
        }
        int index = getIndex(resume.getUuid());
        if (index > 0) return;
        insertElement(resume, index);
        size++;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) return;
        fillDeletedElement(index);
        storage[size - 1] = null;
        size--;
    }

    protected abstract void fillDeletedElement(int index);

    protected abstract void insertElement(Resume resume, int index);


}
