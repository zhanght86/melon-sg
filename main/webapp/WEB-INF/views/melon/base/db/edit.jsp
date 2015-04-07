<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html" %>
	
<style type="text/css">
	td{
		padding:8px;
	}
	.form-input {
		background-color: #FFFFFF;
	  	border:none;
	    color: #555555;
	    font-size: 14px;
	    height: 34px;
	    line-height: 1.42857;
	    padding: 6px 12px;
	    transition: border-color 0.15s ease-in-out 0s, box-shadow 0.15s ease-in-out 0s;
	    vertical-align: middle;
	    width: 100%;
	    }
	.melon-buttons{
		float:right;
	}
	.td-checkbox span{
		margin-left:10px;
		font-size:12px;
	}
	.td-checkbox span label{
		font-weight:normal;
	}
	.td-checkbox span .others{
		width:170px;
		border-bottom:1px solid #b3b3b3;
	}
</style>

<div class="page-header">
  <h4>基本信息</h4>
</div>
<form id="organInfo" role="form" action="/melon-guoshui/db/organInfo/create" method="POST">
		<table class="table form-horizontal table-bordered">
			<tr>
				<td align="center" width="15%"><b>01单位名称</b></td>
				<td colspan="4" disabled="disabled">
				<input class="form-input" type="text" value="总局单位"/>
				</td>
			</tr>
			<tr>
				<td align="center"><b>02单位地址</b></td>
				<td colspan="4" disabled="disabled">
				<input class="form-input" type="text" value="北京市海淀区羊坊店西路5号"/>
				</td>
			</tr>
			<tr>
				<td align="center"><b>03邮政编码</b></td>
				<td colspan="2">
				<input id="postCode" name="postCode" class="form-input" type="text" value=""/>
				<!-- <label class="control-error" for="postCode" style="color: red"></label> -->
				</td>
				<td align="center" width="15%"><b>04行政区划代码</b></td>
				<td>
				GSZJ
				</td>
			</tr>
			<tr>
				<td align="center" rowspan="2"><b>05单位负责人</b></td>
				<td align="center" width="10%"><b>姓名</b></td>
				<td>
				<input id="chargePerName" name="chargePerName" class="form-input" type="text" value=""/>
				</td>
				<td align="center"><b>职务/职称</b></td>
				<td>
				<input id="chargePerJob" name="chargePerJob" class="form-input" type="text" value=""/>
				</td>
				
			</tr>
			<tr>
				<td align="center"><b>办公电话</b></td>
				<td>
				<input id="chargePerTel" name="chargePerTel" class="form-input" type="text" value=""/>
				</td>
				<td align="center"><b>电子邮件</b></td>
				<td>
				<input id="chargePerEmail" name="chargePerEmail" class="form-input" type="text" value=""/>
				</td>
			</tr>
			<tr>
				<td align="center"><b>06责任部门</b></td>
				<td colspan="4">
				<input id="chargeSection" name="chargeSection" class="form-input" type="text" value=""/>
				</td>
			</tr>
			<tr>
				<td align="center" rowspan="3"><b>07责任部门负责人</b></td>
				<td align="center"><b>姓名</b></td>
				<td>
				<input id="sectionPerName" name="sectionPerName" class="form-input" type="text" value=""/>
				</td>
				<td align="center"><b>职务/职称</b></td>
				<td>
				<input id="sectionPerJob" name="sectionPerJob" class="form-input" type="text" value=""/>
				</td>
			</tr>
			<tr>
				<td align="center"><b>办公电话</b></td>
				<td>
				<input id="sectionPerTel" name="sectionPerTel" class="form-input" type="text" value=""/>
				</td>
				<td align="center" rowspan="2"><b>电子邮件</b></td>
				<td rowspan="2">
				<input id="sectionPerEmail" name="sectionPerEmail" class="form-input" type="text" value=""/>
				</td>
			</tr>
			<tr>
				<td align="center"><b>移动电话</b></td>
				<td>
				<input id="sectionPerMobile" name="sectionPerMobile" class="form-input" type="text" value=""/>
				</td>
			</tr>
			<tr>
				<td align="center"><b>08隶属关系</b></td>
				<td colspan="4" class="td-checkbox">
					<span><input id="belong1" name="belong" type="checkbox" value="1"/><label for="belong1">1中央</label></span>
					<span><input id="belong2" name="belong" type="checkbox" value="2"/><label for="belong2">2省（自治区、直辖市）</label></span>
					<span><input id="belong3" name="belong" type="checkbox" value="3"/><label for="belong3">3地(区、市、州、盟）</label></span>
					<span><input id="belong4" name="belong" type="checkbox" value="4"/><label for="belong4">4县（区、市、旗）</label></span><br/>
					<span><input id="belong5" name="belong" type="checkbox" value="5"/><label for="belong5">9其他</label><input type="hidden" name="_belong" value="on"/>
					<input id="belongBack" name="belongBack" class="form-input others" type="text" value="" maxlength="10"/></span>
				</td>
			</tr>
			<tr>
				<td align="center"><b>09单位类型</b></td>
				<td colspan="4" class="td-checkbox">
				
				<span>
					<input id="organType1" name="organType" disabled="disabled" type="checkbox" value="1"/><label for="organType1">1党委机关</label>
				</span>
				<span>
					<input id="organType2" name="organType" disabled="disabled" type="checkbox" value="2" checked="checked"/><label for="organType2">2政府机关</label>
				</span>
				<span>
					<input id="organType3" name="organType" disabled="disabled" type="checkbox" value="3"/><label for="organType3">3事业单位</label>
				</span>
				<span>
					<input id="organType4" name="organType" disabled="disabled" type="checkbox" value="4"/><label for="organType4">4企业</label>
				</span><br/>
				<span>
					<input id="organType5" name="organType" disabled="disabled" type="checkbox" value="5"/><label for="organType5">9其他</label>
					<input id="organTypeBack" name="organTypeBack" class="form-input others" type="text" value="" maxlength="10"/>
				</span>
				</td>
			</tr>
			<tr>
				<td align="center"><b>10行业类别</b></td>
				<td colspan="4" class="td-checkbox">
					<div>
						<span><input id="tradeType1" name="tradeType" disabled="disabled" type="checkbox" value="1"/><label for="tradeType1">11电信</label></span>
						<span><input id="tradeType2" name="tradeType" disabled="disabled" type="checkbox" value="2"/><label for="tradeType2">12广电</label></span>
						<span><input id="tradeType3" name="tradeType" disabled="disabled" type="checkbox" value="3"/><label for="tradeType3">13经营性公众互联网</label></span>
					</div>
					<div>
						
						<span><input id="tradeType4" name="tradeType" disabled="disabled" type="checkbox" value="4"/><label for="tradeType4">21铁路</label></span>
						<span><input id="tradeType5" name="tradeType" disabled="disabled" type="checkbox" value="5"/><label for="tradeType5">22银行</label></span>
						<span><input id="tradeType6" name="tradeType" disabled="disabled" type="checkbox" value="6"/><label for="tradeType6">23海关</label></span>
						<span><input id="tradeType7" name="tradeType" disabled="disabled" type="checkbox" value="7" checked="checked"/><label for="tradeType7">24税务</label></span>
					</div>
					<div>
						
						<span><input id="tradeType8" name="tradeType" disabled="disabled" type="checkbox" value="8"/><label for="tradeType8">25民航</label></span>
						<span><input id="tradeType9" name="tradeType" disabled="disabled" type="checkbox" value="9"/><label for="tradeType9">26电力</label></span>
						<span><input id="tradeType10" name="tradeType" disabled="disabled" type="checkbox" value="10"/><label for="tradeType10">27证券</label></span>
						<span><input id="tradeType11" name="tradeType" disabled="disabled" type="checkbox" value="11"/><label for="tradeType11">28保险</label></span>
					</div>
					<div>
						
						<span><input id="tradeType12" name="tradeType" disabled="disabled" type="checkbox" value="12"/><label for="tradeType12">35审计</label></span>
						<span><input id="tradeType13" name="tradeType" disabled="disabled" type="checkbox" value="13"/><label for="tradeType13">36商业贸易</label></span>
						<span><input id="tradeType14" name="tradeType" disabled="disabled" type="checkbox" value="14"/><label for="tradeType14">37国土资源</label></span>
						<span><input id="tradeType15" name="tradeType" disabled="disabled" type="checkbox" value="15"/><label for="tradeType15">38能源</label></span>
						<span><input id="tradeType16" name="tradeType" disabled="disabled" type="checkbox" value="16"/><label for="tradeType16">38交通</label></span>
					</div>
					<div>
						<span><input id="tradeType17" name="tradeType" disabled="disabled" type="checkbox" value="17"/><label for="tradeType17">40统计</label></span>
						<span><input id="tradeType18" name="tradeType" disabled="disabled" type="checkbox" value="18"/><label for="tradeType18">41工商行政管理</label></span>
						<span><input id="tradeType19" name="tradeType" disabled="disabled" type="checkbox" value="19"/><label for="tradeType19">42邮政</label></span>
						<span><input id="tradeType20" name="tradeType" disabled="disabled" type="checkbox" value="20"/><label for="tradeType20">43教育</label></span>
					</div>
					<div>
						<span><input id="tradeType21" name="tradeType" disabled="disabled" type="checkbox" value="21"/><label for="tradeType21">44文化</label></span>
						<span><input id="tradeType22" name="tradeType" disabled="disabled" type="checkbox" value="22"/><label for="tradeType22">45卫生</label></span><span><input id="tradeType23" name="tradeType" disabled="disabled" type="checkbox" value="23"/><label for="tradeType23">46农业</label></span><span><input id="tradeType24" name="tradeType" disabled="disabled" type="checkbox" value="24"/><label for="tradeType24">47水利</label></span>
					</div>
					<div>
						<span><input id="tradeType25" name="tradeType" disabled="disabled" type="checkbox" value="25"/><label for="tradeType25">48外交</label></span>
						<span><input id="tradeType26" name="tradeType" disabled="disabled" type="checkbox" value="26"/><label for="tradeType26">49发展改革</label></span>
					</div>
					<div>
						<span><input id="tradeType27" name="tradeType" disabled="disabled" type="checkbox" value="27"/><label for="tradeType27">50科技</label></span>
						<span><input id="tradeType28" name="tradeType" disabled="disabled" type="checkbox" value="28"/><label for="tradeType28">51宣传</label></span>
						<span><input id="tradeType29" name="tradeType" disabled="disabled" type="checkbox" value="29"/><label for="tradeType29">52质量监督检验检疫</label></span><br/>
						<span>
							<input id="tradeType30" name="tradeType" disabled="disabled" type="checkbox" value="30"/><label for="tradeType30">99其他</label>
							<input id="tradeTypeBack" name="tradeTypeBack" class="form-input others" type="text" value="" maxlength="10"/>
						</span>
					</div>
				</td>
			</tr>
		</table>
		
		<div class="form-group melon-buttons">
			<button class="btn btn-primary"   id="btnSave" >保存</button>
			<a class="btn btn-default"   href="" >返回</a>
		</div>

</form> 

