package com.jorge.gestionEstudiantilBack;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GestionEstudiantilBackApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(GestionEstudiantilBackApplication.class, args);
	}
	@Bean
	public CommandLineRunner run()
	{
		return args -> {
			System.out.println("¡La aplicación se ha iniciado correctamente!");
		};
}
}
