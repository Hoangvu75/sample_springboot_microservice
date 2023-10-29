package com.cnweb.api.utils;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Expression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import jakarta.persistence.PersistenceContext;

public abstract class QuerydslCustomRepositorySupport extends QuerydslRepositorySupport {

    private JPAQueryFactory queryFactory;

    public QuerydslCustomRepositorySupport(Class<?> domainClass) {
        super(domainClass);
    }

    @PersistenceContext
    @Override
    public void setEntityManager(@NotNull EntityManager entityManager) {
        super.setEntityManager(entityManager);
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    protected <T> JPAQuery<T> select(Expression<T> expr) {
        return queryFactory.select(expr);
    }

    protected <T> JPAQuery<T> selectFrom(EntityPath<T> from) {
        return queryFactory.selectFrom(from);
    }
}
