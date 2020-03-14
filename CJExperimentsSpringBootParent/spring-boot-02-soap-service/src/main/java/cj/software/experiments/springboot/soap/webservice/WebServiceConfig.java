package cj.software.experiments.springboot.soap.webservice;

import javax.servlet.GenericServlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig
		extends WsConfigurerAdapter
{
	@Bean
	public ServletRegistrationBean<GenericServlet> messageDispatcherServlet(
			ApplicationContext pContext)
	{
		MessageDispatcherServlet lDispatcherServlet = new MessageDispatcherServlet();
		lDispatcherServlet.setApplicationContext(pContext);
		lDispatcherServlet.setTransformSchemaLocations(true);
		ServletRegistrationBean<GenericServlet> lResult = new ServletRegistrationBean<>(
				lDispatcherServlet,
				"/ws/*");
		return lResult;
	}

	@Bean(name = "countries")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema pCountriesSchema)
	{
		DefaultWsdl11Definition lResult = new DefaultWsdl11Definition();
		lResult.setPortTypeName("CountriesPort");
		lResult.setLocationUri("/ws");
		lResult.setTargetNamespace("http://cj.software.experiments.soap/countries");
		lResult.setSchema(pCountriesSchema);
		return lResult;
	}

	@Bean
	public XsdSchema countriesSchema()
	{
		ClassPathResource lClassPathResource = new ClassPathResource("countries.xsd");
		XsdSchema lResult = new SimpleXsdSchema(lClassPathResource);
		return lResult;
	}
}
