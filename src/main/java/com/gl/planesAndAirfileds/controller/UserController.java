package com.gl.planesAndAirfileds.controller;

import com.gl.planesAndAirfileds.domain.Plane;
import com.gl.planesAndAirfileds.domain.User;
import com.gl.planesAndAirfileds.domain.api.Mappings;
import com.gl.planesAndAirfileds.domain.dto.SearchResult;
import com.gl.planesAndAirfileds.domain.dto.UserDto;
import com.gl.planesAndAirfileds.domain.filter.PagingRequest;
import com.gl.planesAndAirfileds.domain.filter.SearchRequest;
import com.gl.planesAndAirfileds.domain.filter.UserFilter;
import com.gl.planesAndAirfileds.service.UserService;
import com.gl.planesAndAirfileds.validators.UserDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.groups.Default;

/**
 * Created by marek.sroga on 2017-03-29.
 */
@RestController
public class UserController extends AbstractController
{
   private final UserService userService;

   private final UserDtoValidator userDtoValidator;

   @Autowired
   public UserController(UserService userService, UserDtoValidator userDtoValidator)
   {
      this.userService = userService;
      this.userDtoValidator = userDtoValidator;
   }

   @InitBinder
   protected void initBinder(WebDataBinder binder) {
      addValidator(binder, UserDto.class, userDtoValidator);
   }

   @RequestMapping(value = Mappings.FIND_USERS, method = RequestMethod.POST)
   @ResponseStatus(value = HttpStatus.OK)
   public SearchResult<User> findPlanes(@RequestBody SearchRequest<UserFilter> searchRequest) {
      UserFilter userFilter = searchRequest.getFilter();
      PagingRequest pagingRequest = searchRequest.getPageRequest();
      return findBySearchParams(userFilter, pagingRequest, userService);
   }

   @RequestMapping(value = Mappings.SAVE_USER, method = RequestMethod.POST)
   @ResponseStatus(value = HttpStatus.OK)
   public User save(@RequestBody @Validated(Default.class) UserDto userDto) {
      User user = userDto.getUser();
      String password = userDto.getPassword();

      if (StringUtils.isEmpty(user.getSid())) {
         return userService.save(user, password);
      }
      else {
         return userService.update(user);
      }
   }

   @RequestMapping(value = Mappings.GET_USER, method = RequestMethod.GET)
   @ResponseStatus(value = HttpStatus.OK)
   public User getUser(@PathVariable(value = "sid") String sid) {
      return userService.getBySid(sid);
   }
}
