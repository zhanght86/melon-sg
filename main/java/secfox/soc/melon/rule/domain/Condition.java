package secfox.soc.melon.rule.domain;

import java.io.Serializable;
import java.util.Set;

import secfox.soc.melon.base.annotation.XStreamCDATA;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("condition")
public class Condition implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@XStreamAsAttribute
	private String type;
	
	@XStreamAsAttribute
	private String opr;
	
	@XStreamImplicit
	private Set<Condition> nodes;
	
	@XStreamImplicit
	private Set<Var> vars;
	
	
	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getOpr() {
		return opr;
	}


	public void setOpr(String opr) {
		this.opr = opr;
	}


	public Set<Condition> getNodes() {
		return nodes;
	}


	public void setNodes(Set<Condition> nodes) {
		this.nodes = nodes;
	}

	public Set<Var> getVars() {
		return vars;
	}


	public void setVars(Set<Var> vars) {
		this.vars = vars;
	}

	@XStreamAlias("var")
	public static class Var implements Serializable{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@XStreamAsAttribute
		private String type;
		
		@XStreamAsAttribute
		private String attrname;
		
		@XStreamAsAttribute
		private String ref;
		
		@XStreamCDATA
		private String value;
		

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getAttrname() {
			return attrname;
		}

		public void setAttrname(String attrname) {
			this.attrname = attrname;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getRef() {
			return ref;
		}

		public void setRef(String ref) {
			this.ref = ref;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((attrname == null) ? 0 : attrname.hashCode());
			result = prime * result + ((type == null) ? 0 : type.hashCode());
			result = prime * result + ((value == null) ? 0 : value.hashCode());
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
			Var other = (Var) obj;
			if (attrname == null) {
				if (other.attrname != null)
					return false;
			} else if (!attrname.equals(other.attrname))
				return false;
			if (type == null) {
				if (other.type != null)
					return false;
			} else if (!type.equals(other.type))
				return false;
			if (value == null) {
				if (other.value != null)
					return false;
			} else if (!value.equals(other.value))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Var [type=" + type + ", attrname=" + attrname + ", value="
					+ value + "]";
		}
		
	}

}
