/*
 * ListenkandidaturErgebnisTmpModel
 * 
 * Created on 18.02.2009
 * Copyright (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.modell;

/**
 * @author ugo@ivu.de, IVU Traffic Technologies AG

 */
public interface ListenkandidaturErgebnisTmpModel extends ListenkandidaturErgebnisModel {

  public String getID_Liste();

  public int getListenplatzAlt();
}
