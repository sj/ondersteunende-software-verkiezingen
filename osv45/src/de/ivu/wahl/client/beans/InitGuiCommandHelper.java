/*
 * Created on 05.04.2011
 * Copyright (c) 2011 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.client.beans;

import static de.ivu.wahl.client.util.GUICommand.GUI_CLASS_1;

import java.util.Map;

import de.ivu.wahl.client.beans.InitGuiCommand.GUICommandList;
import de.ivu.wahl.client.util.GUICommand;

@SuppressWarnings("hiding")
class InitGuiCommandHelper {
  private final InitGuiCommand parent;

  private final Map<String, String> jspLevelWorkName;
  private final GUICommandList commandList;
  private final int level;
  private String rights;
  private String guiClass = GUI_CLASS_1;
  private boolean gebietsabhaengig = false;

  public void setRights(String rights) {
    this.rights = rights;
  }

  public void setGuiClass(String guiClass) {
    this.guiClass = guiClass;
  }

  public void setGebietsabhaengig(boolean gebietsabhaengig) {
    this.gebietsabhaengig = gebietsabhaengig;
  }

  InitGuiCommandHelper(InitGuiCommand parent,
      Map<String, String> jspLevelWorkName,
      GUICommandList commandList,
      int level) {
    this.parent = parent;
    this.jspLevelWorkName = jspLevelWorkName;
    this.commandList = commandList;
    this.level = level;
  }

  public GUICommand addCommand(Command command, String nameKey, String titleKey, String jsp) {
    String name = parent.getBundleString(nameKey);
    jspLevelWorkName.put(level + "_" + command.getId(), name); //$NON-NLS-1$
    String title = parent.getBundleString(titleKey);
    GUICommand cmd = parent.createCommand(name,
        command,
        rights,
        gebietsabhaengig,
        jsp,
        title,
        guiClass);
    commandList.add(cmd);
    return cmd;
  }

  public void addJspPage(Command command, String nameKey, String jsp) {
    String name = parent.getBundleString(nameKey);
    jspLevelWorkName.put(level + "_" + command.getId(), name); //$NON-NLS-1$
    parent.putToJspMap(command, jsp);
  }

}