package com.gl.planesAndAirfileds.domain;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by marek.sroga on 2017-03-29.
 */
@Entity
@Table(name = "user")
public class User extends AbstractStatefulEntity
{
   public static final String FIELD_LOGIN = "login";

   public static final String FIELD_NAME = "name";

   public static final String FIELD_SURNAME = "surname";

   public static final int FIELD_LOGIN_LENGTH = 32;

   public static final int FIELD_NAME_LENGTH = 32;

   public static final int FIELD_SURNAME_LENGTH = 64;

   @Column(nullable = false, length = FIELD_LOGIN_LENGTH, unique = true)
   @Length(max = FIELD_LOGIN_LENGTH)
   @NotBlank
   private String login;

   @Column(nullable = false, length = FIELD_NAME_LENGTH)
   @Length(max = FIELD_NAME_LENGTH)
   @NotBlank
   private String name;

   @Column(nullable = false, length = FIELD_SURNAME_LENGTH)
   @Length(max = FIELD_SURNAME_LENGTH)
   @NotBlank
   private String surname;

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

   public String getSurname()
   {
      return surname;
   }

   public void setSurname(String surname)
   {
      this.surname = surname;
   }
}
