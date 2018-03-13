/*
 * ElectionCategoryStrategy
 * 
 * Created on 01.02.2010
 * Copyright (c) 2010-2013 Kiesraad
 */
package de.ivu.wahl.wus.electioncategory;

/**
 * This abstract class provides an alternative to a case statement that makes sure you cover all
 * possible cases.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public abstract class ElectionCategoryStrategy<T, C> {
  public T process(ElectionCategory electionCategory, C context) {
    switch (electionCategory) {
      case EP :
        return processEP(context);
      case EK :
        return processEK(context);
      case TK :
        return processTK(context);
      case PS :
        return processPS(context);
      case GR :
        return processGR(context);
      case ER :
        return processER(context);
      case NR :
        return processNR(context);
      case PR :
        return processPR(context);
      case LR :
        return processLR(context);
      case IR :
        return processIR(context);
      case BC :
        return processBC(context);
      case GC :
        return processGC(context);
      case AB :
        return processAB(context);

      default :
        throw new IllegalArgumentException();
    }
  }

  protected abstract T processEP(C context);

  protected abstract T processEK(C context);

  protected abstract T processTK(C context);

  protected abstract T processPS(C context);

  protected abstract T processGR(C context);

  protected abstract T processER(C context);

  protected abstract T processNR(C context);

  protected abstract T processPR(C context);

  protected abstract T processLR(C context);

  protected abstract T processIR(C context);

  protected abstract T processBC(C context);

  protected abstract T processGC(C context);

  protected abstract T processAB(C context);

}
