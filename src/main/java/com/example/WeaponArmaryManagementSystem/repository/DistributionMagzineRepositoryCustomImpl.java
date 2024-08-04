package com.example.WeaponArmaryManagementSystem.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DistributionMagzineRepositoryCustomImpl implements DistributionMagzineRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<String> findDistinctPoliceStationsWithWeaponName() {
        String jpql = "SELECT DISTINCT d.policeStationName " +
                "FROM DistributionMagzine d " +
                "WHERE d.weaponName IS NOT NULL";
        TypedQuery<String> query = entityManager.createQuery(jpql, String.class);
        return query.getResultList();
    }

    @Override
    public List<String> findMatchingPoliceStationNames() {
        String jpql = "SELECT psr.name " +
                "FROM PoliceStationRegistration psr " +
                "JOIN DistributionMagzine d ON psr.id = d.policeStationId " +
                "WHERE d.weaponName IS NOT NULL";
        TypedQuery<String> query = entityManager.createQuery(jpql, String.class);
        return query.getResultList();
    }

    @Override
    public List<String> findDistinctPoliceStationsWithMatchingNames() {
        String jpql = "SELECT DISTINCT psr.name " +
                "FROM DistributionMagzine dm " +
                "JOIN PoliceStationRegistration psr ON dm.policeStationId = psr.policeStationId " +
                "WHERE dm.weaponName IS NOT NULL";
        TypedQuery<String> query = entityManager.createQuery(jpql, String.class);
        return query.getResultList();
    }


}

