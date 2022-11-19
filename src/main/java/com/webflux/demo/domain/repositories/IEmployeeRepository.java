package com.webflux.demo.domain.repositories;

import com.webflux.demo.domain.entities.Employee;
import reactor.core.publisher.Flux;

import java.util.Collection;

public interface IEmployeeRepository {

    Flux<Employee> getEmployees();
}
