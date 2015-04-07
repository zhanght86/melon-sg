/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.report.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import secfox.soc.melon.base.util.ApplicationContextUtils;
import secfox.soc.melon.report.domain.ReportFile;
import secfox.soc.melon.report.service.ReportFileService;

/**
 * @since 1.0 2014年9月28日,上午9:34:32
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class ReportFileUtils {

	private final Logger logger = LoggerFactory.getLogger(ReportFileUtils.class);
	
	//文件存放路径
	private final File fileRepository;
	
	/**
	 * 构造器
	 * @param fileRepositoy 文件存放地址
	 */
	public ReportFileUtils(Resource fileRepositoy) {
		File repositoy = null;
		try {
			repositoy = fileRepositoy.getFile();
		} catch (IOException e) {
			logger.warn("Upload Report is not valid, Use Default path:/ReportFiles!");
			repositoy = new File("/ReportFiles");
		}
		//创建文件存放目录
		if(!repositoy.isDirectory() && !repositoy.exists()) {
			repositoy.mkdirs();
        }
		//创建临时目录
		File tempPath = new File(repositoy, "TEMP");
		if(!tempPath.exists()) {
			tempPath.mkdir();
		}
		//初始化文件存放目录
		fileRepository = new File(repositoy, "TARGET");
		if(!fileRepository.exists()) {
			fileRepository.mkdir();
		}
	}
	
	/**
	 * 将文件保存到磁盘上
	 * @param ajaxFile
	 * @param file
	 * @return
	 */
	public boolean writeToDisk(ReportFile report, MultipartFile file) {
		boolean result = false;
		try {
			File newFile = new File(fileRepository, report.getName());//通过文件名获取文件存放地址
			BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(newFile));
			BufferedInputStream input = new BufferedInputStream(file.getInputStream());
			//声明缓存
			long sum = 0;
            int len = 0;
            byte[] buffer = new byte[8096];
            // 写入文件
            do {
                len = input.read(buffer);
                if(len > -1) {
                    sum = sum + len;
                    output.write(buffer, 0, len);//输出到文件中
                }
            } while(len > -1);
			//关闭输入输出流
			input.close();
	        output.close();
	        result = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 下载文件
	 * @param ajaxFile
	 * @param request
	 * @param response
	 */
	public void readFromDisk(ReportFile reportFile, HttpServletRequest request, HttpServletResponse response) {
		try {
        	String userAgent = StringUtils.upperCase(request.getHeader("User-Agent"));
        	String realName = reportFile.getName();
        	//乱码
        	if(StringUtils.contains(userAgent, "MSIE") || StringUtils.contains(userAgent, "TRIDENT")) {
    			response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(realName,"UTF-8"));
            }else{
            	response.setHeader("Content-Disposition", "attachment;filename=" + new String(realName.getBytes("UTF-8"),"ISO8859-1"));
            }
        	//创建缓冲输入输出流
		    BufferedInputStream bis = null;  
		    BufferedOutputStream bos = null;
		    //文件实际路径
		    File downFile = new File(fileRepository, reportFile.getName());
		    //String downLoadPath = fileRepositoy.getFile().getAbsoluteFile() + "\\TARGET\\"+businessId;  
		    response.setContentType("application/octet-stream");
		    response.setHeader("Content-Length", String.valueOf(downFile.length()));
		    bis = new BufferedInputStream(new FileInputStream(downFile));  
		    bos = new BufferedOutputStream(response.getOutputStream());  
		    //创建缓冲区
		    byte[] buff = new byte[8096];  
		    int offset = 0;
		    //输出文件
		    while((offset=bis.read(buff))>-1){
		    	bos.write(buff, 0, offset);
		    }
		    bis.close();  
		    bos.close(); 
        } catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * 
	 * @param ajaxFile
	 */
	public boolean removeFromDisk(Long id) {
		 //获取实际文件名
		 ReportFileService service = ApplicationContextUtils.getBean(ReportFileService.class);
		 File downFile = new File(fileRepository, service.find(id).getName());
		 return downFile.delete();
	}

	/**
	 * @param file
	 * @return
	 */
	public ReportFile parseFromFile(String domainId, MultipartFile file) {
		ReportFile reportFile = new ReportFile();
		reportFile.setTotal(file.getSize());
		reportFile.setCreateTime(new Date());
		if(file instanceof CommonsMultipartFile) {
			CommonsMultipartFile commonFile = (CommonsMultipartFile)file;
			reportFile.setName(commonFile.getFileItem().getName());
		} else {
			reportFile.setName(file.getOriginalFilename());
		}
		reportFile.setBusinessId(domainId);
		return reportFile;
	}
	
}
