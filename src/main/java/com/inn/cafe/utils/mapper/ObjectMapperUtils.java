package com.inn.cafe.utils.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;

import com.inn.cafe.user.data.User;
import com.inn.cafe.user.dto.query.UserResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ObjectMapperUtils {

  private static final ModelMapper modelMapper;

  static {
    modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);

 
  }

  private ObjectMapperUtils() {}


  public static <D, T> D map(final T entity, Class<D> outClass) {
    return modelMapper.map(entity, outClass);
  }

  public static <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outCLass) {
    return entityList.stream()
                     .map(entity -> map(entity, outCLass))
                     .collect(Collectors.toList());
  }

  public static <S, D> D map(final S source, D destination) {
    modelMapper.map(source, destination);
    return destination;
  }
}
