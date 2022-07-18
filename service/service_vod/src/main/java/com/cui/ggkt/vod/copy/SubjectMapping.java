package com.cui.ggkt.vod.copy;

import com.cui.ggkt.model.vod.Subject;
import com.cui.ggkt.vo.vod.SubjectEeVo;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * @author 崔令雨
 * {@code @date} 2022/7/10 17:40
 * {@code @Version} 1.0
 */
@Mapper(componentModel = "spring")
public interface SubjectMapping extends Converter<List<Subject>, List<SubjectEeVo>> {

    @Override
    List<SubjectEeVo> convert(@NonNull List<Subject> source);


    List<Subject> toSubject(List<SubjectEeVo> source);

}