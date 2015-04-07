/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import secfox.soc.melon.asset.domain.Device;
import secfox.soc.melon.base.DateTimeType;
import secfox.soc.melon.base.util.DictionaryUtils;
import secfox.soc.melon.base.util.MessageSourceUtils;
import secfox.soc.melon.web.util.ExportUtils;



/**
 * @since 1.0 2014年10月11日,下午1:23:08
 * Excel导出
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class AssetView extends AbstractExcelView {

	private static final int _BASE_INDEX = 2;
	
	@Override
	protected void buildExcelDocument(Map<String, Object> params,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 获取标题
		String title = (String) params.get("title");
		ExportUtils.setExcelAttachName(request, response, title);
		// 获取到模板
		HSSFSheet sheet = workbook.getSheetAt(0);
		// 设置标题
		setText(getCell(sheet, 0, 0), title);

		
		
		// 获取到结果
		@SuppressWarnings("unchecked")
		List<Device> devices = (List<Device>) params.get("results");

		// 如果没有数据提示友好提示
		if (devices == null || devices.size() < 1) {
			setText(getCell(sheet, 2, 0),
					MessageSourceUtils.getMessage("asset.abstract.notDb"));
		} else {
			String produceTime ="";
			// 设置数据表格里的内容
			for (int i = 0; i < devices.size(); i++) {
				Device device = devices.get(i);
				//如果已停用
				if(device.getUsing() == 0){
					HSSFCellStyle cellStyle = workbook.createCellStyle(); //创建样式
					HSSFFont createFont = workbook.createFont(); //创建字体样式
					createFont.setFontHeightInPoints((short)11); //设置字体大小
					createFont.setFontName("宋体"); //设置字体为宋体
					createFont.setColor(HSSFColor.RED.index); //设置颜色为红色
					cellStyle.setFont(createFont); //设置字体样式到
					HSSFCell cell = getCell(sheet,  _BASE_INDEX + i, 6); 
					cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//设置居中
					cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);//设置靠左
					cell.setCellStyle(cellStyle); //设置样式
				}
				setText(getCell(sheet, _BASE_INDEX + i, 0), device.getName());
				setText(getCell(sheet, _BASE_INDEX + i, 1), device.getCode());
				setText(getCell(sheet, _BASE_INDEX + i, 2),device.getOrganName());
				//TODO wwy导出安全域字段
				setText(getCell(sheet, _BASE_INDEX + i, 3),"device.getDomainName()");
				setText(getCell(sheet, _BASE_INDEX + i, 4),device.getTypeName());
				setText(getCell(sheet, _BASE_INDEX + i, 5),device.getChargeName());
				setText(getCell(sheet, _BASE_INDEX + i, 6),DictionaryUtils.filterValue("usingState",device.getUsing()));
				setText(getCell(sheet, _BASE_INDEX + i, 7),DictionaryUtils.filterValue("bool", device.isVirtual()));
				setText(getCell(sheet, _BASE_INDEX + i, 8),DictionaryUtils.filterValue("bool", device.isHasIp()));
				setText(getCell(sheet, _BASE_INDEX + i, 9),device.getModel());
				setText(getCell(sheet, _BASE_INDEX + i, 10),device.getProducer());
				setText(getCell(sheet, _BASE_INDEX + i, 11),device.getProducerTel());
				if(device.getProduceTime()!=null){
					produceTime = DateTimeType.SHORT_DATE.getFormatter().format(device.getProduceTime());
				}
				setText(getCell(sheet, _BASE_INDEX + i, 12),produceTime);
				setText(getCell(sheet, _BASE_INDEX + i, 13),DictionaryUtils.filterValue("bool", device.isHasOs()));
				setText(getCell(sheet, _BASE_INDEX + i, 14), DictionaryUtils.filterValue("device.os", device.getOsName()));
				setText(getCell(sheet, _BASE_INDEX + i, 16),device.getRemarks());
				
				//获取这一行
				HSSFRow row = sheet.getRow(_BASE_INDEX + i);
				//不为空就设置行高 15.625为基本行高
				if(row!=null){
					row.setHeight((short) (15.625*32));
				}
			}
		}
		
		
	}

	

}
