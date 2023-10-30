package com.cnweb.api.repositories;

import com.cnweb.api.models.Profile;
import com.cnweb.api.models.QProfile;
import com.cnweb.api.utils.QuerydslCustomRepositorySupport;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProfileRepository extends JpaRepository<Profile, UUID>, ProfileDslRepository {
}

interface ProfileDslRepository {
    Optional<Profile> findByAccountId(String accountId);
}

class ProfileRepositoryImpl extends QuerydslCustomRepositorySupport implements ProfileDslRepository {
    public ProfileRepositoryImpl(EntityManager entityManager) {
        super(Profile.class);
        setEntityManager(entityManager);
    }

    @Override
    public Optional<Profile> findByAccountId(String accountId) {
        JPAQuery<Profile> query = select(QProfile.profile)
                .from(QProfile.profile)
                .where(QProfile.profile.accountId.eq(accountId));
        return Optional.ofNullable(query.fetchOne());
    }
}