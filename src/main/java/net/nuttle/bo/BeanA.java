package net.nuttle.bo;

public class BeanA<T, U> {

  private T t;
  private U u;

  public BeanA(T t, U u) {
    this.t = t;
    this.u = u;
  }

  public BeanA() {
    
  }
  
  public void setT(T t) {
    this.t = t;
  }
  
  public T getT() {
    return t;
  }
  
  public void setU(U u) {
    this.u = u;
  }
  
  public U getU() {
    return u;
  }
}
