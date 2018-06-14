package com.semantyca.nb.core.dataengine.jpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface IFilter<T> {
    Predicate collectPredicate(Root<T> root, CriteriaBuilder cb, Predicate condition);
}
