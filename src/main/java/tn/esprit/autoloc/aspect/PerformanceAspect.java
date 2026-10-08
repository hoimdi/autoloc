package tn.esprit.autoloc.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class PerformanceAspect {

    @Around("execution(* tn.esprit.autoloc.service..*.*(..)) || execution(* tn.esprit.autoloc.repository..*.*(..))")
    public Object mesurerPerformance(ProceedingJoinPoint pjp) throws Throwable {
        long debut = System.currentTimeMillis();
        try {
            return pjp.proceed();
        } finally {
            long duree = System.currentTimeMillis() - debut;
            if (duree > 500) {
                log.warn("PERFORMANCE - {} s'est execute en {} ms (lent)",
                        pjp.getSignature().toShortString(), duree);
            } else {
                log.debug("PERFORMANCE - {} s'est execute en {} ms",
                        pjp.getSignature().toShortString(), duree);
            }
        }
    }
}
