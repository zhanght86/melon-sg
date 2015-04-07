package secfox.soc.melon.rule.domain;

import java.io.Serializable;
import java.util.Set;

import com.google.common.collect.Sets;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * @since 1.0 2014年12月09日,上午11:50:46
 * 规则模板
 * @author  <a href="mailto:zhangdi@legendsec.com">张棣</a>
 * @version  1.0 
 */
@XStreamAlias("rule")
public class Rule implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@XStreamAsAttribute
	private String name;
	
	@XStreamAsAttribute
	private String depends;
	
	@XStreamAsAttribute
	private boolean enable;
	
	private String controllerField;
	
	private EventType eventType;
	
	private OutPut output;
	
	private Filter filter;
	
	private Set<Epl> epls;
	
	private EplVar eplvars;
	
	public void addEql(Epl epl) {
		if(epls == null) {
			epls = Sets.newHashSet(epl);
		} else {
			epls.add(epl);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepends() {
		return depends;
	}

	public void setDepends(String depends) {
		this.depends = depends;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getControllerField() {
		return controllerField;
	}

	public void setControllerField(String controllerField) {
		this.controllerField = controllerField;
	}
	
	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public OutPut getOutPut() {
		return output;
	}

	public void setOutPut(OutPut output) {
		this.output = output;
	}

	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}

	public OutPut getOutput() {
		return output;
	}

	public void setOutput(OutPut output) {
		this.output = output;
	}

	public EplVar getEplvars() {
		return eplvars;
	}

	public void setEplvars(EplVar eplvars) {
		this.eplvars = eplvars;
	}

	public void setEpls(Set<Epl> epls) {
		this.epls = epls;
	}

	public Set<Epl> getEpls() {
		return epls;
	}

	public void setEqls(Set<Epl> epls) {
		this.epls = epls;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((controllerField == null) ? 0 : controllerField.hashCode());
		result = prime * result + ((depends == null) ? 0 : depends.hashCode());
		result = prime * result + ((epls == null) ? 0 : epls.hashCode());
		result = prime * result
				+ ((eventType == null) ? 0 : eventType.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((output == null) ? 0 : output.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rule other = (Rule) obj;
		if (controllerField == null) {
			if (other.controllerField != null)
				return false;
		} else if (!controllerField.equals(other.controllerField))
			return false;
		if (depends == null) {
			if (other.depends != null)
				return false;
		} else if (!depends.equals(other.depends))
			return false;
		if (epls == null) {
			if (other.epls != null)
				return false;
		} else if (!epls.equals(other.epls))
			return false;
		if (eventType == null) {
			if (other.eventType != null)
				return false;
		} else if (!eventType.equals(other.eventType))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (output == null) {
			if (other.output != null)
				return false;
		} else if (!output.equals(other.output))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Rule [name=" + name + ", depends=" + depends
				+ ", controllerField=" + controllerField + ", eventType="
				+ eventType + ", outPut=" + output + ", filter=" + filter
				+ ", var=" + eplvars + ", epls=" + epls + "]";
	}
	
}
