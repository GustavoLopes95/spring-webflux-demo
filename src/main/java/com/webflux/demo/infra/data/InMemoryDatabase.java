package com.webflux.demo.infra.data;

import com.webflux.demo.infra.data.interfaces.IDatabase;
import lombok.SneakyThrows;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class InMemoryDatabase<T> implements IDatabase<T> {

    private final Map<String, T> database;

    public InMemoryDatabase() {
        this.database = new ConcurrentHashMap<>();
    }

    @Override
    @SneakyThrows
    public T save(final String key, final T entity) {
        this.database.put(key, entity);
        Thread.sleep(300);
        return entity;
    }

    @Override
    @SneakyThrows
    public T get(String key) {
        Thread.sleep(15);
        return this.database.get(key);
    }
}
