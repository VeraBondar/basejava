package com.basejava.storage;

import com.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    int size;
    Resume[] storage = new Resume[10000];

    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    public void save(Resume resume) {
        if (size == storage.length) {
            System.out.println("The storage is completely filled");
            return;
        }
        if (get(resume.getUuid()) != null) return;
        storage[size] = resume;
        size++;
    }

    public void update(Resume resume) {
        int number = getIndex(resume.getUuid());
        if (number == -1) {
            System.out.println("com.basejava.model.Resume is not found");
            return;
        }
        storage[number] = resume;
    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid() == uuid) {
                return storage[i];
            }
        }
        return null;
    }

    public void delete(String uuid) {
        if (get(uuid) == null) return;

        int number = getIndex(uuid);
        for (int i = number; i < size - 1; i++) {
            storage[i] = storage[i + 1];
        }
        storage[size] = null;
        size--;
    }

    int getIndex(String uuid) {
        int number = -1;
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid() == uuid) {
                number = i;
                break;
            }
        }
        return number;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }
}
