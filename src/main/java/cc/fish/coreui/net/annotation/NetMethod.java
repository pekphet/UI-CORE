package cc.fish.coreui.net.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import cc.fish.coreui.net.RequestHelper;

/**
 * Created by fish on 16-4-28.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NetMethod {
    RequestHelper.Method value();
}
