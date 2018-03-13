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
public abstract class ElectionSubcategoryStrategy<T, C> {
  public T process(ElectionSubcategory electionSubcategory, C context) {
    switch (electionSubcategory) {
      case EP :
        return processEP(context);
      case EK :
        return processEK(context);
      case TK :
        return processTK(context);
      case PS1 :
        return processPS1(context);
      case PS2 :
        return processPS2(context);
      case AB1 :
        return processAB1(context);
      case AB2 :
        return processAB2(context);
      case GR1 :
        return processGR1(context);
      case GR2 :
        return processGR2(context);
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

      default :
        throw new IllegalArgumentException();
    }
  }

  protected abstract T processEP(C context);

  protected abstract T processEK(C context);

  protected abstract T processTK(C context);

  protected abstract T processPS1(C context);

  protected abstract T processPS2(C context);

  protected abstract T processAB1(C context);

  protected abstract T processAB2(C context);

  protected abstract T processGR1(C context);

  protected abstract T processGR2(C context);

  protected abstract T processNR(C context);

  protected abstract T processPR(C context);

  protected abstract T processLR(C context);

  protected abstract T processIR(C context);

  protected abstract T processBC(C context);

  protected abstract T processGC(C context);

}
