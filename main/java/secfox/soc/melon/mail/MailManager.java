/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.mail;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.EncoderException;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import secfox.soc.las.domain.AlertRule;
import secfox.soc.melon.base.UrlCodeUtils;
import secfox.soc.melon.system.domain.GlobalConfig;
import secfox.soc.melon.web.util.RequestUtils;

import com.google.common.collect.Maps;

import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @since 2015-1-19,上午10:16:44
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
public class MailManager {
	
	//java mail
	private JavaMailSenderImpl sender;
	//模板处理
	private FreeMarkerConfigurer  freeMarkerConfigurer;
	
	private Resource resource;
	
	public MailManager( Resource resource, JavaMailSenderImpl sender,FreeMarkerConfigurer  freeMarkerConfigurer) {
		this.resource = resource;
		this.sender = sender;
		this.freeMarkerConfigurer = freeMarkerConfigurer;
	}
	
	/**
	 * 获取模板内容
	 * @param content
	 * @return
	 * @throws EncoderException 
	 */
	private String getMailText(String content) throws EncoderException{  
        String htmlText = ""; 
        HttpServletRequest request = RequestUtils.getRequest();
        Map<String, Object> map  = Maps.newHashMap();  
        String param = "2384,true";
        //获取请求地址
        String url = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+RequestUtils.getRequest().getContextPath()+"/alert/handle/test?sid=";
        //邮件内容
        map.put("content",content);
        //处理请求
        map.put("handle", url+UrlCodeUtils.encode(param)); 
        Template tpl = null;  
        try { 
        	//加载资源文件   
            tpl =  freeMarkerConfigurer.getConfiguration().getTemplate("mail.ftl");
            //加入map到模板中 对应${content}   
            htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(tpl, map);
        } catch (IOException e) {             
            e.printStackTrace();  
        } catch (TemplateException e) {           
            e.printStackTrace();  
        }  
        return htmlText;          
    }  
	
	/**
	 * 发送邮件
	 */
	public void sendMail(AlertRule rule,GlobalConfig config) {
		//设置服务器、用户名、密码
		sender.setHost(config.getSystemHost());
		sender.setUsername(config.getSystemMail());
		sender.setPassword(config.getSystemMailPassWord());
		MimeMessage message = sender.createMimeMessage();
		try {
			//true，表示带附件
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setFrom(config.getSystemMail());
			//群发邮件
			helper.setTo(rule.getAddress().split(";"));
			//设置主题
			helper.setSubject(rule.getSubject());
			//使用模板，true表示使用html
			helper.setText(getMailText(rule.getContent()), true);
			FileSystemResource logo = new FileSystemResource(new File(resource.getFile().getPath()+"/logo.jpg"));
			FileSystemResource attachment = new FileSystemResource(new File(resource.getFile().getPath()+"/1.jpg"));
			//内容插入附件
			helper.addInline("logo", logo);
			//添加附件
			helper.addAttachment("CoolImage.jpg", attachment);
		} catch (Exception e) {
			e.printStackTrace();
		}
		sender.send(message);
	}
	
	public static void main(String[] args) {
		String s = "好受点烧烤阿hhhhh${host},shdfjhdk苏丹红卡计划符合${type}dfdf电话号码,df$134}";
		//复制字符串
		String finals = "好受点烧烤阿hhhhh${host},shdfjhdk苏丹红卡计划符合${type}dfdf电话号码,df$134}";
		//
		Pattern p = Pattern.compile("\\$\\{(.*?)\\}");
		Matcher m = p.matcher(s);
		int i = 0;
		while(m.find()) {
			Pattern p2 = Pattern.compile("[$|{|}]");
			Matcher m2 = p2.matcher(m.group());
			//获取字段
			String field = m2.replaceAll("").trim();
			//将从数据库中取得值替换掉源字符串的占位符
			//TODO
			finals = finals.replaceFirst("\\$\\{(.*?)\\}", "dfd"+i);
			System.out.println(finals);
			i++;
		}
	}

}
