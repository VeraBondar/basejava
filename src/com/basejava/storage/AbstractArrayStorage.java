package com.basejava.storage;

import com.basejava.exception.ExistsStorageException;
import com.basejava.exception.NotExistsStorageException;
import com.basejava.exception.StorageException;
import com.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage {
    protected static final int storagelimit = 10000;
    protected static int size;
    protected Resume[] storage = new Resume[storagelimit];

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistsStorageException(uuid);
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
            throw new NotExistsStorageException(resume.getUuid());
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
            new StorageException("The storage is completely filled", resume.getUuid());
            return;
        }
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            throw new ExistsStorageException(resume.getUuid());
        }
        insertElement(resume, index);
        size++;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistsStorageException(uuid);
        }
        fillDeletedElement(index);
        storage[size - 1] = null;
        size--;
    }

    protected abstract void fillDeletedElement(int index);

    protected abstract void insertElement(Resume resume, int index);


}
