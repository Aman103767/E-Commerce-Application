package com.hb.repository;

import java.util.ArrayList; 
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.hb.models.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
public class ProductCriteriaRepository {

	private final EntityManager entityManager;
	private final CriteriaBuilder criteriaBuilder;
	public ProductCriteriaRepository(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
		this.criteriaBuilder= entityManager.getCriteriaBuilder();
		
	}
	public Page<Product> findAllWithFilters(ProductPage productPage,
			ProductSearchCritaria productSearchCritaria){
     
		CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
		Root<Product> productRoot = criteriaQuery.from(Product.class);
		Predicate predicate = getPredicate(productSearchCritaria,productRoot);
		criteriaQuery.where(predicate);
		setOrder(productPage,criteriaQuery,productRoot);
		TypedQuery<Product> typeQuery = entityManager.createQuery(criteriaQuery);
		typeQuery.setFirstResult(productPage.getPageNumber()*productPage.getPageSize());
		typeQuery.setMaxResults(productPage.getPageSize());
		
		Pageable pageable = getPageable(productPage);
		long productCount = getProductCount(predicate);
		if(productSearchCritaria.getSearch()!= null && productSearchCritaria.getSearch()){
			return new PageImpl<>(typeQuery.setMaxResults(3).getResultList(),pageable,productCount);
		}else{
			return new PageImpl<>(typeQuery.getResultList(),pageable,productCount);
		}
	}
	private long getProductCount(Predicate predicate) {
		// TODO Auto-generated method stub
		CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
		Root<Product> countRoot = countQuery.from(Product.class);
		countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
		return entityManager.createQuery(countQuery).getSingleResult();
	}
	private long getOrderCount(Predicate predicate) {
		// TODO Auto-generated method stub
		CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
		Root<Orders> countRoot = countQuery.from(Orders.class);
		countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
		return entityManager.createQuery(countQuery).getSingleResult();
	}
	private Pageable getPageable(ProductPage productPage) {
		// TODO Auto-generated method stub
		Sort sort = Sort.by(productPage.getSortDirection(),productPage.getSortBy());
		return PageRequest.of(productPage.getPageNumber(),productPage.getPageSize(),sort );
	
	}
	private void setOrder(ProductPage productPage, CriteriaQuery<Product> criteriaQuery, Root<Product> productRoot) {
		// TODO Auto-generated method stub
		if(productPage.getSortDirection().equals(Sort.Direction.ASC)) {
			criteriaQuery.orderBy(criteriaBuilder.asc(productRoot.get(productPage.getSortBy())));
		}else {
			criteriaQuery.orderBy(criteriaBuilder.desc(productRoot.get(productPage.getSortBy())));
		}
	}
	private Predicate getPredicate(ProductSearchCritaria productSearchCritaria, 
			Root<Product> productRoot) {
		// TODO Auto-generated method stub
		List<Predicate> predicates = new ArrayList<>();
		if(Objects.nonNull(productSearchCritaria.getProduct()) &&
				Objects.nonNull(productSearchCritaria.getProduct().getProductName())) {
			predicates.add(criteriaBuilder.or(
                 criteriaBuilder.like(criteriaBuilder.upper(productRoot.get("productName")), "%"+productSearchCritaria.getProduct().getProductName()+"%"),
                 criteriaBuilder.like(criteriaBuilder.upper(productRoot.get("manufacturer")),"%"+productSearchCritaria.getProduct().getManufacturer()+"%"),
                 criteriaBuilder.like(criteriaBuilder.upper(productRoot.get("specification")),"%"+productSearchCritaria.getProduct().getSpecification()+"%")
                 
                ));
//			predicates.add(
//			criteriaBuilder.like(productRoot.get("productName"),"%"+productSearchCritaria.getProduct().getProductName()+"%")
		    // Filter by price range (minPrice and maxPrice)
		}
		if (Objects.nonNull(productSearchCritaria.getMinPrice()) && Objects.nonNull(productSearchCritaria.getMaxPrice())) {
			Double minPrice = productSearchCritaria.getMinPrice();
			Double maxPrice = productSearchCritaria.getMaxPrice();
			predicates.add(criteriaBuilder.between(productRoot.get("discountedPrice"), minPrice, maxPrice));
		}
		return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	}
	private Predicate getPredicateOrders(OrderSearchCritaria orderSearchCritaria,
										 Root<Orders> ordersRoot){
		List<Predicate> predicates = new ArrayList<>();
//		if(Objects.nonNull(productSearchCritaria.getSearchName())){
//			predicates.add(criteriaBuilder.or(
//
//					criteriaBuilder.like(criteriaBuilder.upper(ordersRoot.get("products.")), "%"+productSearchCritaria.getSearchName()+"%")
//					));
//		}
		return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	}
	private void setOrderForOrders(ProductPage orderPage, CriteriaQuery<Orders> criteriaQuery, Root<Orders> orderRoot){
		if(orderPage.getSortDirection().equals(Sort.Direction.ASC)) {
			criteriaQuery.orderBy(criteriaBuilder.asc(orderRoot.get(orderPage.getSortBy())));
		}else {
			criteriaQuery.orderBy(criteriaBuilder.desc(orderRoot.get(orderPage.getSortBy())));
		}
	}

	public Page<Orders> findAllOrderWithFilter(ProductPage orderPage, OrderSearchCritaria orderSearchCritaria){
		CriteriaQuery<Orders> criteriaQuery = criteriaBuilder.createQuery(Orders.class);
		Root<Orders> orderRoot = criteriaQuery.from(Orders.class);
		Predicate predicate = getPredicateOrders(orderSearchCritaria,orderRoot);
		criteriaQuery.where(predicate);
		setOrderForOrders(orderPage,criteriaQuery,orderRoot);
		TypedQuery<Orders> typeQuery = entityManager.createQuery(criteriaQuery);
		typeQuery.setFirstResult(orderPage.getPageNumber()*orderPage.getPageSize());
		typeQuery.setMaxResults(orderPage.getPageSize());
		Pageable pageable = getPageable(orderPage);
		long orderCount = getOrderCount(predicate);
		return new PageImpl<>(typeQuery.getResultList(),pageable,orderCount);
	}
}
