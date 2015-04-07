package secfox.soc.melon.event.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import secfox.soc.melon.event.service.EventProcessService;
import secfox.soc.melon.web.ActionStatus;

/**
 *门户首页---出图（力导向分布图）
 * @since 1.0 2014年12月4日下午2:21:00
 * @author <a href="mailto:xugfa@legendsec.com">徐广飞</a>
 * @version 1.0
 */

@Controller
@RequestMapping(value = "/eventProcess")
public class EventProcessController {

	private EventProcessService eventProcessService;
	
	@Inject
	public EventProcessController(EventProcessService eventProcessService) {
		super();
		this.eventProcessService = eventProcessService;
	}
	
	@RequestMapping(value = "/findIp", method=RequestMethod.POST)
	@ResponseBody
    public ActionStatus findIp(Model model,HttpServletRequest request) {
		String ip = request.getParameter("ip");
		model.addAttribute("ip",ip);
        return ActionStatus.SUCCESS;
    }
	
	@RequestMapping(value="/createEnvent",method=RequestMethod.GET)
	@ResponseBody
	public void createEnvent(HttpServletResponse response,HttpServletRequest request) throws Exception{
		String ip = request.getParameter("ip");
    	//获取图数据
		List<Map<String,Object>> sourceAdds = eventProcessService.findSourceAdd(ip);
		List<Map<String,Object>> targetAdds = eventProcessService.findTarAndsourAdd(ip);
		StringBuffer buffer= new StringBuffer();
		buffer.append("{\"nodes\":[");
    	if(sourceAdds!=null && sourceAdds.size()>0){
    		
    		JSONArray jsonArray = new JSONArray(sourceAdds.toString());
			JSONObject jsonObj = null;
			for (int j = 0; j < jsonArray.length(); j++) {
				jsonObj = jsonArray.getJSONObject(j);
				buffer.append("{\"category\":").append(jsonObj.get("category")).append(",\"name\":\"").
				append(jsonObj.get("name")).append("\",\"value\":").append(jsonObj.get("value"));
				if(j==jsonArray.length()-1){
					buffer.append("}],");
				}else{
					buffer.append("},");
				}
			}
    	}else{
    		buffer.append("}],");
    	}
    	buffer.append("\"links\":[");
    	if(targetAdds!=null && targetAdds.size()>0){
    		JSONArray jsonArray = new JSONArray(targetAdds.toString());
			JSONObject jsonObj = null;
			for (int j = 0; j < jsonArray.length(); j++) {
				jsonObj = jsonArray.getJSONObject(j);
				buffer.append("{\"source\":\"").append(jsonObj.get("source")).append("\",\"target\":\"").
				append(jsonObj.get("target")).append("\",\"weight\":").append(jsonObj.get("weight"));
				if(j==jsonArray.length()-1){
					buffer.append("}]}");
				}else{
					buffer.append("},");
				}
			}
    	}else{
    		buffer.append("}]}");
    	}
    	writerResponse(buffer.toString(), response);
	}
	
	public static void writerResponse(String str, HttpServletResponse response) {
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(str);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != out)
				out.close();
		}
	}
}
