package ru.itis.ivavprp.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.ivavprp.dto.EmploymentType;
import ru.itis.ivavprp.dto.WorkSchedule;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpecSearchCriteria {

    private String key;
    private SearchOperation operation;
    private Object value;
    private boolean orPredicate;

    public SpecSearchCriteria(final String orPredicate, final String key, final SearchOperation operation, final Object value) {
        super();
        this.value = changeUnderscoresToWhiteSpaces(key, value);
        this.value = changeTypes(key, this.value);
        this.orPredicate = orPredicate != null && orPredicate.equals(SearchOperation.OR_PREDICATE_FLAG);
        this.key = key;
        this.operation = operation;
    }

    private Object changeTypes(final String key, Object value) {
        switch (key) {
            case "workSchedule": {
                return WorkSchedule.valueOf((String) value);
            }
            case "employmentType": {
                return EmploymentType.valueOf((String) value);
            }
            default: {
                return value;
            }
        }
    }

    private Object changeUnderscoresToWhiteSpaces(String key, Object value) {
        if (key.equals("name")) {
            String val = (String) value;
            return val.replaceAll("_", " ");
        }
        return value;
    }
}