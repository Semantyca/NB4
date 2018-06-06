package com.semantyca.nb.core.page;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
// @IndexAnnotated
public @interface Page {
    String value() default "default.xsl";
}
