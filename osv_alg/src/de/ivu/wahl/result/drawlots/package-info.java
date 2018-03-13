/**
 * This package covers the requirement of making decisions in the algorithm by drawing lots.
 * <p>
 * To separate the drawing lots from the rest of the algorithm, the interface {@link DrawingLotsCallback} provides the decision to 
 * the algorithm without revealing <b>how</b> the decision was made.
 * <p>
 * In some cases in the electoral law the case may occur that more than one alternative out of a given list (of at least three 
 * alternatives) has to be drawn. This event in broken down into a number of events in which only one alternative is drawn 
 * each time. So for example the list of alternatives offered in the second event contains the same alternatives as in the first
 * event with the single exception of the alternative, that was already drawn in the first event.
 * <p>
 * This is to simplify the interface and to make sure that the order of the drawing is clearly visible in the order of the events.
 * <p>
 * Usually if a decision has not already been made, the algorithm will be terminated by throwing a {@link DrawingLotsException}.
 * The DrawingLotsException contains the conflict that has be be decided by lot. This will be presented to the user. His / her 
 * decision will be stored in the database so that it cannot be modified later. After the decision was made and saved in the 
 * database, the algorithm will be restarted. This time the first event of drawing lots will be decided according to the decision
 * that was saved. If a second desision by lot is required, the algorithm is terminated again, the suer is asked again to make
 * the decision by lot, this is again saved in the database and again the algorithm is restarted. This time it is restarted with 
 * a list of all the decisions that have already been made and saved in the database.
 * <p> 
 * This way finally all decisions that must be made by lot have been made and saved in the database. Only then after restarting
 * the algorithm, the user is no longer asked and the algorithm can finally calculate the result, i.e. the seat distribution according
 * to the votes entered etc..   
 */
package de.ivu.wahl.result.drawlots;

