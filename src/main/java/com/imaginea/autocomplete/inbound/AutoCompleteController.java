package com.imaginea.autocomplete.inbound;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imaginea.autocomplete.service.AutoCompleteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Validated
@Api(value = "AutoComplete", description = "Based on city prefix, List all the cities.")
public class AutoCompleteController {

	@Autowired
	private AutoCompleteService autoCompleteService;

	@GetMapping("/suggest_cities")
	@ApiOperation(value = "Suggest a list of cities", response = String.class)
	public String getCities(
			@RequestParam(name = "start", required = true) @Valid @Pattern(message = "Start should not contains special character", regexp = "[a-zA-Z0-9 ]+") String prefix,
			@RequestParam(name = "atmost", required = false, defaultValue = Integer.MAX_VALUE + "") int maxSize) {
		return this.autoCompleteService.getCities(prefix, maxSize);
	}

}
