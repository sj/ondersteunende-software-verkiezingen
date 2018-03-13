/*
 * ErgebniseingangModelKonstantenMap
 * 
 * Created on 30.11.2005
 * Copyright (c) 2005-7 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.client.beans;

import de.ivu.wahl.eingang.GUIEingangMsg;

public class GUIEingangMsgKostantenMap extends KonstantenWrapperMap {
   private static final long serialVersionUID = 8733330468809155653L;

   public GUIEingangMsgKostantenMap() {
      super(GUIEingangMsg.class);
   }
}
