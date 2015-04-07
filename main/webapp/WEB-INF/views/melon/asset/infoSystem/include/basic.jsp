<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper"%>




<mh:section id="basic">

	<jsp:attribute name="title">
		<fmt:message key="security.account.basic" />
	</jsp:attribute>

	<jsp:attribute name="body">
		<div class="col-xs-8 col-xs-offset-2 form-singlon">
				<%--名称--%>
				<div class="form-group">
					<sf:label path="name" cssClass="col-xs-3 control-label">
						<fmt:message key="hint.required" />
						<fmt:message key="asset.abstract.name" />
					</sf:label>
					<div class="col-xs-9 form-field">
						<sf:input path="name" cssClass="form-control" maxlength="100" />
						<sf:errors path="name" />
					</div>
				</div>
				
				<%--编码
				<div class="form-group">
					<sf:label path="code" cssClass="col-xs-3 control-label">
						<fmt:message key="hint.required" />
						<fmt:message key="asset.abstract.code" />
					</sf:label>
					<div class="col-xs-9 form-field">
						<sf:input path="code" cssClass="form-control" maxlength="100" />
						<sf:errors path="code" />
					</div>
				</div>
				--%>
				
				<%--所属单位--%>
				<div class="form-group">
					<sf:label path="organId" cssClass="col-xs-3 control-label">
						<fmt:message key="hint.required" />
						<fmt:message key="asset.abstract.organName" />
					</sf:label>
					<div class="col-xs-9 form-field">
						<sf:hidden path="organName" />
						<sf:hidden path="organPath" />
						<sf:hidden path="organId" />
						
						<!-- 准备单位选择 -->
						<mh:dropdown id="organMenu">
							<jsp:attribute name="values">
								${infoSystem.organName}
							</jsp:attribute>
							<jsp:attribute name="menus">
								<li role="presentation" id="organButtons">
									<div class="input-group">
									  <span class="input-group-btn">
									    <a class="btn btn-primary btn-sm" type="button" href="javascript:void(0);" id="btnSearch">
											<span class="glyphicon glyphicon-search"></span>
										</a>
								      </span>
								      <input type="text" class="form-control" style="height: 30px;" id="organSearch">
								      <span class="input-group-btn">
								        <a class="btn btn-primary btn-sm" href="javascript:void(0);" type="button">
								        	<fmt:message key="button.sure" />
								        </a>
								      </span>
								    </div>
								</li>
								<li role="presentation" id="organSelect"
									style="height: 250px; overflow: auto;">
									<mh:tree id="organ" rootId="1" url="organ/organization/find?includeDepart=false"
										sortName="order" scriptSelf="false">
									</mh:tree>
								</li>
							</jsp:attribute>
						</mh:dropdown>
						
						<sf:errors path="organId" />
					</div>
				</div>
			
				 <%--负责人--%>
				 <div class="form-group">
				 	<sf:label path="chargeName" cssClass="col-xs-3 control-label">
						<fmt:message key="hint.required" /> 
						<fmt:message key="asset.abstract.chargeName" />
					</sf:label>
					<div class="col-xs-9 form-field">
						<!-- chargeName -->
						<sf:hidden path="chargeName" />
						<sf:input path="chargerId" cssClass="form-control" maxlength="10" />
						<hh:select id="chargerId" onceUrl="security/account/findUsers" />
						<sf:errors path="chargerId" />
					</div>
				</div>
				
				<%-- 所属安全域 --%>
				<div class="form-group">
					<sf:label path="domainIds" cssClass="col-xs-3 control-label">
						<fmt:message key="hint.required" />
						<fmt:message key="asset.abstract.domainName" />
					</sf:label>
					<div class="col-xs-9 form-field">
						<input type="hidden" id="domainIds" name="domainIds" value="${domainIds}">
						
						<!-- 准备类型选择 -->
						<mh:dropdown id="domainMenu">
							<jsp:attribute name="values">
								${domainNames}
							</jsp:attribute>
							<jsp:attribute name="menus">
								<li role="presentation" id="domainButtons" >
									<div class="input-group">
									  <span class="input-group-btn">
									    <a class="btn btn-primary btn-sm" type="button" href="javascript:void(0);" id="domainBtn">
											<span class="glyphicon glyphicon-search"></span>
										</a>
								      </span>
								      <input type="text" class="form-control" style="height:30px;" id="domainSearch">
								      <span class="input-group-btn">
								        <a class="btn btn-primary btn-sm" href="javascript:void(0);" type="button">
								        	<fmt:message key="button.sure"/>
								        </a>
								      </span>
								    </div>
								</li>
								<li role="presentation" id="domainSelect" style="height:220px;overflow:auto;">
									<mh:tree id="domain"
											rootId="1"
											url="asset/group/find"
											sortName="order"
											scriptSelf="false"
											multiSelect="true">
									</mh:tree>
								</li>
							</jsp:attribute>
						</mh:dropdown>
						
						<sf:errors path="domainIds" />
					</div>
				</div>
				
				<%--类型--%>
				<div class="form-group">
					<sf:label path="typeId" cssClass="col-xs-3 control-label">
						<fmt:message key="hint.required" />
						<fmt:message key="asset.infoSystem.type" />
					</sf:label>
					<div class="col-xs-9 form-field">
						<sf:hidden path="typeName"/>
						<sf:hidden path="typePath"/>
						<sf:hidden path="typeId"/>
						
						<!-- 准备类型选择   dropup="true"-->
						<mh:dropdown id="typeMenu">
							<jsp:attribute name="values">
								${infoSystem.typeName}
							</jsp:attribute>
							<jsp:attribute name="menus">
								<li role="presentation" id="typeButtons" >
									<div class="input-group">
									  <span class="input-group-btn">
									    <a class="btn btn-primary btn-sm" type="button" href="javascript:void(0);" id="typeBtn">
											<span class="glyphicon glyphicon-search"></span>
										</a>
								      </span>
								      <input type="text" class="form-control" style="height:30px;" id="typeSearch">
								      <span class="input-group-btn">
								        <a class="btn btn-primary btn-sm" href="javascript:void(0);" type="button">
								        	<fmt:message key="button.sure"/>
								        </a>
								      </span>
								    </div>
								</li>
								<li role="presentation" id="typeSelect" style="height:160px;overflow:auto;">
									<mh:tree id="type"
											rootId="230"
											url="asset/type/find"
											sortName="order"
											scriptSelf="false">
									</mh:tree>
								</li>
							</jsp:attribute>
						</mh:dropdown>
						
						<sf:errors path="typeId" />
					</div>
				</div>
				
				<%--访问地址--%>
				<div class="form-group">
					<sf:label path="url" cssClass="col-xs-3 control-label">
						<fmt:message key="hint.required" />
						<fmt:message key="asset.infoSystem.url" />
					</sf:label>
					<div class="col-xs-9 form-field">
						<sf:input path="url" cssClass="form-control" maxlength="100" />
						<sf:errors path="url" />
					</div>
				</div>
				
				<%--备份信息 --%>
				<div class="form-group">
					<sf:label path="infoId" cssClass="col-xs-3 control-label">
						<fmt:message key="asset.infoSystem.infoId" />
					</sf:label>
					<div class="col-xs-9 form-field">
						<sf:input path="infoId" cssClass="form-control" maxlength="50"  />
						<hh:select id="infoId" onceUrl="asset/infoSystem/getInfo" />
						<sf:errors path="infoId" />
					</div>
				</div>
				
				<%--上线时间--%>
				<div class="form-group">
					<sf:label path="onlineTime" cssClass="col-xs-3 control-label">
						<fmt:message key="hint.required" />
						<fmt:message key="asset.infoSystem.onlineTime" />
					</sf:label>
					<div class="col-xs-9 form-field">
						<sf:input path="onlineTime" cssClass="form-control"	maxlength="100" />
						<sf:errors path="onlineTime" />
					</div>
				</div>
				
				<%--安全等级--%>
				<div class="form-group">
					<sf:label path="safeLeven" cssClass="col-xs-3 control-label">
						<fmt:message key="asset.infoSystem.safeLeven" />
					</sf:label>
					<div class="col-xs-9 form-field">
						<mh:dictionary key="info.safeLeven" var="leven"/>				
						<sf:select path="safeLeven" cssClass="form-control" items="${leven }" />		
						<sf:errors path="safeLeven" />
					</div>
				</div>
				
				
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					 <div class="modal-dialog">
					    <div class="modal-content">
					      <div class="modal-header">
					        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
					        <h4 class="modal-title" id="myModalLabel">请选择</h4>
					      </div>
					      <div class="modal-body">
					      </div>
					      <div class="modal-footer">
					        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					        <button type="button" class="btn btn-primary">Save changes</button>
					      </div>
					    </div>
					  </div>
				</div>
				
				
				<%-- 关联的设备 --%>
				<div class="form-group">
					<sf:label path="deviceIds" cssClass="col-xs-3 control-label">
						<fmt:message key="asset.infoSystem.conn.device" />
					</sf:label>
					<div class="col-xs-9 form-field">
						<sf:hidden path="deviceIds"/>
						
						<div>
							<input type="text" id="deviceNames" class="form-control" readOnly="true" style="width:312px;display:inline-block;"/>
						    <mh:button class="btn btn-primary" type="button" id = "myModel" style="display:inline-block;margin:-4px 0px 0px -4px;">请选择</mh:button>
					    </div>
					</div>
				</div>
				
				
				<%--使用状态--%>
				<div class="form-group">
					<sf:label path="using" cssClass="col-xs-3 control-label">
						<fmt:message key="asset.abstract.using" />
					</sf:label>
					<div class="col-xs-9 form-field">
						<mh:dictionary var="usingItem" key="usingState" />
						<sf:select  path="using" items="${usingItem}"  cssClass="form-control" />
						<sf:errors path="using" />
					</div>
				</div>
				
				<%--是否关键--%>
				<div class="form-group">
					<sf:label path="ishinge" cssClass="col-xs-3 control-label">
						<fmt:message key="asset.infoSystem.ishinge" />
					</sf:label>
					<div class="col-xs-9 form-field">
						<mh:dictionary var="hinge" key="bool" />
						<sf:radiobuttons path="ishinge" items="${hinge }"/>
						<sf:errors path="ishinge" />
					</div>
				</div>
				
		</div>
	</jsp:attribute>
</mh:section>

