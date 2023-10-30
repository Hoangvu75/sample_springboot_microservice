package com.cnweb.api.repositories;


import com.cnweb.api.entities.Account;
import com.cnweb.api.entities.QAccount;
import com.cnweb.api.utils.QuerydslCustomRepositorySupport;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID>, AccountDslRepository {
}

interface AccountDslRepository {
    Optional<Account> findByEmail(String email);
}

class AccountRepositoryImpl extends QuerydslCustomRepositorySupport implements AccountDslRepository {

    public AccountRepositoryImpl(EntityManager entityManager) {
        super(Account.class);
        setEntityManager(entityManager);
    }

    @Override
    public Optional<Account> findByEmail(String email) {
        JPAQuery<Account> query = select(QAccount.account)
                .from(QAccount.account)
                .where(QAccount.account.email.eq(email));
        return Optional.ofNullable(query.fetchOne());
    }
}