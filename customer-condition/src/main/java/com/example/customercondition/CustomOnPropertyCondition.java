package com.example.customercondition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

/**
 * @author: zhengsl26931
 * @create: 2020-04-24
 */
public class CustomOnPropertyCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        Map<String, Object> annotationAttributes = annotatedTypeMetadata.getAnnotationAttributes(CustomConditionalOnProperty.class.getName());
        String propertyName = (String) annotationAttributes.get("name");
        String[] values = (String[]) annotationAttributes.get("havingValue");
        if (0 == values.length) {
            return false;
        }
        String propertyValue = conditionContext.getEnvironment().getProperty(propertyName);
        // 有一个匹配上就ok
        for (String havingValue : values) {
            if (propertyValue.equalsIgnoreCase(havingValue)) {
                return true;
            }
        }
        return false;
    }
}