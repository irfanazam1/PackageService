package com.example.services.packageservice.model;

import com.example.services.packageservice.controller.PackageServiceController;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Data
@With
@AllArgsConstructor
@NoArgsConstructor
@Entity
/**
 * Database entity and DTO to store the package info data.
 */
public class PackageInfo extends RepresentationModel<PackageInfo> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int seqId;
    @JsonProperty(required = true)
    private String name;
    private double price;
    private boolean active = false;
    private String description;
    private int duration;
    private Date creationDate;
    private Date lastModifiedDate;

    /**
     * Adds the links to Package Info Resource.
     */
    public void addLinks(){
        add(WebMvcLinkBuilder.linkTo(methodOn(PackageServiceController.class).getAllPackages()).withRel("packages"));
        add(linkTo(methodOn(PackageServiceController.class).getPackageById(seqId)).withSelfRel());
    }
}
