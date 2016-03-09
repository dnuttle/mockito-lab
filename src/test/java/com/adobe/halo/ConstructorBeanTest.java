package com.adobe.halo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ConstructorBean.class})
public class ConstructorBeanTest {

  String testValue;
  
  /**
   * Mock getValue, ignore constructor
   */
  @Test
  public void testMockGetValue() {
    String value = "abc";
    ConstructorBean mockBean = Mockito.mock(ConstructorBean.class, Answers.CALLS_REAL_METHODS.get());
    Mockito.doAnswer(new Answer<Object>() {
      @Override
      public Object answer(InvocationOnMock inv) {
        return value;
      }
    }).when(mockBean).getValue();
    Assert.assertEquals(value, mockBean.getValue());
  }
  
  /**
   * Mock constructor
   */
  @Test
  public void testMockConstructor() throws Exception {
    String value = "one";
    ConstructorBean mockBean = Mockito.mock(ConstructorBean.class);
    PowerMockito.whenNew(ConstructorBean.class).withArguments(value).thenReturn(mockBean);
    Mockito.doAnswer(new Answer<Object>() {
      @Override
      public Object answer(InvocationOnMock inv) {
        return value;
      }
    }).when(mockBean).getValue();
    ConstructorBean bean = new ConstructorBean(value);
    Assert.assertEquals(value, bean.getValue());
  }
}
