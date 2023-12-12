package com.example.demoCupones.services;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demoCupones.constants.DemoConstant;
import com.example.demoCupones.models.Cupon;
import com.example.demoCupones.utils.DemoUtils;

@Service
public class DemoService {

	private RestTemplate restTemplate = new RestTemplate();

	@Autowired
	private DemoUtils demoUtils;

	@Autowired
	public DemoService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Cacheable(value = "cupones")
	public List<Cupon> consumirCupones() {
		String url = DemoConstant.URL_CUPONES;
		Cupon[] cupones = restTemplate.getForObject(url, Cupon[].class);

		List<Cupon> litaCupones = new ArrayList<Cupon>();

		litaCupones = demoUtils.ObtenerCuponesVigentes(cupones);

		return litaCupones;
	}

}

