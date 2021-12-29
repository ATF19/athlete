package com.atef.athlete.infrastructure.data.repository.user;

import com.atef.athlete.infrastructure.data.model.user.RegisteredUserDataModel;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserJpaRepository extends PagingAndSortingRepository<RegisteredUserDataModel, UUID> {
    Optional<RegisteredUserDataModel> findByUsernameOrEmail(String username, String email);
}
