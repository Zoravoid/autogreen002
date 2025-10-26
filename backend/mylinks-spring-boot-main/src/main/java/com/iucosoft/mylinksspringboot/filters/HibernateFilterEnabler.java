package com.iucosoft.mylinksspringboot.filters;

import com.iucosoft.mylinksspringboot.context.UserContext;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.Session;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Aspect
@Order(1)
@Component
@Log4j2
public class HibernateFilterEnabler {

    @Pointcut("execution(public * javax.persistence.EntityManager+.*Query(..))")
    private void createQuery() {
        //intended to be empty
    }

    @Around(value = "createQuery() && target(entityManager)")
    public Object applyFilter(ProceedingJoinPoint pjp, Object entityManager) throws Throwable {
        Long userId = UserContext.getUserId();
        String authorities = UserContext.getAuthorities();
        if (userId != null) {
            // Check if authorities do not contain "ROLE_ADMIN"
            if (authorities != null && !authorities.contains("ADMIN")) {
                // Set the userId in the Hibernate filter
                ((EntityManager) entityManager).unwrap(Session.class).enableFilter("ownerFilter").setParameter("ownerIdParam", userId);

            }
        } else {
            try {
                ((EntityManager) entityManager).unwrap(Session.class).disableFilter("ownerFilter");
            } catch (Exception e) {
                log.warn("Unable to enable filter");
            }
        }

        return pjp.proceed();
    }

}

