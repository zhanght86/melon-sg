<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>

<div class="row" style="margin:0px -20px 0px 0px;">
	<div class="col-xs-6 organ" >
		<div class="thumbnail" >
  			<div class="caption" style="background-color:#e5e5dc;height: 150px">
				
				<a href="<c:url value='/las/dashboard/default?subpath=UMS_WORM1#/dashboard/elasticsearch/UMS_WORM'/>">
					<h4>
						恶意程序
					</h4>
				</a>
				<div style="height: 70px">
					&nbsp;&nbsp;&nbsp;&nbsp;恶意程序是指插入到信息系统中的一段程序，危害系统中数据、应用程序或操作系统的保密性、完整性或可用性，或影响信息系统的正常运行。主要包括：特洛伊木马、蠕虫、病毒、僵尸软件等。
				</div>
				<a href="<c:url value='/las/dashboard/default?subpath=UMS_WORM#/dashboard/elasticsearch/UMS_WORM'/>">
						事件展示
				</a>
				&nbsp;&nbsp;
				<a href="<c:url value='/elasticsearch/evilProgram'/>">
						恶意代码传播
				</a>
				&nbsp;&nbsp;
				<a href="<c:url value='/elasticsearch/virusBreakOut'/>">
						恶意代码爆发
				</a>
				&nbsp;&nbsp;
				<a href="<c:url value='/las/dashboard/default?subpath=UMS_WORM_ALERT#/dashboard/elasticsearch/UMS_WORM_ALERT'/>">
						告警查询
				</a>
			</div>
  		</div>
	</div>
	<div class="col-xs-6 organ">
		<div class="thumbnail">
  			<div class="caption" style="background-color:#e5e5dc;;height: 150px">
				<a href="<c:url value='/asset/infoSystem/listOrgans'/>">
					<h4>
					扫描行为
					</h4>
				</a>
				<div style="height: 70px">
					&nbsp;&nbsp;&nbsp;&nbsp;端口扫描是指某些别有用心的人发送一组端口扫描消息，试图以此侵入某台计算机，并了解其提供的计算机网络服务类型（这些网络服务均与端口号相关）。
				</div>
				<a href="<c:url value='/las/dashboard/default?subpath=UMS_SCAN_ALERT#/dashboard/elasticsearch/UMS_SCAN_ALERT'/>">
					告警查询
				</a>
				&nbsp;&nbsp;
				<a href="<c:url value='/elasticsearch/visitPurpose'/>">
						网络扫描
				</a>
				&nbsp;&nbsp;
				<a href="<c:url value='/elasticsearch/portScan'/>">
						端口扫描
				</a>
			</div>
  		</div>
	</div>
</div>
<div class="row" style="margin:0px -20px 0px 0px">
	<div class="col-xs-6 organ">
			<div class="thumbnail">
	  			<div class="caption" style="background-color:#e5e5dc;;height: 150px">
					<a href="<c:url value='/asset/group/home'/>">
						<h4>
							WEB攻击
						</h4>
					</a>
					<div style="height: 70px">
						&nbsp;&nbsp;&nbsp;&nbsp;常见的Web攻击分为两类：一是利用Web服务器的漏洞进行攻击，如CGI缓冲区溢出，目录遍历漏洞利用等攻击;二是利用网页自身的安全漏洞进行攻击，如SQL注入，跨站脚本攻击等。
					</div>
					<a href="<c:url value='/las/dashboard/default?subpath=UMS_WEBATTACK#/dashboard/elasticsearch/UMS_WEBATTACK'/>">
							事件展示
					</a>
					&nbsp;&nbsp;
					<a href="<c:url value='/elasticsearch/evilProgram'/>">
							WEB攻击
					</a>
					&nbsp;&nbsp;
					<a href="<c:url value='/las/dashboard/default?subpath=UMS_WEBATTACK_ALERT#/dashboard/elasticsearch/UMS_WEBATTACK_ALERT'/>">
							告警查询
					</a>
				</div>
	  		</div>
		</div>
		<div class="col-xs-6 organ" >
		<div class="thumbnail" >
  			<div class="caption" style="background-color:#e5e5dc;;height: 150px">
				
				<a href="<c:url value='/asset/device/listOrgans'/>">
					<h4>
						口令探测
					</h4>
				</a>
				<div style="height: 70px">
					展现账号登录失败事件按照主机的分布情况
				</div>
				<a href="<c:url value='/las/dashboard/default?subpath=UMS_PWDETECT#/dashboard/elasticsearch/UMS_PWDETECT'/>">
						事件展示
				</a>
				&nbsp;&nbsp;
				<a href="<c:url value='/elasticsearch/pwdetectScan'/>">
					口令探测
				</a>
				&nbsp;&nbsp;
				<a href="<c:url value='/elasticsearch/pwdetectScanB'/>">
					账号遍历
				</a>
				&nbsp;&nbsp;
				<a href="<c:url value='/las/dashboard/default?subpath=UMS_PWDETECT_ALERT#/dashboard/elasticsearch/UMS_PWDETECT_ALERT'/>">
						告警查询
				</a>
			</div>
  		</div>
	</div>
</div>
<div class="row" style="margin:0px -20px 0px 0px;">
	<div class="col-xs-6 organ">
		<div class="thumbnail">
  			<div class="caption" style="background-color:#e5e5dc;;height: 150px">
				<a href="<c:url value='/asset/infoSystem/listOrgans'/>">
					<h4>
						DDOS攻击
					</h4>
				</a>
				<div style="height: 70px">
					&nbsp;&nbsp;&nbsp;&nbsp;DDoS攻击通过大量合法的请求占用大量网络资源，以达到瘫痪网络的目的。
				</div>
				<a href="<c:url value='/las/dashboard/default?subpath=UMS_DDOS#/dashboard/elasticsearch/UMS_DDOS'/>">
						事件展示
				</a>&nbsp;&nbsp;
				<a href="<c:url value='/las/dashboard/default?subpath=UMS_DDOS_ALERT#/dashboard/elasticsearch/UMS_DDOS_ALERT'/>">
						告警查询
				</a>
			</div>
  		</div>
	</div>
	<div class="col-xs-6 organ">
		<div class="thumbnail">
  			<div class="caption" style="background-color:#e5e5dc;;height: 150px">
				<a href="<c:url value='/asset/group/home'/>">
					<h4>
						僵尸网络
					</h4>
				</a>
				<div style="height: 70px">
					&nbsp;&nbsp;&nbsp;&nbsp;僵尸网络 Botnet 是指采用一种或多种传播手段。 攻击者通过各种途径传播僵尸程序感染互联网上的大量主机，而被感染的主机将通过一个控制信道接收攻击者的指令，组成一个僵尸网络
				</div>
			</div>
  		</div>
	</div>
</div>
<div class="row" style="margin:0px -20px 0px 0px;">
	<div class="col-xs-6 organ" >
		<div class="thumbnail" >
  			<div class="caption" style="background-color:#e5e5dc;;height: 150px">
				
				<a href="<c:url value='/asset/device/listOrgans'/>">
					<h4>
						恶意信息发布
					</h4>
				</a>
				<div style="height: 70px">
					&nbsp;&nbsp;&nbsp;&nbsp;恶意信息发布行为是指通过网络或其他技术手段，转发或捏造有害信息等导致的安全事件。
				</div>
				<a href="<c:url value='/las/dashboard/default?subpath=UMS_EVILINFORELEASE#/dashboard/elasticsearch/UMS_EVILINFORELEASE'/>">
						事件展示
				</a>
				
				&nbsp;&nbsp;
				<a href="<c:url value='/las/dashboard/default?subpath=UMS_ EVILINFORELEASE _ALERT#/dashboard/elasticsearch/UMS_ EVILINFORELEASE _ALERT'/>">
						告警查询
				</a>
			</div>
  		</div>
	</div>
	<div class="col-xs-6 organ">
		<div class="thumbnail">
  			<div class="caption" style="background-color:#e5e5dc;;height: 150px">
				<a href="<c:url value='/asset/infoSystem/listOrgans'/>">
					<h4>
						访问恶意网站
					</h4>
				</a>
				<div style="height: 70px">
					&nbsp;&nbsp;&nbsp;&nbsp;恶意网站是指挂载了可在计算机系统上执行恶意任务的病毒、蠕虫和特洛伊木马的非法网站。
				</div>
				<a href="<c:url value='/las/dashboard/default?subpath=UMS_VISITEVILWEB#/dashboard/elasticsearch/UMS_VISITEVILWEB'/>">
						事件展示
				</a>
				
				&nbsp;&nbsp;
				<a href="<c:url value='/las/dashboard/default?subpath=UMS_VISTEVILWEB_ALERT#/dashboard/elasticsearch/UMS_VISTEVILWEB_ALERT'/>">
						告警查询
				</a>
			</div>
  		</div>
	</div>
</div>
<div class="row" style="margin:0px -20px 0px 0px;">
	<div class="col-xs-6 organ">
		<div class="thumbnail">
  			<div class="caption" style="background-color:#e5e5dc;height: 150px">
				<a href="<c:url value='/asset/group/home'/>">
					<h4>
						窃取敏感信息
					</h4>
				</a>
				<div style="height: 70px">
					&nbsp;&nbsp;&nbsp;&nbsp;窃取敏感信息
				</div>
				<a href="<c:url value='/las/dashboard/default?subpath=UMS_STEALSENSITIVEINFO#/dashboard/elasticsearch/UMS_STEALSENSITIVEINFO'/>">
						事件展示
				</a>
				
				&nbsp;&nbsp;
				<a href="<c:url value='/las/dashboard/default?subpath=UMS_STEALSENSITIVEINFO_ALERT#/dashboard/elasticsearch/UMS_STEALSENSITIVEINFO_ALERT'/>">
						告警查询
				</a>
			</div>
  		</div>
	</div>
	<div class="col-xs-6 organ">
		<div class="thumbnail">
  			<div class="caption" style="background-color:#e5e5dc;height: 150px">
				<a href="<c:url value='/asset/group/home'/>">
					<h4>
						ARP欺骗行为
					</h4>
				</a>
				<div style="height: 70px">
					&nbsp;&nbsp;&nbsp;&nbsp;在局域网中，黑客经过收到ARP Request广播包，能够偷听到其它节点的(IP,MAC)地址,黑客就伪装为A，告诉B(受害者)一个假地址，使得B在发送给A的数据包都被黑客截取，而B浑然不知
				</div>
				<a href="<c:url value='/las/dashboard/default?subpath=UMS_STEALSENSITIVEINFO#/dashboard/elasticsearch/UMS_STEALSENSITIVEINFO'/>">
						事件展示
				</a>
				
				&nbsp;&nbsp;
				<a href="<c:url value='/las/dashboard/default?subpath=UMS_STEALSENSITIVEINFO_ALERT#/dashboard/elasticsearch/UMS_STEALSENSITIVEINFO_ALERT'/>">
						告警查询
				</a>
			</div>
  		</div>
	</div>
</div>
<link rel="stylesheet" href="<mh:theme code='jquery.jstree.css'/>" media="all" />
