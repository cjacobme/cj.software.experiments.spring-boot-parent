package cj.software.experiments.springboot._03_javafirst.controller.ws;

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
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema pSchema)
	{
		DefaultWsdl11Definition lResult = new DefaultWsdl11Definition();
		lResult.setPortTypeName("CalculationRequestPort");
		lResult.setLocationUri("/ws");
		lResult.setTargetNamespace(WSEndpoint.NAMESPACE_URI);
		lResult.setSchema(pSchema);
		return lResult;
	}

	@Bean
	public XsdSchema requestSchema()
	{
		ClassPathResource lClassPathResource = new ClassPathResource("request.xsd");
		XsdSchema lResult = new SimpleXsdSchema(lClassPathResource);
		return lResult;
	}
}
