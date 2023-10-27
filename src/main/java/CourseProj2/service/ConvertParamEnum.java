package CourseProj2.service;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ConvertParamEnum implements Converter<EExam, String> {
    @Override
    public String convert(EExam eExam){
        return eExam.toString();
    }
}
