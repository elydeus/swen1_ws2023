package at.technikum.apps.mtcg.repository;

import at.technikum.apps.mtcg.entity.Package;
import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.pbkdf2.Pack;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PackageRepository {
    Package save(Package pkg);

    Package update(Package oldPkg, Package newPkg);

    void updateCoins(String username, int costs);

    int getCoinsFromUser(String username);

    String getIdFromUser(String username);

    String getIdFromPackage();

    List<String> getCardsInPackage(String package_id);

    void delete(String package_id);

    Optional<Package> find(String packageId);

    Set<Package> findAll();
}
