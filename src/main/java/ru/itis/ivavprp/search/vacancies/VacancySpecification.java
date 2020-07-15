package ru.itis.ivavprp.search.vacancies;

import org.springframework.data.jpa.domain.Specification;
import ru.itis.ivavprp.models.Vacancy;
import ru.itis.ivavprp.search.SpecSearchCriteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class VacancySpecification implements Specification<Vacancy> {
    private SpecSearchCriteria criteria;

    public VacancySpecification(final SpecSearchCriteria criteria) {
        super();
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(final Root<Vacancy> root, final CriteriaQuery<?> query, final CriteriaBuilder builder) {
        switch (criteria.getOperation()) {
            case EQUALITY:
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            case NEGATION:
                return builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
            case GREATER_THAN:
                return builder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString());
            case LESS_THAN:
                return builder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString());
            case LIKE:
            case CONTAINS:
                return builder.like(root.get(criteria.getKey()),"%" + criteria.getValue() + "%");
            case STARTS_WITH:
                return builder.like(root.get(criteria.getKey()), criteria.getValue() + "%");
            case ENDS_WITH:
                return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue());
            default:
                return null;
        }
    }
}
