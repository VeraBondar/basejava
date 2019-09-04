package com.basejava.storage;

import com.basejava.exception.ExistsStorageException;
import com.basejava.exception.NotExistsStorageException;
import com.basejava.exception.StorageException;
import com.basejava.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public abstract class AbstractArrayStorageTest {
    private Storage storage;
    private static final int STORAGE_LIMIT = 10000;

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume("uuid1"));
        storage.save(new Resume("uuid2"));
        storage.save(new Resume("uuid3"));
    }

    @Test
    public void get() throws Exception {
        assertEquals(new Resume("uuid1"), storage.get("uuid1"));
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertEquals(0, storage.size());
    }


    @Test
    public void update() throws Exception {
        Resume newResume = new Resume("uuid1");
        storage.update(newResume);
        assertTrue(newResume == storage.get("uuid1"));
    }

    @Test
    public void size() throws Exception {
        assertEquals(3, storage.size());
    }

    @Test
    public void getAll() throws Exception {
        Resume[] array = storage.getAll();
        assertEquals(3, array.length);
        assertEquals(new Resume("uuid1"), array[0]);
        assertEquals(new Resume("uuid2"), array[1]);
        assertEquals(new Resume("uuid3"), array[2]);
    }

    @Test(expected = ExistsStorageException.class)
    public void saveExists() throws Exception {
        storage.save(new Resume("uuid1"));
    }

    @Test
    public void saveTooMuch() throws Exception {
        storage.clear();
        try {
            for (int i = 0; i < STORAGE_LIMIT; i++) {
                storage.save(new Resume("uuid" + ((char) i)));
            }
        } catch (StorageException e) {
            Assert.fail();
        }
    }

    @Test
    public void save() throws Exception {
        storage.save(new Resume("uuid4"));
        assertEquals(new Resume("uuid4"), storage.get("uuid4"));
        assertEquals(4, storage.size());
    }


    @Test(expected = NotExistsStorageException.class)
    public void deleteNotExists() throws Exception {
        storage.delete("uuid5");
    }

    @Test
    public void delete() throws Exception {
        storage.delete("uuid1");
        assertEquals(2, storage.size());
    }

    @Test(expected = NotExistsStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("uuid5");
    }

}