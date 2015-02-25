package joe.spring.springweb.mvc.controller;

import java.util.ArrayList;
import java.util.List;

import joe.spring.springapp.data.reference.Country;
import joe.spring.springapp.data.reference.State;
import joe.spring.springapp.data.reference.Title;
import joe.spring.springapp.services.ReferenceService;
import joe.spring.springweb.mvc.data.DropDownData;

//import jot.projects.spring.springapp.data.reference.State;
//import jot.projects.spring.springapp.services.ReferenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ReferenceController {

	@Autowired
	protected ReferenceService refService;

	protected final static Logger log = LoggerFactory
			.getLogger(ReferenceController.class);

	
	protected ArrayList<State> lookupStatesByCountryId(Long countryId) {
		ArrayList<State> stateList = new ArrayList<State>();
		Country c = null;
		if (countryId != null) {
			c = refService.getCountryById(countryId);			
		}		
		if (c != null) {
			stateList = (ArrayList<State>) refService.getStatesByCountry(c);
		} else {	
			log.warn("Country was null. Getting all states / provinces");
			stateList = (ArrayList<State>) refService.getAllStates();
		}		
		return stateList;
	}
	
	@RequestMapping(value = "/getStatesDropDownData", method = RequestMethod.GET)
	public @ResponseBody
	List<DropDownData> getStatesDropDownDataByCountryId(
			@RequestParam(value = "countryId",required=false) Long countryId) {
		log.debug("Calling JSON service getStatesDropDownData() with countryId = " + countryId);
		ArrayList<DropDownData> dropDownList = new ArrayList<DropDownData>();
		ArrayList<State> stateList = lookupStatesByCountryId(countryId);
		if (stateList != null && stateList.size() > 0) {
			for (State s: stateList) {
				dropDownList.add(new DropDownData(s.getId(), s.getName() + " (" + s.getCode() + ")"));
			}
		}
		return dropDownList;
	}

	@RequestMapping(value = "/json/getStates", method = RequestMethod.GET)
	public @ResponseBody
	List<State> getStatesByCountryId(
			@RequestParam(value = "countryId",required=false) Long countryId) {
		log.debug("Calling JSON service getStates() with countryId = " + countryId);
		ArrayList<State> stateList = lookupStatesByCountryId(countryId);
		return stateList;
	}
	
	
	
	@RequestMapping(value = "/getTitlesDropDownData", method = RequestMethod.GET)
	public @ResponseBody
	List<DropDownData> getTitlesDropDownData() {
		log.debug("Calling JSON service getTitlesAsJSON()");
		ArrayList<DropDownData> dropDownList = new ArrayList<DropDownData>();
		ArrayList<Title> titleList = (ArrayList<Title>) refService
				.getAllTitles();
		if (titleList != null && titleList.size() > 0) {
			for (Title t : titleList) {
				dropDownList.add(new DropDownData(t.id(), t.name()));
			}
		}
		return dropDownList;
	}

	@RequestMapping(value = "/getCountriesDropDownData", method = RequestMethod.GET)
	public @ResponseBody
	List<DropDownData> getCountriesDropDownData() {
		log.debug("Calling JSON service getCountriesAsJSON()");
		ArrayList<DropDownData> dropDownList = new ArrayList<DropDownData>();
		ArrayList<Country> countryList = (ArrayList<Country>) refService
				.getAllCountries();
		if (countryList != null && countryList.size() > 0) {
			for (Country c : countryList) {
				dropDownList.add(new DropDownData(c.getId(), c.getName()));
			}
		}
		return dropDownList;
	}

}