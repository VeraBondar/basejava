package com.basejava.exception;

public class NotExistsStorageException extends StorageException {
    public NotExistsStorageException(String uuid) {
        super("Resume " + uuid + " is not exist", uuid);
    }
}
