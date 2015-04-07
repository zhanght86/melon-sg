package secfox.soc.melon.rule.dao.impl;

import org.springframework.stereotype.Repository;

import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;
import secfox.soc.melon.rule.dao.EplValueDao;
import secfox.soc.melon.rule.domain.EplValue;

@Repository
public class EplDaoImpl extends GenericDaoImpl<EplValue, Long> implements EplValueDao {

	public EplDaoImpl() {
		super(EplValue.class);
	}
	
	@Override
	public void persist(EplValue value) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "eplvalue.findByname");
		qt.addParameter("name", value.getName());
		EplValue eplValue = findFirstDomain(qt);
		if(eplValue != null) {
			super.merge(value);
		} else {
			super.persist(value);
		}
	}

}
