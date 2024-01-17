package at.technikum.apps.mtcg.service;

import at.technikum.apps.mtcg.entity.Card;
import at.technikum.apps.mtcg.repository.CardRepository;
import at.technikum.apps.mtcg.repository.DatabaseCardRepository;
import at.technikum.apps.mtcg.repository.DatabasePackageRepository;
import at.technikum.apps.mtcg.repository.PackageRepository;
import at.technikum.apps.mtcg.entity.Package;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class PackageService {
    private final PackageRepository packageRepository;

    public PackageService()  {
        this.packageRepository = new DatabasePackageRepository();
    }

    public void updateCoins(String username, int costs){
        packageRepository.updateCoins(username, costs);
    }
    public int getCoinsFromUser(String username){
        return packageRepository.getCoinsFromUser(username);
    }

    public String getIdFromUser(String username){
        return packageRepository.getIdFromUser(username);
    }

    public List<String> getCardsFromPackage(String package_id){
        return packageRepository.getCardsInPackage(package_id);
    }



    public String getIdFromPackage(){
        return packageRepository.getIdFromPackage();
    }
    public Set<Package> findAll() {
        return packageRepository.findAll();
    }

    public void delete(String package_id){
        packageRepository.delete(package_id);
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
