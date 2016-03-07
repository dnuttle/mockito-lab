package net.nuttle.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import net.nuttle.bo.BeanA;

@RunWith(PowerMockRunner.class)
@PrepareForTest(AService.class)
public class AServiceTest {

  

  /**
   * Attempts to mock a static method in an interface have proven fruitless.
   * I have to pass in actual values when setting up the mocked method
   * or I get an exception about...can't remember.
   * If I pass in explicit values, then I get UnfinishedStubbingException.
   * It appears that currently you simply can't mock an interface static method.
   * @throws Exception
   */
  @Test
  public void testService1() throws Exception {
    PowerMockito.mockStatic(AService.class, Answers.CALLS_REAL_METHODS.get());
    /*
    PowerMockito.doThrow(new Exception("abc"))
      .when(AService.class, "service1", any(BeanA.class), anyString());
    */
    PowerMockito
      .doAnswer(new Answer<Object>() {
        @Override
        public Object answer(InvocationOnMock iom) {
          System.out.println("MOCKED!");
          return null;
        }
      })
      .when(AService.class, "service1", null, "ghi");
    System.out.println("Ready to start");
    //BeanA<String, String> bean = new BeanA<>("abc", "def");
    BeanA<String, String> bean = Mockito.mock(BeanA.class);
    Mockito.doAnswer(new Answer<Object>() {
      @Override
      public Object answer(InvocationOnMock iom) {
        System.out.println("Setting U");
        return null;
      }
    }).when(bean).setU(anyString());
    AService.service1(bean, "ghi");
    System.out.println(bean.getU());
  }

}
