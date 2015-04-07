/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.staff.view;

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
import secfox.soc.melon.asset.staff.domain.OuterStaff;
import secfox.soc.melon.web.util.ExportUtils;

/**
 * @since 1.0 2014年10月11日,下午1:23:08
 * Excel导出
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class OuterStaffView extends AbstractExcelView {

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
		List<OuterStaff> staffs = (List<OuterStaff>)params.get("results");
		
		//如果没有数据提示友好提示
		if (staffs == null || staffs.size() < 1) {
			setText(getCell(sheet, _BASE_INDEX, 0),MessageSourceUtils.getMessage("security.staffs.notData"));
		}else{
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			//设置数据表格里的内容
			for (int i = 0; i < staffs.size(); i++) {
				OuterStaff info = staffs.get(i);
				setText(getCell(sheet, _BASE_INDEX + i, 0), info.getCode()); //编号
	        	setText(getCell(sheet, _BASE_INDEX + i, 1), info.getName()); //姓名
	        	setText(getCell(sheet, _BASE_INDEX + i, 2), info.getNumber());  //工号
	        	setText(getCell(sheet, _BASE_INDEX + i, 3), String.valueOf(info.getSex()));   //性别
	        	setText(getCell(sheet, _BASE_INDEX + i, 4), sdf.format(info.getBirthday())); //出生日期
	        	setText(getCell(sheet, _BASE_INDEX + i, 5), DictionaryUtils.filterValue("outerstaff.fulljob",info.getFullJob()));   //所属岗位
	        	setText(getCell(sheet, _BASE_INDEX + i, 6), DictionaryUtils.filterValue("outerstaff.certificate",info.getCertificate()));   //证书
	        	setText(getCell(sheet, _BASE_INDEX + i, 7), info.getCompanyName());  //所辖公司
	        	setText(getCell(sheet, _BASE_INDEX + i, 8), sdf.format(info.getOfficeDate())); 	//上岗时间
	        	setText(getCell(sheet, _BASE_INDEX + i, 9), DictionaryUtils.filterValue("bool", info.isExamine()));  					//是否参加任职考试
	        	setText(getCell(sheet, _BASE_INDEX + i, 10),info.getDepartName());  //管理部门
	        	setText(getCell(sheet, _BASE_INDEX + i, 11), info.getChargeName());  //内部管理人员
	        	setText(getCell(sheet, _BASE_INDEX + i, 12), info.getTel());  //联系方式
	        	setText(getCell(sheet, _BASE_INDEX + i, 13), DictionaryUtils.filterValue("outerstaff.maintainAutor", info.getMaintainAutor())); //维护权限
	        	setText(getCell(sheet, _BASE_INDEX + i, 14), info.getProject()); 	//参与项目			
	        	setText(getCell(sheet, _BASE_INDEX + i, 15), DictionaryUtils.filterValue("outerstaff.work",info.getWork())); //主要工作
	        	setText(getCell(sheet, _BASE_INDEX + i, 16), info.getRemark()); //备注
			}
		}
	}

}
