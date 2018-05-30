package com.ecommerce.ECommerce.Repository;

import com.ecommerce.ECommerce.Model.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserModel, String> {
    boolean existsByEmail(String email);

    Optional<UserModel> findByEmail(String email);

    Optional<UserModel> findByVerificationToken(String verificationToken);
}
