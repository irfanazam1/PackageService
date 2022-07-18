package com.example.services.packageservice.controller;

import com.example.services.packageservice.model.PackageInfo;
import com.example.services.packageservice.service.PackageService;
import com.example.services.packageservice.utils.Constants;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.API_PACKAGESERVICE)
/**
 * Package service controller.
 */
public class PackageServiceController {
    private PackageService packageService;
    private static Logger log = LoggerFactory.getLogger(PackageServiceController.class);

    @Autowired PackageServiceController(PackageService packageService){
        this.packageService = packageService;
    }


    @ApiResponse(responseCode = "400", description = "Some data is missing or not in proper format. Check error message for details.")
    @ApiResponse(responseCode = "500", description = "Unknown exception occurred.")
    @ApiResponse(responseCode = "200", description = "Successfully got the package by id.")
    @ApiResponse(responseCode = "404", description = "Package with the given id is not present.")
    @GetMapping(value = "/" + Constants.PACKAGE_RESOURCE_NAME + "/" + Constants.PATH_VARIABLE_PACKAGE_ID)
    public HttpEntity<PackageInfo> getPackageById(
            @Parameter(description = "Package Id to be retrieved")
            @PathVariable(value = "id") int packageId){
        log.info("Getting package by id: {}", packageId);
        return ResponseEntity.ok(packageService.findPackage(packageId));
    }

    @ApiResponse(responseCode = "204", description = "There are no packages in the database.")
    @ApiResponse(responseCode = "500", description = "Unknown exception occurred.")
    @ApiResponse(responseCode = "200", description = "Successfully got the packages.")
    @GetMapping(value = "/" + Constants.PACKAGE_RESOURCE_NAME)
    public HttpEntity<List<PackageInfo>> getAllPackages(){
        log.info("Getting package all packages");
        return ResponseEntity.ok(packageService.findAllPackages());
    }

    @ApiResponse(responseCode = "400", description = "Some data is missing or not in proper format. Check error message for details.")
    @ApiResponse(responseCode = "500", description = "Unknown exception occurred.")
    @ApiResponse(responseCode = "200", description = "Successfully deleted the package by id.")
    @ApiResponse(responseCode = "404", description = "Package with the given id is not present.")
    @DeleteMapping(value = "/" + Constants.PACKAGE_RESOURCE_NAME + "/" + Constants.PATH_VARIABLE_PACKAGE_ID)
    public HttpEntity<Boolean> deletePackageById(
            @Parameter(description = "Package id to be deleted")
            @PathVariable(value = "id") int packageId){
        log.info("Deleting package by id: {}", packageId);
        return ResponseEntity.ok(packageService.deletePackage(packageId));
    }

    @ApiResponse(responseCode = "400", description = "Some data is missing or not in proper format. Check error message for details.")
    @ApiResponse(responseCode = "400", description = "A package with the same name is already present.")
    @ApiResponse(responseCode = "500", description = "Unknown exception occurred.")
    @ApiResponse(responseCode = "200", description = "Successfully added the package")
    @PostMapping(value = "/" + Constants.PACKAGE_RESOURCE_NAME)
    public HttpEntity<PackageInfo> addPackage(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Package Info JSON Object.")
            @RequestBody PackageInfo packageInfo){
        log.info("Adding a new package by name: {}", packageInfo.getName());
        return ResponseEntity.ok(packageService.savePackage(packageInfo));
    }

    @ApiResponse(responseCode = "400", description = "Some data is missing or not in proper format. Check error message for details.")
    @ApiResponse(responseCode = "500", description = "Unknown exception occurred.")
    @ApiResponse(responseCode = "200", description = "Successfully updated the package")
    @ApiResponse(responseCode = "404", description = "Package with the given id is not present.")
    @ApiResponse(responseCode = "400", description = "A package with the same name is already present.")
    @PutMapping(value = "/" + Constants.PACKAGE_RESOURCE_NAME + "/" + Constants.PATH_VARIABLE_PACKAGE_ID)
    public HttpEntity<PackageInfo> updatePackage(
            @Parameter(description = "Package id to be updated") @PathVariable(value = "id") Integer packageId,
                                                @io.swagger.v3.oas.annotations.parameters.RequestBody (description = "Package Info Object")
                                                @RequestBody PackageInfo packageInfo){
        log.info("Updating package by id: {}", packageId);
        return ResponseEntity.ok(packageService.updatePackage(packageInfo));
    }

}
