package com.adobe.halo;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class ServiceTest {

  @Test
  public void testPropertyA() {
    Service service = Mockito.mock(Service.class, Answers.CALLS_REAL_METHODS.get());
    service.setPropertyA("default");
    service.setPropertyB("default");
    Mockito.doAnswer(new Answer<Object>() {
      @Override
      public Object answer(InvocationOnMock inv) {
        return "alternate";
      }
    }).when(service).getPropertyA();
    Assert.assertEquals("alternate", service.getPropertyA());
    Assert.assertEquals("default", service.getPropertyB());
  }
}
