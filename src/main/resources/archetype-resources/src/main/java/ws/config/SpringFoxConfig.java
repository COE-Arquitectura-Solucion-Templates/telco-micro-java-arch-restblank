package ${groupId}.ws.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
/**
 * Clase encargada de incluir SUAGGER_2.0 en la aplicación para autodescripción del API.
 * @version 1.0.0
 * @author COEArquitectura@telefonica.com
 * @since 19/05/2021
 */
public class SpringFoxConfig    {                                    
	@Bean
	/**
	 *  Metodo que permite generar la configuración para SWAGGER 2.0
	 * @return Docket
	 */
	public Docket api() {
	    return new Docket(DocumentationType.SWAGGER_2)
	    		  .select()
		            .apis(RequestHandlerSelectors.any())
		            .paths(PathSelectors.regex("/operacion.*"))
		            .build().apiInfo(apiInfo());
	}
	/**
	 * Metodo que retorna la configuración de API Info
	 * @return ApiInfo
	 */
	private ApiInfo apiInfo() {
	    return new ApiInfo(
	      "REST Blank API", 
	      "Some custom description of API.", 
	      "API TOS", 
	      "Terms of service", 
	      new Contact("John Doe", "www.telefonica.com.com", "desarrollador.name@telefonica.com"), 
	      "License of API", "API license URL");
	}
}
	

