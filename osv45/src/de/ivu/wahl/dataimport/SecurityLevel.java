/*
 * SecurityLevel
 * 
 * Created on 10.11.2009
 * Copyright (c) 2009-2017 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.dataimport;

/**
 * @author tdu@ivu.de, IVU Traffic Technologies AG
 */
public enum SecurityLevel {

  // No security to check hashcode
  NO_HASH_CODE,

  // The hashcode is presented to the user in total and the user must confirm
  CONFIRM_HASH_CODE,

  // An incomplete hashcode is presented to the user must enter the missing parts
  ASK_FOR_HASH_CODE;
}
