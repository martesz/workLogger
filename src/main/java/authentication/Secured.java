package authentication;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.ws.rs.NameBinding;

/**
 *
 * @author martin
 * 
 *         Custom name binding annotation for filters and interceptors The
 *         defined name-binding annotation @Secured will be used to decorate a
 *         filter class
 */
@NameBinding
@Retention(RUNTIME)
@Target({ TYPE, METHOD })
public @interface Secured {
}
