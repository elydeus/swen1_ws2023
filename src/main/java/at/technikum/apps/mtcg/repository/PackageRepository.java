package at.technikum.apps.mtcg.repository;

import at.technikum.apps.mtcg.entity.Package;
import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.pbkdf2.Pack;

import java.util.Optional;
import java.util.Set;

public interface PackageRepository {
    Package save(Package pkg);

    Package update(Package oldPkg, Package newPkg);

    Optional<Package> find(String packageId);

    Set<Package> findAll();
}
