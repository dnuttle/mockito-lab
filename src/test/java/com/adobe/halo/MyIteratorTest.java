package com.adobe.halo;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;

/**
 * Example of mocking a constructor with PowerMockito
 * The constructor of MyIterator accepts a collection,
 * which we need to simulate.  We also need to use regular
 * Mockito to mock both methods in MyIterator.
 * @author dnuttle
 *
 */
public class MyIteratorTest {

  private int counter;
  private List<String> values;

  @Before
  public void setup() {
    counter = 0;
    values = Arrays.asList("one", "two", "three");
  }
  
  @SuppressWarnings("unchecked")
  @Test
  public void test() throws Throwable {
    MyIterator<String> myit = Mockito.mock(MyIterator.class, Answers.CALLS_REAL_METHODS.get());
    PowerMockito.whenNew(MyIterator.class).withArguments(Mockito.any(Collection.class)).thenReturn(myit);
    /* Alternate syntax for the two method mocks above;
     * you must use the other form if you are mocking a method 
     * that returns void.
     * In addition, this syntax will result in NPEs if you include
     * Answers.CALLS_REAL_METHODS.get() in the mock statement.
     * My advice therefore is to stick with the other syntax.
    Mockito.when(myit.hasNext()).then(new Answer<Object>() {
      @Override
      public Object answer(InvocationOnMock inv) {
        return counter < values.size();
      }
    });
    Mockito.when(myit.next()).then(new Answer<Object>() {
      @Override
      public Object answer(InvocationOnMock inv) {
        return values.get(counter++);
      }
    });
    */
    Mockito.doAnswer(new Answer<Object>() {
      @Override
      public Object answer(InvocationOnMock inv) {
        return counter < values.size();
      }
    }).when(myit).hasNext();
    Mockito.doAnswer(new Answer<Object>() {
      @Override
      public Object answer(InvocationOnMock inv) {
        return values.get(counter++);
      }
    }).when(myit).next();
    System.out.println(counter);
    Assert.assertEquals("one", myit.next());
    System.out.println(counter);
    Assert.assertEquals("two", myit.next());
    Assert.assertEquals("three", myit.next());
    Assert.assertFalse(myit.hasNext());
  }
}
