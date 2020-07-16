package ru.itis.ivavprp.search.resumes;

import ru.itis.ivavprp.models.Resume;
import ru.itis.ivavprp.search.EntitySpecification;
import ru.itis.ivavprp.search.SpecSearchCriteria;

public class ResumeSpecification extends EntitySpecification<Resume> {

    public ResumeSpecification(SpecSearchCriteria criteria) {
        super(criteria);
    }
}
