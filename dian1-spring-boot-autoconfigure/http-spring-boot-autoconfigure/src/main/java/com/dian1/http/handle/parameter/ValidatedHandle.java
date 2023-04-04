package com.dian1.http.handle.parameter;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.dian1.http.annotate.parameter.HttpValidated;
import com.dian1.http.handle.ParameterHandle;
import com.dian1.http.properties.HttpProperties;
import com.dian1.http.utils.ClassUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.util.*;

/**
 * 校验抽象类
 *
 * @author zhangzhi
 * @date 2023/4/4
 */
@Data
@Component
public class ValidatedHandle<T> implements ParameterHandle<HttpValidated> {


    @Autowired
    private Validator validator;


    @Override
    public HttpProperties resolving(HttpProperties properties, Object arg, HttpValidated basicAuth) {
        if (StrUtil.isNotBlank(basicAuth.handleClassName())) {
            ParameterHandle bean = ClassUtils.getBean(basicAuth.handleClassName(), ParameterHandle.class);
            return bean.resolving(properties, arg, basicAuth);
        }
        //集合类
        if (Collection.class.isAssignableFrom(arg.getClass())) {
            Collection collection = (Collection) arg;
            if (!collection.isEmpty()) {
                Iterator iterator = collection.iterator();
                if (convert(collection.iterator().next())) {
                    List<Set<ConstraintViolation<T>>> linkedList = new LinkedList<>();
                    while (iterator.hasNext()) {
                        Set<ConstraintViolation<T>> validate = validator.validate((T) arg, basicAuth.groups());
                        linkedList.add(validate);
                    }
                    validateArg(linkedList);
                }
            }
        } else {
            //普通对象
            if (convert(arg)) {
                List<Set<ConstraintViolation<T>>> linkedList = new LinkedList<>();
                Set<ConstraintViolation<T>> validate = validator.validate((T) arg, basicAuth.groups());
                linkedList.add(validate);
                validateArg(linkedList);
            }
        }
        return properties;
    }

    public void validateArg(List<Set<ConstraintViolation<T>>> validate) {
        if (ObjectUtil.isEmpty(validate)) {
            return;
        }
        boolean isErr = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < validate.size(); i++) {
            Set<ConstraintViolation<T>> constraintViolations = validate.get(i);
            for (ConstraintViolation<T> next : constraintViolations) {
                sb.append(i).append(" : ").append(next.getPropertyPath()).append("[").append(next.getInvalidValue()).append("]").append("\t").append(next.getMessage()).append("\n");
                isErr = true;
            }
        }
        if (isErr) {
            throw new ValidationException(sb.toString());
        }
    }

    @Override
    public int order() {
        return 3;
    }

    public boolean convert(Object arg) {
        try {
            T arg1 = (T) arg;
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
