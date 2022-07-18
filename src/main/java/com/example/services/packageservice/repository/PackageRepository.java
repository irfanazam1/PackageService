package com.example.services.packageservice.repository;

import com.example.services.packageservice.model.PackageInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
/**
 * Database repository for Package Info Entity.
 */
public interface PackageRepository extends CrudRepository<PackageInfo, Integer> {
    /**
     * Finds a package by name. Used to check if a package with a same name is alrady present or not?
     * @param name
     * @return
     */
    Optional<PackageInfo> findByName(String name);
}
