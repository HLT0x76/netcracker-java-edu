package com.netcracker.edu.injections.annotations;

import com.netcracker.edu.injections.Injector;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation, which {@link Injector} uses
 * for a field injection (see also {@link com.netcracker.edu.injections.utils.InjectionUtils}).
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CustomInjection {
}
