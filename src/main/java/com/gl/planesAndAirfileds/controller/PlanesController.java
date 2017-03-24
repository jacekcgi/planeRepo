package com.gl.planesAndAirfileds.controller;

import com.gl.planesAndAirfileds.domain.Plane;
import com.gl.planesAndAirfileds.domain.api.Mappings;
import com.gl.planesAndAirfileds.domain.dto.SearchResult;
import com.gl.planesAndAirfileds.domain.filter.PagingRequest;
import com.gl.planesAndAirfileds.domain.filter.PlaneFilter;
import com.gl.planesAndAirfileds.domain.filter.SearchRequest;
import com.gl.planesAndAirfileds.service.PlaneService;
import com.gl.planesAndAirfileds.validators.PlaneValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.groups.Default;
import java.util.List;

@RestController
public class PlanesController extends AbstractController
{

   private RestTemplate restTemplate;

   @Value("${simulator.plane.add.url}")
   private String simulatorPlaneAddUrl;

   @Value("${simulator.plane.newCoordinates.url}")
   private String simulatorPlaneNewCoordinatesUrl;

   private PlaneService planeService;

   private PlaneValidator planeValidator;

   @Autowired
   public PlanesController(PlaneService planeService, PlaneValidator planeValidator, RestTemplateBuilder builder)
   {
      this.planeService = planeService;
      this.planeValidator = planeValidator;
      this.restTemplate = builder.build();
   }

   @InitBinder
   protected void initBinder(WebDataBinder binder)
   {
      addValidator(binder, Plane.class, planeValidator);
   }

   @RequestMapping(value = Mappings.CREATE_PLANE, method = RequestMethod.POST)
   @ResponseStatus(value = HttpStatus.OK)
   public Plane save(@RequestBody @Validated(Default.class) Plane plane)
   {
      boolean newPlane = plane.getSid() == null;
      Plane savedPlane = planeService.save(plane);
      if (newPlane)
      {
         restTemplate.postForEntity(simulatorPlaneAddUrl, savedPlane, Plane.class);
      }
      return savedPlane;

   }

   @RequestMapping(value = Mappings.FIND_PLANE_SIDS)
   @ResponseStatus(value = HttpStatus.OK)
   public List<String> findPlanesSid()
   {
      return planeService.findPlanesSid();
   }

   @RequestMapping(value = Mappings.GET_SEND_PLANE_TO_POSITION, method = RequestMethod.GET)
   @ResponseStatus(value = HttpStatus.OK)
   public void sendPlaneToCoordinates(@PathVariable(value = "sid") String sid,
                                      @PathVariable(value = "latitude") Double latitude,
                                      @PathVariable(value = "longitude") Double longitude)
   {
      StringBuilder builder = new StringBuilder(simulatorPlaneNewCoordinatesUrl);
      builder.append("/");
      builder.append(sid);
      builder.append("/");
      builder.append(latitude);
      builder.append("/");
      builder.append(longitude);
      builder.append("/");
      restTemplate.getForEntity(builder.toString(), null);

   }

   @RequestMapping(value = Mappings.FIND_PLANES, method = RequestMethod.POST)
   @ResponseStatus(value = HttpStatus.OK)
   public SearchResult<Plane> findPlanes(@RequestBody SearchRequest<PlaneFilter> searchRequest)
   {
      PlaneFilter planeFilter = searchRequest.getFilter();
      PagingRequest pagingRequest = searchRequest.getPageRequest();
      return findBySearchParams(planeFilter, pagingRequest, planeService);
   }
}
