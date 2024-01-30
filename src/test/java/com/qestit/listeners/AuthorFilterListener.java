package com.qestit.listeners;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import com.qestit.annotations.FrameworkAnnotation;
import com.qestit.enums.AuthorType;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AuthorFilterListener implements IMethodInterceptor {

    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {
        List<IMethodInstance> result = new ArrayList<>();

        for (IMethodInstance method : methods) {
            Method testMethod = method.getMethod().getConstructorOrMethod().getMethod();
            if (testMethod.isAnnotationPresent(FrameworkAnnotation.class)) {
                FrameworkAnnotation annotation = testMethod.getAnnotation(FrameworkAnnotation.class);
                if (Arrays.asList(annotation.author()).contains(AuthorType.Hany)) {
                    result.add(method);
                }
            }
        }
        return result;
    }
}

