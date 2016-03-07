package net.nuttle.service;

import net.nuttle.bo.BeanA;

public interface AService<T, U> {
  static <T,U> void service1(BeanA<T, U> bean, U newValue) {
    System.out.println("service1 call: " + newValue + " " + (newValue==null));
    if (bean != null) {
      bean.setU(newValue);
    }
  }
}
