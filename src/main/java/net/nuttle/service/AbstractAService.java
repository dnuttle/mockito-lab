package net.nuttle.service;

import net.nuttle.bo.BeanA;

import org.apache.log4j.Logger;

public abstract class AbstractAService implements AService {

  public static final Logger LOG = Logger.getLogger(AbstractAService.class);
  
  @Override
  public abstract <T, U> void service2(BeanA<T, U> bean, U value);
  
  public static <T, U> void service3(BeanA<T, U> bean, U value) {
    LOG.debug("service3 default behavior");
  }
}
