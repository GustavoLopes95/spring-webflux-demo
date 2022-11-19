package com.webflux.demo.infra.data.repositories;

import com.webflux.demo.domain.entities.Employee;
import com.webflux.demo.domain.repositories.IEmployeeRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeRepositoryInMemory implements IEmployeeRepository {

    private Map<Long, Employee> database;

    public EmployeeRepositoryInMemory() {
        this.database = new HashMap<>();

        this.bootstrap();
    }

    private void bootstrap() {
        final var employee = new Employee();
        employee.criar("nome xpto");

        this.database.put(1L, employee);
    }

    @Override
    public Flux<Employee> getEmployees() {
        return null;
//        return this.database.values();
    }
}
