package com.adobe.halo;

import org.apache.log4j.Logger;

public class Service {

  private static final Logger LOG = Logger.getLogger(Service.class);

  private String propertyA = "default";
  private String propertyB = "default";

  public Service(String propertyA, String propertyB) {
    this.propertyA = propertyA;
    this.propertyB = propertyB;
  }
  
  public void setPropertyA(String value) {
    LOG.debug("default implementation");
    this.propertyA = value;
  }
  
  public String getPropertyA() {
    LOG.debug("default implementation");
    return propertyA;
  }
  
  public void setPropertyB(String value) {
    LOG.debug("default implementation");
    this.propertyB = value;
  }
  
  public String getPropertyB() {
    LOG.debug("default implementation");
    return propertyB;
  }
}
