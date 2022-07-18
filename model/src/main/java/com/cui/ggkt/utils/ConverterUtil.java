package com.cui.ggkt.utils;


import lombok.experimental.UtilityClass;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * @author 崔令雨
 * {@code @date} 2022/7/10 17:48
 * {@code @Version} 1.0
 */
@UtilityClass
public class ConverterUtil {

    private static final ConversionService CONVERSION_SERVICE = SpringContextHolder.getBean(ConversionService.class);

    public static <T> T convert(@NonNull Object source, Class<T> targetType) {
        return CONVERSION_SERVICE.convert(source, targetType);
    }

    public static Object convert(@NonNull Object source,
                                 @Nullable TypeDescriptor sourceType,
                                 TypeDescriptor targetType) {
        return CONVERSION_SERVICE.convert(source, sourceType, targetType);
    }

    @SuppressWarnings("unchecked")
    public static <T, R> List<R> convertList(List<R> sourceList, Class<T> targetType) {

        if (CollectionUtils.isEmpty(sourceList)) {
            return new ArrayList<>();
        }
        return (List<R>) convert(sourceList, TypeDescriptor.collection(List.class,
                        TypeDescriptor.valueOf(sourceList.get(0).getClass())),
                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(targetType)));

    }


}