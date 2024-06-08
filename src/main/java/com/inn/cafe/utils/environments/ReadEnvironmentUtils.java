package com.inn.cafe.utils.environments;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ReadEnvironmentUtils {
  private static Environment environment;

  @Autowired
  public ReadEnvironmentUtils(Environment environment) {
    ReadEnvironmentUtils.environment = environment;
  }

  public static String readProperty(String propertyName) {
    return environment.getProperty(propertyName);
  }


}
