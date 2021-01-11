package com.ciklum.services.packageservice.service;

import com.ciklum.services.packageservice.model.PackageInfo;

import java.util.List;

/**
 * The PackageService interface to encapsulate the PackageRepsitory.
 */
public interface PackageService {
    /**
     * Saves a new package. Will throw DuplicatePackageException if a package with the same name is already present.
     * @param packageInfo Package info object.
     * @return PackageInfo object with randomly generated id.
     */
    PackageInfo savePackage(PackageInfo packageInfo);

    /**
     * Finds a Package with the given id.
     * @param id Id for which the package needs to be retrieved.
     * @return PackageInfo object. Will throw package not found exception if there is no package in the database.
     */
    PackageInfo findPackage(int id);

    /**
     * Finds all the packages.
     * @return List of all packages in the database.
     */
    List<PackageInfo> findAllPackages();

    /**
     * Updates a given package. Will throw duplicate package exception if a package with the same name already exists.
     * It won't update the following fields even if they are sent in the payload.
     * - Creation Date.
     * - Last Modified Date.
     * @param packageInfo Package info object.
     * @return
     */
    PackageInfo updatePackage(PackageInfo packageInfo);

    /**
     * Deletes a package by id. Will throw exception if the package not found.
     * @param id Id of the package to be deleted.
     * @return True or False.
     */
    boolean deletePackage(int id);
}
