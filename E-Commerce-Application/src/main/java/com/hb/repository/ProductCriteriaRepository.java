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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.hb.models.Product;
import com.hb.models.ProductPage;
import com.hb.models.ProductSearchCritaria;

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
		return new PageImpl<>(typeQuery.getResultList(),pageable,productCount);
	}
	private long getProductCount(Predicate predicate) {
		// TODO Auto-generated method stub
		CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
		Root<Product> countRoot = countQuery.from(Product.class);
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
		
	        
		}
		return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	}
	
	
}
