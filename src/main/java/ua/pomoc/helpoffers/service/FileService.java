package ua.pomoc.helpoffers.service;

import java.util.List;

public interface FileService<T> {
    List<T> findAll();
    List<T> saveAll();
}
