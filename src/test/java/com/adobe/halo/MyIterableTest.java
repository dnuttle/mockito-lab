package com.adobe.halo;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class MyIterableTest {

  private int counter;
  
  @SuppressWarnings("unchecked")
  @Test
  public void testCannedIterator() {
    MyIterable<String> myit = Mockito.mock(MyIterable.class, Answers.CALLS_REAL_METHODS.get());
    //Need to mock this to do nothing, because otherwise there will be NPE in
    //call to add(), because internal List is not initialized.
    Mockito.doNothing().when(myit).add(Mockito.anyString());
    Mockito.doAnswer(new Answer<Object>() {
      @Override
      public Object answer(InvocationOnMock inv) {
        String[] vals = {"a","b","c"};
        return Arrays.asList(vals).iterator();
      }
    }).when(myit).iterator();
    Iterator<String> it = myit.iterator();
    Assert.assertEquals("a", it.next());
    Assert.assertEquals("b", it.next());
    Assert.assertEquals("c", it.next());
    Assert.assertFalse(it.hasNext());
  }
  
  @SuppressWarnings("unchecked")
  @Test
  public void testMockedAdd() {
    Stack<String> vals = new Stack<>();
    MyIterable<String> myit = Mockito.mock(MyIterable.class);
    Mockito.doAnswer(new Answer<Object>() {
      @Override
      public Object answer(InvocationOnMock inv) {
        String value = inv.getArgumentAt(0, String.class);
        vals.push(value + "-test");
        return null;
      }
    }).when(myit).add(Mockito.anyString());
    Mockito.doAnswer(new Answer<Object>() {
      @Override
      public Object answer(InvocationOnMock inv) {
        return vals.iterator();
      }
    }).when(myit).iterator();
    myit.add("one");
    myit.add("two");
    myit.add("three");
    Iterator<String> it = myit.iterator();
    Assert.assertEquals("one-test", it.next());
    Assert.assertEquals("two-test", it.next());
    Assert.assertEquals("three-test", it.next());
    Assert.assertFalse(it.hasNext());
  }
}
