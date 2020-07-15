package ru.itis.ivavprp.search.vacancies;

import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;
import ru.itis.ivavprp.models.Vacancy;
import ru.itis.ivavprp.search.SearchOperation;
import ru.itis.ivavprp.search.SpecSearchCriteria;

import java.util.ArrayList;
import java.util.List;

@Getter
public class VacancySpecificationBuilder {

    private final List<SpecSearchCriteria> params;

    public VacancySpecificationBuilder() {
        params = new ArrayList<>();
    }

    public final VacancySpecificationBuilder with(final String key, final String operation, final Object value, final String prefix, final String suffix) {
        return with(null, key, operation, value, prefix, suffix);
    }

    public final VacancySpecificationBuilder with(final String orPredicate, final String key, final String operation, final Object value, final String prefix, final String suffix) {
        SearchOperation op = SearchOperation.getSimpleOperation(operation.charAt(0));
        if (op != null) {
            if (op == SearchOperation.EQUALITY) {
                final boolean startWithAsterisk = prefix != null && prefix.contains(SearchOperation.ZERO_OR_MORE_REGEX);
                final boolean endWithAsterisk = suffix != null && suffix.contains(SearchOperation.ZERO_OR_MORE_REGEX);

                if (startWithAsterisk && endWithAsterisk) {
                    op = SearchOperation.CONTAINS;
                } else if (startWithAsterisk) {
                    op = SearchOperation.ENDS_WITH;
                } else if (endWithAsterisk) {
                    op = SearchOperation.STARTS_WITH;
                }
            }
            params.add(new SpecSearchCriteria(orPredicate, key, op, value));
        }
        return this;
    }

    public Specification<Vacancy> build() {
        if (params.size() == 0)
            return null;

        Specification<Vacancy> result = new VacancySpecification(params.get(0));

        for (int i = 1; i < params.size(); i++) {
            result = params.get(i).isOrPredicate()
                    ? Specification.where(result).or(new VacancySpecification(params.get(i)))
                    : Specification.where(result).and(new VacancySpecification(params.get(i)));
        }

        return result;
    }
}
