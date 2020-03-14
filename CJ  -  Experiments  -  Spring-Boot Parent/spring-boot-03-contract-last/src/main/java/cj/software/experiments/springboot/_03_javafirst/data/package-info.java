@XmlSchema(namespace = WSEndpoint.NAMESPACE_URI, elementFormDefault = XmlNsForm.QUALIFIED, xmlns =
{ @XmlNs(prefix = "jf", namespaceURI = WSEndpoint.NAMESPACE_URI)
})
package cj.software.experiments.springboot._03_javafirst.data;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;

import cj.software.experiments.springboot._03_javafirst.controller.ws.WSEndpoint;