package com.gl.planesAndAirfileds.domain.filter;

/**
 * Created by marek.sroga on 2017-03-29.
 */
public class UserFilter implements Filter
{
   private String login;

   private String name;

   public String getLogin()
   {
      return login;
   }

   public void setLogin(String login)
   {
      this.login = login;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }
}
