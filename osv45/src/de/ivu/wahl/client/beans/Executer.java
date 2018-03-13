package de.ivu.wahl.client.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * Interface f�r ein Bean, welches Commandos aus einem HTTP-Request ausf�hrt. Die Commandos sind von
 * der Form cmd=_commando_
 * 
 * @author klie@ivu.de cos@ivu.de - Copyright (c) 2002-7 Statistisches Bundesamt und IVU Traffic Technologies AG
 */

public interface Executer {

  abstract static class Command implements Serializable {
    private static final long serialVersionUID = 5761557178298963006L;
    private final String _description;

    public Command(String description) {
      _description = description;
    }

    abstract void execute(HttpServletRequest request);

    /**
     * Gibt Beschreibung zur�ck.
     * 
     * @return Beschreibung
     */
    public String getDescription() {
      return _description;
    }
  }

  interface CommandMap extends Map<String, Command> {
    /* alias */
  }

  static class CommandMapImpl extends HashMap<String, Command> implements CommandMap {
    private static final long serialVersionUID = 1751306067289820466L;
  }

  /**
   * Execute command contained in request
   * 
   * @param request HTTP-Request
   * @param n 0 indicates the command is stored as "cmd", any other number indicates"cmd"+n
   */
  void executeCommand(HttpServletRequest request, int n);
}