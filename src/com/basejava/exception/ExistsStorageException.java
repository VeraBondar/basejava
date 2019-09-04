package com.basejava.exception;

public class ExistsStorageException extends StorageException {
    public ExistsStorageException(String uuid) {
        super("Resume " + uuid + " is exist", uuid);
    }
}
