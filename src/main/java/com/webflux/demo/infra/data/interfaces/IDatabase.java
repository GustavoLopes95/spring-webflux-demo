package com.webflux.demo.infra.data.interfaces;

public interface IDatabase<T> {

    T save(final String key, final T entity);

    T get(final String key);
}
