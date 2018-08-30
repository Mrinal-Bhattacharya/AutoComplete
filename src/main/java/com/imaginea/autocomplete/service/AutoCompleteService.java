package com.imaginea.autocomplete.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
public class AutoCompleteService {

	private static Logger log = LoggerFactory.getLogger(AutoCompleteService.class);

	@Autowired
	private ResourceLoader resourceLoader;

	private List<String> cities = new ArrayList<>();

	@PostConstruct
	public void init() {
		Resource resource = this.resourceLoader.getResource("classpath:static/cities.txt");
		try (BufferedReader buffer = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
			buffer.lines().forEach(s -> cities.add(s));
		} catch (IOException e) {
			log.error("Error while reading cities", e);
		}
	}

	public String getCities(String prefix, int maxSize) {
		return this.cities.stream().filter(s -> s.toLowerCase().startsWith(prefix.toLowerCase())).limit(maxSize)
				.collect(Collectors.joining(System.lineSeparator()));
	}

}
