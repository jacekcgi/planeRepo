package com.gl.planesAndAirfileds.domain.exceptions;

/**
 * Created by marek.sroga on 2017-03-23.
 */
public class DataNotFoundException extends RuntimeException
{
   public DataNotFoundException(String message)
   {
      super(message);
   }

   public DataNotFoundException(String message, Throwable cause)
   {
      super(message, cause);
   }
}
