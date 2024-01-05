package at.technikum.apps.mtcg.service;

import at.technikum.apps.mtcg.entity.Card;
import at.technikum.apps.mtcg.repository.CardRepository;
import at.technikum.apps.mtcg.repository.DatabaseCardRepository;
import at.technikum.apps.mtcg.repository.DatabasePackageRepository;
import at.technikum.apps.mtcg.repository.PackageRepository;
import at.technikum.apps.mtcg.entity.Package;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class PackageService {
    private final PackageRepository packageRepository;

    public PackageService() {
        this.packageRepository = new DatabasePackageRepository();
    }

    public Set<Package> findAll() {
        return packageRepository.findAll();
    }

    public Optional<Package> find(String id) {
        return packageRepository.find(id);
    }

    public Package update(Package oldPkg, Package newPkg){
        return packageRepository.update(oldPkg,newPkg);
    }

    public Package save(Package pkg) {
        pkg.setId(UUID.randomUUID().toString());
        return packageRepository.save(pkg);
    }
}
