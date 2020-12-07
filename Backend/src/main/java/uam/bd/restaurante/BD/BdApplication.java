package uam.bd.restaurante.BD;

import java.sql.Connection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import uam.bd.restaurante.BD.MysqlConnector.DBConnection;

@SpringBootApplication
public class BdApplication 
{
	@Bean
	public Connection jdbcConnection() 
	{
	    return DBConnection.getConnection();
	}
	
	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() 
	{
        return new BCryptPasswordEncoder();
    }
	
	public static void main(String[] args) 
	{
		SpringApplication.run(BdApplication.class, args);			
	}    
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}
}
