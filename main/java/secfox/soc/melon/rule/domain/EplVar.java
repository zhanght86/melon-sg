package secfox.soc.melon.rule.domain;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("eplvars")
public class EplVar implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@XStreamAsAttribute
	private String desc;
	
	@XStreamImplicit
	private List<Variable> vars;
	
	public void addVar(Variable var) {
		if(vars == null) {
			vars = Lists.newArrayList();
		} else {
			vars.add(var);
		}
	}
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public List<Variable> getVars() {
		return vars;
	}

	public void setVars(List<Variable> vars) {
		this.vars = vars;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result + ((vars == null) ? 0 : vars.hashCode());
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
		EplVar other = (EplVar) obj;
		if (desc == null) {
			if (other.desc != null)
				return false;
		} else if (!desc.equals(other.desc))
			return false;
		if (vars == null) {
			if (other.vars != null)
				return false;
		} else if (!vars.equals(other.vars))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EplVar [desc=" + desc + ", vars=" + vars + "]";
	}
}
