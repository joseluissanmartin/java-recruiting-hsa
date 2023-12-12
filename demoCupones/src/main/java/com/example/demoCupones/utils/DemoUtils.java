package com.example.demoCupones.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demoCupones.models.Cupon;
import com.example.demoCupones.services.DemoService;

@Service
public class DemoUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(DemoService.class);
	
	@Autowired
	private CacheManager cacheManager;

	public List<Cupon> ObtenerCuponesVigentes(Cupon[] cupones) {

		LocalDateTime fechaActual = LocalDateTime.now();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String fechaFormateada = fechaActual.format(formatter);

		LOGGER.info("la fecha actual es: " + fechaFormateada);

		List<Cupon> cuponesVigentes = Arrays.stream(cupones)
				.filter(cupon -> cupon.getExpiresAt().compareTo(fechaFormateada) >= 0).collect(Collectors.toList());

		return cuponesVigentes;

	}
	
	@Scheduled(cron = "0 0/1440 * * * ?")
	public void clearCache() {
		for (String name : cacheManager.getCacheNames()) {
			cacheManager.getCache(name).clear();
		}
	}

}

