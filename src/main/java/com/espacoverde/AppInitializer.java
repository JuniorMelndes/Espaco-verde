package com.espacoverde;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.espacoverde.util.Utils;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AppInitializer implements CommandLineRunner {
	private final Utils util;
	
	@Override
	public void run(String... args) throws Exception {
		if (args.length == 0) {
		}else {
			//util.readSheet("teste_tcc.xlsx");
			util.readSheet(args[0]);
		}
		
	}

}
