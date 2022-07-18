package com.example.services.packageservice.service;

import com.example.services.packageservice.error.DuplicatePackageNameException;
import com.example.services.packageservice.error.InvalidPayloadException;
import com.example.services.packageservice.repository.PackageRepository;
import com.example.services.packageservice.error.NoContentException;
import com.example.services.packageservice.error.PackageNotFoundException;
import com.example.services.packageservice.model.PackageInfo;
import com.example.services.packageservice.utils.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PackageServiceImpl implements PackageService {

    private PackageRepository packageRepository;

    @Autowired
    public PackageServiceImpl(PackageRepository packageRepository){
        this.packageRepository = packageRepository;
    }

    @Override
    public PackageInfo savePackage(PackageInfo packageInfo) {
        checkPackageContents(packageInfo);
        checkDuplicatePackage(packageInfo.getName());
        packageInfo.setCreationDate(new Date());
        packageRepository.save(packageInfo);
        packageInfo.addLinks();
        return packageInfo;
    }

    @Override
    public PackageInfo findPackage(int id) {
        Optional<PackageInfo> packageInfo = packageRepository.findById(id);
        if(packageInfo.isPresent()){
            packageInfo.get().addLinks();
            return packageInfo.get();
        }
        throw new PackageNotFoundException(id);
    }

    @Override
    public List<PackageInfo> findAllPackages() {
        Iterable<PackageInfo> packageInfos = packageRepository.findAll();
        if(!packageInfos.iterator().hasNext()){
            throw new NoContentException(Constants.PACKAGE_RESOURCE_NAME);
        }
        List<PackageInfo> result = new ArrayList<>();
        packageInfos.forEach(packageInfo -> {
            packageInfo.addLinks();
            result.add(packageInfo);
        });
        return result;
    }

    @Override
    public PackageInfo updatePackage(PackageInfo packageInfo) {
        Optional<PackageInfo> savedPackage = packageRepository.findById(packageInfo.getSeqId());
        if(!savedPackage.isPresent()){
            throw new PackageNotFoundException(packageInfo.getSeqId());
        }
        savedPackage.get().setLastModifiedDate(new Date());
        savedPackage.get().setActive(packageInfo.isActive());
        savedPackage.get().setDuration(packageInfo.getDuration());
        savedPackage.get().setDescription(packageInfo.getDescription());
        savedPackage.get().setPrice(packageInfo.getPrice());
        savedPackage.get().setName(packageInfo.getName());
        packageRepository.save(savedPackage.get());
        savedPackage.get().addLinks();
        return savedPackage.get();
    }

    @Override
    public boolean deletePackage(int id) {
        Optional<PackageInfo> savedPackage = packageRepository.findById(id);
        if(!savedPackage.isPresent()){
            throw new PackageNotFoundException(id);
        }
        packageRepository.delete(savedPackage.get());
        return true;
    }

    static void checkPackageContents(PackageInfo packageInfo){
        if(StringUtils.isBlank(packageInfo.getName())){
            throw new InvalidPayloadException(Constants.COLUMN_NAME);
        }
        if(packageInfo.getPrice() < 0 ){
            throw new InvalidPayloadException(Constants.COLUMN_PRICE);
        }
        if(packageInfo.getDuration() < 0){
            throw new InvalidPayloadException(Constants.COLUMN_DURATION);
        }
    }

    private void checkDuplicatePackage(String name){
        Optional<PackageInfo> packageInfo = packageRepository.findByName(name);
        if(packageInfo.isPresent()){
            throw new DuplicatePackageNameException();
        }
    }
}
