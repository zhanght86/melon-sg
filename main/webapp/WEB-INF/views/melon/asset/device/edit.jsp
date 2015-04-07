<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper"%>

<link rel="stylesheet" href="<mh:theme code='jquery.jstree.css'/>" media="all" />
<script type="text/javascript" src="<mh:theme code='jquery.jstree.js'/>"></script>
<script type="text/javascript" src="<mh:theme code='melon.ajaxtree.js'/>"></script>

<link rel="stylesheet" href="<mh:theme code='jquery.select.css'/>" media="all" />
<script type="text/javascript" src="<mh:theme code='jquery.select.js'/>"></script>
<link rel="stylesheet" href="<mh:theme code="jquery.steps.css"/>" media="all" />
<script src="<mh:theme code="jquery.steps.js"/>"></script>

<style type="text/css">
	#attributeMap input {
		display:inline-block;
		height:30px;
		margin:5px 0
	}
	
	.tmplate {
		display : none;
	}
	
	#organMenu ul>li {
		display : block;
	}
	
	#device .content{
		height:1000px;
	}
</style>


<sf:form modelAttribute="device" role="form" cssClass="form-horizontal">
	
	<sf:hidden path="id"/>
	
	<sf:hidden path="code"/>
	
	<sf:hidden path="createTime"/>
	
	<%-- <sf:hidden path="chargeName"/> --%>
	
	<div id="assetWiz">

		<%-- 基本信息 --%>
		<jsp:include page="/WEB-INF/views/melon/asset/device/include/basic.jsp"/>

		<%-- 网络属性 --%>
		<jsp:include page="/WEB-INF/views/melon/asset/device/include/network.jsp"/>

		<%-- 生产属性 --%>
		<jsp:include page="/WEB-INF/views/melon/asset/device/include/factory.jsp"/>

		<%-- 附加属性--%>
		<jsp:include page="/WEB-INF/views/melon/asset/device/include/addition.jsp"/>

		<%--完成--%>
		<mh:section id="finish-section">
		
			<jsp:attribute name="title">
				<fmt:message key="button.finish" />
			</jsp:attribute>
			
			<jsp:attribute name="body">
				<div class="col-xs-8 col-xs-offset-2 form-singlon">
					<%--备注--%>
					<div class="form-group">
						<sf:label path="remarks" cssClass="col-xs-3 control-label">
							<fmt:message key="asset.abstract.remarks" />
						</sf:label>
						<div class="col-xs-9 form-field">
							<sf:textarea path="remarks" cssClass="form-control" rows="5" />
							<sf:errors path="remarks" />
						</div>
					</div>
				</div>
			</jsp:attribute>
		</mh:section>
	</div>
</sf:form>


<jsp:include page="/WEB-INF/views/melon/asset/device/deviceFrame.jsp" />

<script type="text/javascript">
	$(document).ready(function() {
		var deviceForm = $("#device"),
			loadUrl = '<c:url value="/asset/field/load"/>',
			loaded = false;
		//配置验证信息
		deviceForm.validate({
			/* rules : {
				name:{
					required : true 
				},
				code:{
					required : true
					<c:if test="${empty device.id }">
						,remote : {
							url : '<c:url value="/asset/device/checkUnique"/>',
							type : 'POST',
							data : {
								code : function() {
									return $("#code").val();
								}
							}
						}
					</c:if>
				},
				chargerId :{
					required : true
				}
				
			} */
		}); 

		//配置向导
		$("#assetWiz").steps({
			headerTag : "h4",
			bodyTag : "section",
			transitionEffect : "slideLeft",
			
			enableCancelButton : true,

			onStepChanging : function(event, currentIndex, newIndex) {
				//如果是扩展属性输入
				if(newIndex == 3) {
					if(!loaded) {
						if(${!empty device.id}) {
							//如果是修改则采用deviceId参数
							$("#assetFields").load(loadUrl + "?deviceId=${device.id}");
						} else {
							//如果是新增则采用typeId参数
							$("#assetFields").load(loadUrl + "?typeId="+$("#typeId").val());
						}
						loaded = true;
					}
				}
				deviceForm.validate().settings.ignore = ":disabled,:hidden";
				return deviceForm.valid();
			},

			onFinishing : function(event, currentIndex) {
				deviceForm.validate().settings.ignore = ":disabled";
				return deviceForm.valid();
			},

			onFinished : function(event, currentIndex) {
				deviceForm.submit();
				$("#finish").prop("disabled", true);
				event.preventDefault();
			},
			
			onInit : function(event, currentIndex) {
				
			},
			
			onCanceled : function(event) {
				window.location.href = '<c:url value="/asset/device/list"/>';
			}
		}); 
		
		//是否有虚拟机
	 	$(":radio[name=virtual]").on("change", function() {
			var res = $(this).val(),
				master = $("#master");
			if (res === "true") {
				master.show();
			} else {
				master.hide();
			}
		});
		
	 	//是否有操作系统
		$(":radio[name=hasOs]").on("change", function() {
			var res = $(this).val(),
			osName = $("#os_name"),
			osVersion = $("#os_version");
			if (res == 'true') {
				osName.show();
				osVersion.show();
			} else {
				osName.hide();
				osVersion.hide();
			}
		});

		//负责人
		$("#chargerId").on("change", function(e) {
			var data = e.added;
			$("#chargeName").val(data.name);
		});	
		
		//维护人
		$("#keepPersonId").on("change", function(e) {
			var data = e.added;
			$("#keepPersonName").val(data.name);
		});	
		
		//使用人
		$("#usePersonId").on("change", function(e) {
			var data = e.added;
			$("#usePersonName").val(data.name);
		});	
		
		
		
		
		
		
		//添加按钮事件
		$("#btnAdd").click(function(event) {
			$('.tmplate').clone(true).removeClass("tmplate").appendTo("#attributeMap");
			event.preventDefault();
		});
		
		//删除按钮事件
		$(".btnRemove").click(function(event) {
			$(this).parent().remove();
			event.preventDefault();
		});
		
		//选择设备列表	
		assetChooser.init(function (date){
			var dataNames=$("#deviceNames"),dataIds=$("#deviceIds"),
				domainName = new Array(),domainId = new Array(),
				doname,doId;
			for(var i=0;i<date.length;i++){
				domainName.push(date[i].name);
				domainId.push(date[i].id);
			}
			doname = domainName.join(",");
			doId  = domainId.join(",");
			dataNames.val(doname);
			dataIds.val(domainId);
		});
		$("#myModel").on("click",function(event){
			assetChooser.show();
			//阻止默认的提交动作
	        event.preventDefault();
		});
	});
	
	/*
	 * 所有文档加载完成后注册
	 */
	window.onload = function() {
		//处理IP输入
		doIpsInput();
		//处理单位选择
		doOrganSearch();
		//处理设备类型
		doTypeSearch();
		//处理安全域
		doAssetGroup();
		//处理物理环境
		doEnvi();
	};
	
	//处理IP地址输入
	function doIpsInput() {
		//必须在向导元素后查找
		var ipAddress = $("#ipAddress"),
			ipTable = ipAddress.find("table");
		//初始化IP设备
		if(ipTable.size() > 0) {
			var tbody = ipTable.find(">tbody"),
				ipRow = tbody.find("#ipRowTmpl"),
				//更新编号
				updateRowNum = function() {
					var rows = tbody.find(">tr");
					//添加默认行
					if(rows.size() == 1) {
						ipRow.clone(true).show().prependTo(tbody);
					}
					rows.not(":hidden").each(function(index, item) {
						$(item).find(">td:first").text(index + 1);
					});
			};
			//
			tbody.find(".btn-minus").on("click", function(event) {
				$(this).parents("tr").remove();//删除所在行
				updateRowNum();//更新编号
				event.preventDefault();
			})
			tbody.find(".btn-plus").on("click", function(event) {
				var row = $(this).parents("tr");
				ipRow.clone(true).show().insertAfter(row);//添加在本行之下
				updateRowNum();//更新编号
				event.preventDefault();
			})
			//添加默认行
			updateRowNum();
		}
		//处理IP设备
	 	$(":radio[name=hasIp]").on("change", function(event) {
			var value = $(this).val();
			if(value == "true") {
				ipAddress.show();
			} else {
				ipAddress.hide();
			}
		});
	}
	
	//处理安全域选择
	function doAssetGroup(){
		var domainSearch = $("#domainSearch"),
			domainText = $("#domainMenu").find(".dropdown-text"),
			btnSearch = $("#domainBtn"),
			checkedDomain = <mh:toJson value="${device.domainIds}"/>,
			rootId = AjaxTree.prototype.ROOT_ID,
			domains = new Array(); 
		
		
		//阻止默认的提交动作
		$("#domainSelect").on("click", function(event) {
			event.stopPropagation();
		});
		//处理查询
		domainSearch.on("click", function(event) {
			event.stopPropagation();
		});
		btnSearch.on("click", function(event) {
			domainTree.search($.trim(domainSearch.val()));
			event.stopPropagation();
		});
		
		//安全域勾选事件
		domainTree.onCheck(function(data) {
			checkDomain(domains,domainText);
		});
		
		//安全域勾掉事件
		domainTree.onUnCheck(function(data){
			checkDomain(domains,domainText);
		}); 
		
		domainTree.onReady(function() {
			//选中已有的节点
			if($.isArray(checkedDomain)) {
				$.each(checkedDomain, function(index, item) {
					domainTree.setChecked(item);
				});
			}
			//阻止选择根节点
			var root = $(domainTree.getNode(rootId, true)),
				rootCheck = root.find(".jstree-checkbox:first");
			rootCheck.remove(); 
		});
	}
	
	//安全域赋值
	function checkDomain(domains,domainText){
		domains = domainTree.getChecked(true);//获取选中的节点
			var name = new Array(),
				id = new Array();
			for ( var i = 0; i < domains.length; i++) {
				if(domains[i].id != 1){
					name.push(domains[i].text);
					id.push(domains[i].id);
				};
			}
			name.splice(3,domains.length-3); //删除第三个及最后一个
			id.splice(3,domains.length-3);
			domainText.text(name.join(","));
			$("#domainIds").val(id.join(","));
			if(domains.length>3){
				Dialog.warn("<fmt:message key='asset.domain.hint' />")
			};
		
	}
	
	//处理物理环境
	function doEnvi(){
		var enviSearch = $("#enviSearch"),
		enviText = $("#enviMenu").find(".dropdown-text"),
		enviName = $("#enviName"),
		enviId = $("#enviId"),
		enviPath = $("#enviPath"),
		btnSearch = $("#enviBtn");
		//阻止默认的提交动作
		$("#enviSelect").on("click", function(event) {
			event.stopPropagation();
		});
		//处理查询
		enviSearch.on("click", function(event) {
			event.stopPropagation();
		});
		btnSearch.on("click", function(event) {
			enviTree.search($.trim(enviSearch.val()));
			event.stopPropagation();
		});
		//处理选择
		enviTree.onSelect(function(data) {
			enviText.text(data.text);
			enviName.val(data.name);
			enviId.val(data.id);
			enviPath.val(data.path);
		});
	}
	
	//处理设备类型
	function doTypeSearch(){
		var typeSearch = $("#typeSearch"),
			typeText = $("#typeMenu").find(".dropdown-text"),
			typeName = $("#typeName"),
			typeId = $("#typeId"),
			typePath = $("#typePath"),
			btnSearch = $("#typeBtn");
		//阻止默认的提交动作
		$("#typeSelect").on("click", function(event) {
			event.stopPropagation();
		});
		//处理查询
		typeSearch.on("click", function(event) {
			event.stopPropagation();
		});
		btnSearch.on("click", function(event) {
			typeTree.search($.trim(typeSearch.val()));
			event.stopPropagation();
		});
		//处理选择
		typeTree.onSelect(function(data) {
			typeText.text(data.text);
			typeName.val(data.name);
			typeId.val(data.id);
			typePath.val(data.path);
		});
	}
	

	//处理组织机构选择
	function doOrganSearch() {
		var organSearch = $("#organSearch"),
			organText = $("#organMenu").find(".dropdown-text"),
			organName = $("#organName"),
			organId = $("#organId"),
			organPath = $("#organPath"),
			btnSearch = $("#btnSearch");
		//阻止默认的提交动作
		$("#organSelect").on("click", function(event) {
			event.stopPropagation();
		});
		//处理查询
		organSearch.on("click", function(event) {
			event.stopPropagation();
		});
		btnSearch.on("click", function(event) {
			organTree.search($.trim(organSearch.val()));
			event.stopPropagation();
		});
		//处理选择
		organTree.onSelect(function(data) {
			if(data.type == 1) {
				organText.text(data.text);
				organName.val(data.text);
				organId.val(data.id);
				organPath.val(data.path);
			}
		});
	}
</script>
<hh:date id="produceTime" maxDate="${newDate }" />
<hh:date id="deadline"/>
<hh:date id="catTime"/>
