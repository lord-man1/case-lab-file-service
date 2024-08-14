package ru.caselab.impl.service.converter;

import lombok.SneakyThrows;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.caselab.vo.exception.NoSuchSortingMethodException;

@Service
public class SortTypeConverter implements Converter<String, Sort.Direction> {
    @SneakyThrows
    @Override
    public Sort.Direction convert(String sortType) {
        return Sort.Direction.fromOptionalString(sortType)
                .orElseThrow(() -> NoSuchSortingMethodException.createByMethod(sortType));
    }
}
