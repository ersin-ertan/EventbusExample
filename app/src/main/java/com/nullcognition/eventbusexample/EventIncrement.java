package com.nullcognition.eventbusexample;
/**
 * Created by ersin on 07/12/14 at 4:06 PM
 */
public class EventIncrement {

   int incrementAmount = 0;

   public int getIncrementAmount(){
	  return incrementAmount;
   }

   public EventIncrement(int inIncrementAmount){
	  incrementAmount = inIncrementAmount;
   }
}
