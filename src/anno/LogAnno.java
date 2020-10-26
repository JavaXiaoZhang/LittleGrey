package anno;

import java.lang.annotation.*;

/**
 * @author ZQ
 * @Date 2020/7/6
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface LogAnno {
    int value() default 0;
}
