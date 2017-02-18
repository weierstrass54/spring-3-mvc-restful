package ru.weierstrass.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.weierstrass.models.address.Area;
import ru.weierstrass.models.address.City;
import ru.weierstrass.models.address.Region;
import ru.weierstrass.services.address.AreaService;
import ru.weierstrass.services.address.CityService;
import ru.weierstrass.services.address.RegionService;

import java.util.List;

@RestController
public class AddressController {

    @Autowired
    private RegionService _regionService;
    @Autowired
    private AreaService _areaService;
    @Autowired
    private CityService _cityService;

    @RequestMapping( path = "/address/region", method = RequestMethod.GET )
    public List<Region> getRegionList() throws Exception {
        return _regionService.loadList();
    }

    @RequestMapping( path = "/address/area", method = RequestMethod.GET )
    public List<Area> getAreaList( @RequestParam( "regionId" ) Integer regionId ) throws Exception {
        return _areaService.loadList( regionId );
    }

    @RequestMapping( path = "/address/mainCity", method = RequestMethod.GET )
    public List<City> getMainCityList() throws Exception {
        return _cityService.loadList();
    }

    @RequestMapping( path = "/address/city", method = RequestMethod.GET )
    public List<City> getCityList(
            @RequestParam( name = "regionId", required = false ) Integer regionId,
            @RequestParam( name = "areaId", required = false ) Integer areaId
    ) throws Exception {
        if( regionId == null && areaId == null ) {
            //TODO: bad request exception handler
        }
        return _cityService.loadList( regionId, areaId );
    }

}
