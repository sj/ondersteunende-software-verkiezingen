/*
 * XmlParseException
 * 
 * Created on Nov 14, 2008
 * Copyright (c) 2008 Kiesraad
 */
package de.ivu.wahl.wus.xmlsecurity;

/**
 * this exception is thrown if hash calculation fails
 * 
 * 
 * @author mike@ivu.de, IVU Traffic Technologies AG
 * @version $Id: XmlParseException.java,v 1.1 2009/01/21 17:36:02 mes Exp $
 */
public class XmlParseException extends Exception {

  /** long */
  private static final long serialVersionUID = 6795002977880489676L;

  public XmlParseException() {
  }

  public XmlParseException(String arg0) {
    super(arg0);
  }

  public XmlParseException(Throwable arg0) {
    super(arg0);
  }

  public XmlParseException(String arg0, Throwable arg1) {
    super(arg0, arg1);
  }

}
