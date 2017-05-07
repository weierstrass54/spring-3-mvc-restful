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

    private RegionService _regionService;
    private AreaService _areaService;
    private CityService _cityService;

    @Autowired
    public AddressController(
        RegionService regionService,
        AreaService areaService,
        CityService cityService
    ) {
        _regionService = regionService;
        _areaService = areaService;
        _cityService = cityService;
    }

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
            throw new IllegalArgumentException( "Params `regionId` and `areaId` cannot be null both." );
        }
        return _cityService.loadList( regionId, areaId );
    }

}
