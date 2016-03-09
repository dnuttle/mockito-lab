package com.adobe.halo;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class SimpleBeanTest {

  @Test
  public void test() {
    SimpleBean testBean = new SimpleBean();
    SimpleBean mockBean = Mockito.mock(SimpleBean.class, Answers.CALLS_REAL_METHODS.get());

    Mockito.doAnswer(new Answer<Object>() {
      @Override
      public Object answer(InvocationOnMock inv) {
        testBean.setKey(inv.getArgumentAt(0, String.class));
        return null;
      }
    }).when(mockBean).setKey(Mockito.anyString());
    
    Mockito.doAnswer(new Answer<Object>() {
      @Override
      public Object answer(InvocationOnMock inv) {
        return testBean.getKey();
      }
    }).when(mockBean).getKey();
    
    Mockito.doAnswer(new Answer<Object>() {
      @Override
      public Object answer(InvocationOnMock inv) {
        testBean.setValue(inv.getArgumentAt(0, String.class));
        return null;
      }
    }).when(mockBean).setValue(Mockito.anyString());
    
    Mockito.doAnswer(new Answer<Object>() {
      @Override
      public Object answer(InvocationOnMock inv) {
        return testBean.getValue();
      }
    }).when(mockBean).getValue();
    
    Mockito.doAnswer(new Answer<Object>() {
      @Override
      public Object answer(InvocationOnMock inv) {
        testBean.setValue("override value");
        return null;
      }
    }).when(mockBean).setValue("specific value");

    mockBean.setKey("abc");
    mockBean.setValue("def");
    Assert.assertEquals(testBean.getKey(), mockBean.getKey());
    Assert.assertEquals(testBean.getValue(), mockBean.getValue());
    mockBean.setValue("specific value");
    Assert.assertEquals("override value", mockBean.getValue());
  }
  
  @Test(expected=NullPointerException.class)
  public void testThrowException() {
    SimpleBean mockBean = Mockito.mock(SimpleBean.class);
    Mockito.doThrow(new NullPointerException("Error!")).when(mockBean).setValue(null);
    mockBean.setValue(null);
  }
  
}
