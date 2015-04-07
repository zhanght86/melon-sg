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
import secfox.soc.melon.asset.staff.domain.InterStaff;
import secfox.soc.melon.web.util.ExportUtils;

/**
 * @since 1.0 2014年10月11日,下午1:23:08
 * Excel导出
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class InterStaffView extends AbstractExcelView {

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
		List<InterStaff> staffs = (List<InterStaff>)params.get("results");
		
		//如果没有数据提示友好提示
		if (staffs == null || staffs.size() < 1) {
			setText(getCell(sheet, _BASE_INDEX, 0),MessageSourceUtils.getMessage("security.staffs.notData"));
		}else{
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			//设置数据表格里的内容
			for (int i = 0; i < staffs.size(); i++) {
				InterStaff info = staffs.get(i);
				setText(getCell(sheet, _BASE_INDEX + i, 0), info.getCode()); //编号
	        	setText(getCell(sheet, _BASE_INDEX + i, 1), info.getName()); //姓名
	        	setText(getCell(sheet, _BASE_INDEX + i, 2), info.getNumber());  //工号
	        	setText(getCell(sheet, _BASE_INDEX + i, 3), String.valueOf(info.getSex()));   //性别
	        	setText(getCell(sheet, _BASE_INDEX + i, 4), sdf.format(info.getBirthday())); //出生日期
	        	setText(getCell(sheet, _BASE_INDEX + i, 5), DictionaryUtils.filterValue("outerstaff.fulljob",info.getFullJob()));   //专职岗位
	        	setText(getCell(sheet, _BASE_INDEX + i, 6), DictionaryUtils.filterValue("outerstaff.fulljob",info.getPartJob()));   //兼职岗位
	        	setText(getCell(sheet, _BASE_INDEX + i, 7), String.valueOf(info.getOrganId()));   //单位Id
	        	setText(getCell(sheet, _BASE_INDEX + i, 8), info.getOrganName());   //单位名称
	        	setText(getCell(sheet, _BASE_INDEX + i, 9), info.getDepartName());  //部门名称
	        	setText(getCell(sheet, _BASE_INDEX + i, 10), String.valueOf(info.getChargePerson()));  //上级负责人ID
	        	setText(getCell(sheet, _BASE_INDEX + i, 11), info.getChargeName());  //上级负责人
	        	setText(getCell(sheet, _BASE_INDEX + i, 12), sdf.format(info.getOfficeDate())); 	//任职时间
	        	setText(getCell(sheet, _BASE_INDEX + i, 13), DictionaryUtils.filterValue("interstaff.title",info.getTitle())); //职称
	        	setText(getCell(sheet, _BASE_INDEX + i, 14), DictionaryUtils.filterValue("interstaff.techtitle",info.getTechTitle()));//技术职称
	        	setText(getCell(sheet, _BASE_INDEX + i, 15),info.getTel());//固话
	        	setText(getCell(sheet, _BASE_INDEX + i, 16),info.getPhone());//手机
	        	setText(getCell(sheet, _BASE_INDEX + i, 17),info.getMail());//邮箱
	        	setText(getCell(sheet, _BASE_INDEX + i, 18),String.valueOf(info.getLecturer()));//讲师
	        	setText(getCell(sheet, _BASE_INDEX + i, 19),String.valueOf(info.getExpert()));//安全专家
	        	setText(getCell(sheet, _BASE_INDEX + i, 20),String.valueOf(info.getPaperNum()));//项目组
	        	setText(getCell(sheet, _BASE_INDEX + i, 21),DictionaryUtils.filterValue("interstaff.techskill",info.getTechSkill()));//技能
	        	setText(getCell(sheet, _BASE_INDEX + i, 22),DictionaryUtils.filterValue("bool",info.isSecurity()));//保密性
	        	setText(getCell(sheet, _BASE_INDEX + i, 23),String.valueOf(info.getHistoryJob())); //证书//历史岗位
	        	setText(getCell(sheet, _BASE_INDEX + i, 24),DictionaryUtils.filterValue("bool", info.isExamine())); //任职考核
	        	setText(getCell(sheet, _BASE_INDEX + i, 25),DictionaryUtils.filterValue("interstaff.certificate", info.getCertificate())); //证书
	        	setText(getCell(sheet, _BASE_INDEX + i, 26),String.valueOf(info.getPaperNum()));//论文数量
	        	setText(getCell(sheet, _BASE_INDEX + i, 27),info.getEducation());//第一学历
	        	setText(getCell(sheet, _BASE_INDEX + i, 28),info.getProfession());//专业
	        	setText(getCell(sheet, _BASE_INDEX + i, 29), info.getRemark()); //备注
			}
		}
	}

}
