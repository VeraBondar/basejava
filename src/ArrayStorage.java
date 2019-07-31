/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i <= size(); i++)
            storage[i] = null;
    }

    void save(Resume r) {
        if (get(r.getUuid()) != null) return;
        storage[size()] = r;
    }

    Resume get(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].getUuid() == uuid)
                return storage[i];
        }
        return null;
    }

    void delete(String uuid) {
        if (get(uuid) == null) return;

        int number = 0;
        for (int i = 0; i < size(); i++)
            if (storage[i].getUuid() == uuid) {
                number = i;
                break;
            }
        for (int i = number; i < size(); i++)
            storage[i] = storage[i + 1];
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        if (size() == 0)
            return new Resume[0];

        Resume[] shortStroage = new Resume[size()];
        for (int i = 0; i < size(); i++)
            shortStroage[i] = storage[i];
        return shortStroage;
    }

    int size() {
        int count = 0;
        for (Resume resume : storage) {
            if (resume != null)
                count++;
        }
        return count;
    }
}
