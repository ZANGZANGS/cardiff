package cardiff.domain.repository;

import cardiff.domain.entity.Admin;

import java.util.Optional;

public interface AdminLoginRepository {

    Long save(Admin admin);

    Optional<Admin> findOne(String name, String password);

    Optional<Admin> findByName(String name);
}
