package joe.spring.demoweb.mvc.data;

import java.util.List;

import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement(name="data")
@XmlSeeAlso({CustomerDto.class})
public class DtoListWrapper<T> {

	@JsonProperty("data")
	private List<T> data; 

	public DtoListWrapper() {}

	public DtoListWrapper(List<T> list) {
		this.data = list;
	}

	@XmlMixed
	public List<T> getData() {
		return data;
	}
	
}