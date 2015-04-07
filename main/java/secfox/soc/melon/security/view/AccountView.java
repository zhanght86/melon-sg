/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.security.view;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import secfox.soc.melon.base.util.DictionaryUtils;
import secfox.soc.melon.base.util.MessageSourceUtils;
import secfox.soc.melon.security.domain.Account;
import secfox.soc.melon.web.util.ExportUtils;

/**
 * @since 1.0 2014年10月11日,下午1:23:08
 * Excel导出
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class AccountView extends AbstractExcelView {

	/**
	 * 
	 */
	private static final int _BASE_INDEX = 2;

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.view.document.AbstractExcelView#buildExcelDocument(java.util.Map, org.apache.poi.hssf.usermodel.HSSFWorkbook, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void buildExcelDocument(Map<String, Object> params,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//获取标题
		String title = (String)params.get("title");
		ExportUtils.setExcelAttachName(request, response, title);
		//获取到模板
		HSSFSheet sheet = workbook.getSheetAt(0);
		//设置标题
		//HSSFCell cell = getCell(sheet, 0, 0);
		setText(getCell(sheet, 0, 0), title);
		
		//获取到结果
		@SuppressWarnings("unchecked")
		List<Account> account = (List<Account>)params.get("results");
		
		//如果没有数据提示友好提示
		if (account == null || account.size() < 1) {
			setText(getCell(sheet, _BASE_INDEX, 0),MessageSourceUtils.getMessage("security.account.notData"));
		}else{
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			//设置数据表格里的内容
			for (int i = 0; i < account.size(); i++) {
				Account info = account.get(i);
				setText(getCell(sheet, _BASE_INDEX + i, 0), info.getName());
	        	setText(getCell(sheet, _BASE_INDEX + i, 1), info.getCompanyName());
	        	setText(getCell(sheet, _BASE_INDEX + i, 2), info.getDepartName());
	        	setText(getCell(sheet, _BASE_INDEX + i, 3), info.getDuty());
	        	setText(getCell(sheet, _BASE_INDEX + i, 4), info.getUsername());
	        	setText(getCell(sheet, _BASE_INDEX + i, 5), info.getCode());
	        	setText(getCell(sheet, _BASE_INDEX + i, 6), info.getEmail());
	        	setText(getCell(sheet, _BASE_INDEX + i, 7), info.getMobile());
	        	setText(getCell(sheet, _BASE_INDEX + i, 8), info.getTel());
	        	setText(getCell(sheet, _BASE_INDEX + i, 9), DictionaryUtils.filterValue("sexType", info.getSex()));
	        	setText(getCell(sheet, _BASE_INDEX + i, 10), sdf.format(info.getBirthday()));
	        	setText(getCell(sheet, _BASE_INDEX + i, 11), info.getRoleNames());
	        	setText(getCell(sheet, _BASE_INDEX + i, 12), info.getRemarks());
			}
		}
	}

}
