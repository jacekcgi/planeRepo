package com.gl.planesAndAirfileds.controller;

import com.gl.planesAndAirfileds.domain.Plane;
import com.gl.planesAndAirfileds.domain.PlaneSid;
import com.gl.planesAndAirfileds.domain.api.Mappings;
import com.gl.planesAndAirfileds.domain.dto.SearchResult;
import com.gl.planesAndAirfileds.domain.filter.PlaneFilter;
import com.gl.planesAndAirfileds.domain.filter.SearchRequest;
import com.gl.planesAndAirfileds.service.PlaneService;
import com.gl.planesAndAirfileds.validators.PlaneValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.groups.Default;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PlanesController extends AbstractController {

    private RestTemplate restTemplate;

    @Value("${simulator.plane.add.url}")
    private String simulatorPlaneAddUrl;

    private PlaneService planeService;

    private PlaneValidator planeValidator;

    @Autowired
    public PlanesController(PlaneService planeService, PlaneValidator planeValidator, RestTemplateBuilder builder) {
        this.planeService = planeService;
        this.planeValidator = planeValidator;
        this.restTemplate = builder.build();
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        addValidator(binder, Plane.class, planeValidator);
    }

    @RequestMapping(value = Mappings.CREATE_PLANE, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public Plane save(@RequestBody @Validated(Default.class) Plane plane) {
        boolean newPlane = plane.getSid() == null;
        Plane savedPlane = planeService.save(plane);
       if(newPlane) {
            restTemplate.postForEntity(simulatorPlaneAddUrl, savedPlane, Plane.class);
        }
        return savedPlane;

    }

    @RequestMapping(value = Mappings.FIND_PLANES)
    @ResponseStatus(value = HttpStatus.OK)
    public Iterable<Plane> getPlaneList() {
        return planeService.getAllPlanes();
    }

    @RequestMapping(value = Mappings.FIND_PLANE_SIDS)
    @ResponseStatus(value = HttpStatus.OK)
    public List<PlaneSid> getPlanesSid() {
        return planeService.getAllPlanesSid();
    }

    @RequestMapping(value = "/find/planes", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public SearchResult<Plane> findPlanes(@RequestBody SearchRequest<PlaneFilter> searchRequest) {
        PlaneFilter planeFilter = searchRequest.getFilter();

        long count = planeService.countBySearchParams(planeFilter);
        List<Plane> planes = new ArrayList<Plane>();
        if (count > 0) {
            int page = searchRequest.getPageRequest().getPage();
            int size = searchRequest.getPageRequest().getSize();
            int pages = (int) Math.ceil((double)count / (double)size);
            if (page >= pages) {
                searchRequest.getPageRequest().setPage(pages - 1);
            }
            PageRequest pageRequest = searchRequest.toPageRequest();

            planes = planeService.findBySearchParams(searchRequest.getFilter(), pageRequest);
        }
        return new SearchResult<>(planes, count, searchRequest.getPageRequest());
    }
}
