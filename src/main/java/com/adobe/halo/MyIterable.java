package com.adobe.halo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyIterable<T> implements Iterable<T> {

  private List<T> list = new ArrayList<>();
  
  public MyIterable() {}
  
  public void add(T elem) {
    list.add(elem);
  }
  
  @Override
  public Iterator<T> iterator() {
    return list.iterator();
  }
}
