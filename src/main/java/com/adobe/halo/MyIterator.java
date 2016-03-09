package com.adobe.halo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class MyIterator<T> implements Iterator<T> {
  
  private List<T> list;
  private int index;
  
  public MyIterator(Collection<T> coll) {
    this.list = new ArrayList<>(coll);
  }
  
  @Override
  public boolean hasNext() {
    return index < list.size();
  }
  
  @Override
  public T next() {
    if (hasNext()) {
      return list.get(index++);
    }
    return null;
  }
  
}
