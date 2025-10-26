package com.iucosoft.mylinksspringboot.util;


import lombok.experimental.UtilityClass;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@UtilityClass
public class SortingUtil {

    public static Pageable applyDefaultSorting(Pageable pageable, Class<?> entityClass, String defaultSortField) {
        if (!pageable.getSort().isSorted()) {
            String fieldName = resolveSortField(entityClass, defaultSortField);
            return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(fieldName).ascending());
        }
        return pageable;
    }

    private static String resolveSortField(Class<?> entityClass, String defaultSortField) {
        if (isFieldPresent(entityClass, defaultSortField)) {
            return defaultSortField;
        }
        return AppConstants.Fields.ID;
    }

    private static boolean isFieldPresent(Class<?> entityClass, String fieldName) {
        try {
            entityClass.getDeclaredField(fieldName);
            return true;
        } catch (NoSuchFieldException e) {
            return false;
        }
    }
}
