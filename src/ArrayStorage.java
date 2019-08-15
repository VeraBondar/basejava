/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    int size;
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < size(); i++)
            storage[i] = null;
        size = 0;
    }

    void save(Resume r) {
        if (get(r.getUuid()) != null) return;
        storage[size()] = r;
        size++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].getUuid() == uuid){
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        if (get(uuid) == null) return;

        int number = 0;
        for (int i = 0; i < size(); i++){
            if (storage[i].getUuid() == uuid) {
                number = i;
                break;
            }
        }
        for (int i = number; i < size(); i++){
            storage[i] = storage[i + 1];
        }
        storage[size] = null;
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumes = new Resume[size()];
        for (int i = 0; i < size(); i++){
            resumes[i] = storage[i];
        }
        return resumes;
    }

    int size() {
        return size;
    }
}
