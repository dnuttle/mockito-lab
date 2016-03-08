package net.nuttle.service;

import org.apache.log4j.Logger;

import net.nuttle.bo.BeanA;

public class AServiceImpl extends AbstractAService implements AService {

  private static final Logger LOG = Logger.getLogger(AServiceImpl.class);
  
  @Override
  public <T, U> void service2(BeanA<T, U> bean, U u) {
    LOG.debug("default implementation");
    bean.setU(u);
  }
  
  @Override
  public <T, U> void service3(BeanA<T, U> bean, U u) {
    LOG.debug("default implementation");
    bean.setU(u);
  }
  
}
