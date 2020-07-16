package ru.itis.ivavprp.search.vacancies;

import ru.itis.ivavprp.models.Vacancy;
import ru.itis.ivavprp.search.EntitySpecification;
import ru.itis.ivavprp.search.SpecSearchCriteria;

public class VacancySpecification extends EntitySpecification<Vacancy> {

    public VacancySpecification(SpecSearchCriteria criteria) {
        super(criteria);
    }
}