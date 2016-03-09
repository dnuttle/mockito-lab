package net.nuttle.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import net.nuttle.bo.BeanA;

import org.apache.log4j.Logger;
import org.junit.Ignore;
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
@PrepareForTest({AService.class, AbstractAService.class})
public class AServiceTest {

  private static final Logger LOG = Logger.getLogger(AServiceTest.class);

  /**
   * Attempts to mock a static method in an interface have proven fruitless.
   * I have to pass in actual values when setting up the mocked method
   * or I get an exception about...can't remember.
   * If I pass in explicit values, then I get UnfinishedStubbingException.
   * It appears that currently you simply can't mock an interface static method.
   * @throws Exception
   */
  @Ignore
  @SuppressWarnings("unchecked")
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
          LOG.debug("MOCKED!");
          return null;
        }
      })
      .when(AService.class, "service1", null, "ghi");
    LOG.debug("Ready to start");
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
    LOG.debug("Bean U value: " + bean.getU());
  }
/*
  @Test
  public void testService2() throws Exception {
    AService service = PowerMockito.mock(AService.class);
    PowerMockito.doAnswer(new Answer<Object>() {
      @Override
      public Object answer(InvocationOnMock iom) {
        LOG.debug("MOCKED");
        return null;
      }
    }).when(service).service2(any(BeanA.class), anyObject());
  }
*/  
  @SuppressWarnings("unchecked")
  @Test
  public void testServiceStatic() throws Exception {
    PowerMockito.mockStatic(AbstractAService.class, Answers.CALLS_REAL_METHODS.get());
    PowerMockito
      .doAnswer(new Answer<Object>() {
        @Override
        public Object answer(InvocationOnMock iom) {
          LOG.debug("MOCKED, new U value: " + iom.getArguments()[1]);
          ((BeanA<String, String>) iom.getArguments()[0]).setU("other");
          return null;
        }
      }).when(AbstractAService.class, "serviceStatic", any(BeanA.class), anyString());
    BeanA<String, String> bean = new BeanA<>("abc", "def");
    AbstractAService.serviceStatic(bean, "ghi");
    LOG.debug("Bean U: " + bean.getU());
  }
  
  /**
   * First mocks a static factory method to get a service,
   * which returns a mocked factory; it in turn returns a service
   * with a mocked method.
   * @throws Exception
   */
  @SuppressWarnings("unchecked")
  @Test
  public void testServiceFactory() throws Exception {
    PowerMockito.mockStatic(AbstractAService.class, Answers.CALLS_REAL_METHODS.get());
    PowerMockito
      .doAnswer(new Answer<Object>() {
        @Override
        public Object answer(InvocationOnMock iom) throws Exception {
          LOG.debug("Return mocked service");
          AServiceImpl service = PowerMockito.mock(AServiceImpl.class, Answers.CALLS_REAL_METHODS.get());
          PowerMockito.doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock iom) {
              LOG.debug("Mocked within testServiceFactory");
              BeanA<String, String> bean = iom.getArgumentAt(0, BeanA.class);
              bean.setU("mocked");
              return null;
            }
//          }).when(service, "service3", any(BeanA.class), any());
          }).when(service).service3(any(), any());
          return service;
        }
      }).when(AbstractAService.class, "serviceFactory");
    AService service = AbstractAService.serviceFactory();
    BeanA<String, String> bean = new BeanA<>();
    service.service3(bean, "abc");
    LOG.debug("After service3: " + bean.getU());
    service.service2(new BeanA<String, String>(), "123");
    service = AbstractAService.serviceFactory();
    BeanA<String, String> bean2 = new BeanA<>("123", "456");
    service.service2(bean2, "789");
    LOG.debug("After service2: " + bean2.getU());
  }


}
