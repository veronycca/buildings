package com.veronika.buildings.daos;

import com.veronika.buildings.model.AddressEntity;
import com.veronika.buildings.model.AddressEntity_;
import com.veronika.buildings.model.BuildingEntity;
import com.veronika.buildings.model.BuildingEntity_;
import com.veronika.buildings.model.PropertyType;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class BuildingDao extends SimpleJpaRepository<BuildingEntity, Long> {

    private final EntityManager entityManager;

    public BuildingDao(EntityManager entityManager) {
        super(BuildingEntity.class, entityManager);
        this.entityManager = entityManager;
    }

    public List<BuildingEntity> getBuildingsByOwner(String owner) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BuildingEntity> cr = cb.createQuery(BuildingEntity.class);
        Root<BuildingEntity> root = cr.from(BuildingEntity.class);
        cr.select(root);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get(BuildingEntity_.owner), owner));

        cr.where(cb.and(predicates.toArray(new Predicate[0])));

        return entityManager.createQuery(cr).getResultList();
    }

    public List<BuildingEntity> getSimilarBuildings(String city, String street, PropertyType propertyType,
            int size) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BuildingEntity> cr = cb.createQuery(BuildingEntity.class);
        Root<BuildingEntity> root = cr.from(BuildingEntity.class);

        Join<BuildingEntity, AddressEntity> item = root.join(BuildingEntity_.address, JoinType.LEFT);

        cr.select(root);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(item.get(AddressEntity_.city), city));
        predicates.add(cb.equal(item.get(AddressEntity_.street), street));
        predicates.add(cb.equal(root.get(BuildingEntity_.type), propertyType));

        cr.where(cb.and(predicates.toArray(new Predicate[0])));

        List<Order> orderList = new ArrayList();
        orderList.add(cb.asc(cb.abs(cb.diff(root.get(BuildingEntity_.size), size))));
        orderList.add(cb.desc(root.get(BuildingEntity_.value)));

        cr.orderBy(orderList);

        return entityManager.createQuery(cr).setMaxResults(3).getResultList();
    }

}