<!DOCTYPE html>  
<head>  
	<meta http-equiv="content-type" content="text/html;charset=UTF-8;" > 
</head> 
<body>
	<div style="font-size: 14px;background-color: #f2f3f5;color: #3c3c3c;font-family: "Arial","微软雅黑","宋体","Helvetica Neue",Helvetica,Arial,sans-serif;">
		<#include "/include/header.ftl">
		<div style="height:300px">
			<table style="width: 100%">
				<tbody>
					<tr>
						<td colspan="2">
							<p style="padding: 30px 0px 0px 0px;text-indent:2em">
								尊敬的用户：  
							</p>
							<p style="text-indent:4em">
								本邮件是由安全综合管理系统发送！
							</p>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<p style="text-indent:4em;color: red">
								${content}
							</p>
							<a style="margin-left:25px" href="${handle}">操作</a>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<#include "/include/footer.ftl">
	</div>
	
</body>  
</html>  
