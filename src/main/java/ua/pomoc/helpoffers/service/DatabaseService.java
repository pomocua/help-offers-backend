package ua.pomoc.helpoffers.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface DatabaseService<T, K> {
    List<T> findAll();
    T findById(K key);
    T save(T type);
    void delete(T type);
}
