/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
/**
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.system.view;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import secfox.soc.melon.base.util.MessageSourceUtils;
import secfox.soc.melon.system.domain.Logger;
import secfox.soc.melon.web.util.ExportUtils;

/**
 * Excel导出
 * @since 1.0 2014-10-17
 * @author <a href="mailto:huangxm@legendsec.com">黄晓梅</a>
 * @version 1.0
 */
public class LoggerView extends AbstractExcelView{
	
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
		List<Logger> logger = (List<Logger>)params.get("results");
		
		//如果没有数据提示友好提示
		if (logger == null || logger.size() < 1) {
			setText(getCell(sheet, _BASE_INDEX, 0),MessageSourceUtils.getMessage("system.logger.notData"));
		}else{
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
			//设置数据表格里的内容
			for (int i = 0; i < logger.size(); i++) {
				Logger info = logger.get(i);
				setText(getCell(sheet, _BASE_INDEX + i, 0), info.getOperator().getUsername());
	        	setText(getCell(sheet, _BASE_INDEX + i, 1), info.getFunction());
	        	setText(getCell(sheet, _BASE_INDEX + i, 2), sdf.format(info.getOccurTime()));
	        	setText(getCell(sheet, _BASE_INDEX + i, 3), info.getIp());
	        	setText(getCell(sheet, _BASE_INDEX + i, 4), info.getClazz());
	        	setText(getCell(sheet, _BASE_INDEX + i, 5), info.getModule());
	        	setText(getCell(sheet, _BASE_INDEX + i, 6), info.getResult());
			}
		}
	}

}
