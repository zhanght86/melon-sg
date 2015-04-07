package secfox.soc.las.dao;

import java.util.List;
import java.util.Map;

import secfox.soc.las.domain.Alert;

public interface AlertDao {

	public List<Alert> findBySql(String sql, Map<String, Object> params);
}
