package com.cui.ggkt.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author 崔令雨
 * {@code @date} 2022/7/10 17:50
 * {@code @Version} 1.0
 */
@Lazy(false)
@Component("SpringContextHolder")
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

    private static ApplicationContext applicationContext;
    private static final String ERROR_MESSAGE = "容器未注入";

    @Override
    public void destroy() throws Exception {
        applicationContext = null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> type) {
        return Optional.ofNullable(applicationContext)
                .orElseThrow(() -> new IllegalStateException(ERROR_MESSAGE)).getBean(type);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        return (T) Optional.ofNullable(applicationContext)
                .orElseThrow(() -> new IllegalStateException(ERROR_MESSAGE)).getBean(name);
    }
}