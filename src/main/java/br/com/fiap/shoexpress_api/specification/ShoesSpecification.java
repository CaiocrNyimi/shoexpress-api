package br.com.fiap.shoexpress_api.specification;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import br.com.fiap.shoexpress_api.model.Shoes;
import br.com.fiap.shoexpress_api.model.ShoesFilter;

public class ShoesSpecification {

    public static Specification<Shoes> withFilters(ShoesFilter filter) {
        return (root, query, cb) -> {
            List<jakarta.persistence.criteria.Predicate> predicates = new ArrayList<>();

            // Filtro de nome
            if (filter.name() != null) {
                predicates.add(cb.like(cb.lower(
                    root.get("name")), "%" + filter.name().toLowerCase() + "%"));
            }

            // Filtro de marca
            if (filter.brand() != null) {
                predicates.add(cb.like(cb.lower(
                    root.get("brand")), "%" + filter.brand().toLowerCase() + "%"));
            }

            // Filtro de categoria
            if (filter.category() != null) {
                predicates.add(cb.like(cb.lower(
                    root.get("category")), "%" + filter.category().toLowerCase() + "%"));
            }

            // Filtro de preço (min e max)
            if (filter.minPrice() != null && filter.maxPrice() != null) {
                predicates.add(
                    cb.between(root.get("price"), filter.minPrice(), filter.maxPrice()));
            }

            // Filtro de preço (apenas min)
            if (filter.minPrice() != null && filter.maxPrice() == null) {
                predicates.add(
                    cb.greaterThanOrEqualTo(root.get("price"), filter.minPrice()));
            }

            // Filtro de preço (apenas max)
            if (filter.minPrice() == null && filter.maxPrice() != null) {
                predicates.add(
                    cb.lessThanOrEqualTo(root.get("price"), filter.maxPrice()));
            }

            var arrayPredicates = predicates.toArray(new Predicate[0]);
            return cb.and(arrayPredicates);
        };
    }
    
}
