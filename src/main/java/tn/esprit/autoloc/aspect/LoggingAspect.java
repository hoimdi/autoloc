package tn.esprit.autoloc.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Before("execution(* tn.esprit.autoloc.service..*.*(..))")
    public void logBeforeService(JoinPoint joinPoint) {
        log.info("Appel de la methode : {} - Arguments : {}",
                joinPoint.getSignature().toShortString(),
                joinPoint.getArgs());
    }

    @AfterThrowing(pointcut = "execution(* tn.esprit.autoloc.service..*.*(..))", throwing = "ex")
    public void logExceptionService(JoinPoint joinPoint, Throwable ex) {
        log.error("Exception dans {} : {} - Cause : {}",
                joinPoint.getSignature().toShortString(),
                ex.getMessage(),
                ex.getCause() != null ? ex.getCause().getMessage() : "aucune");
    }

    @Before("execution(* tn.esprit.autoloc.web.controller..*.*(..))")
    public void logBeforeController(JoinPoint joinPoint) {
        log.debug("Requete HTTP sur endpoint : {}", joinPoint.getSignature().toShortString());
    }
}
