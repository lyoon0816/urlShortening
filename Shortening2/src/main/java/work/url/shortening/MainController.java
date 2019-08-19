package work.url.shortening;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller("MainController")
public class MainController {
	/** MainService */
	@Resource(name = "MainService")
	private MainService MainService;
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@RequestMapping(value = "/Home")
	public String home(HttpServletRequest request, HttpServletResponse response) {
		logger.info("Call Controller: Home");
		
		return "home";
	}
	
	/*
	 * original -> short
	 */
	@RequestMapping(value = "/changeUrl")
	public @ResponseBody String changeUrl(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> paramMap) {
		logger.info("Call Controller: changeUrl");
		String strReturnVal="";
		try 
		{
			strReturnVal = MainService.changeUrl(request, response, paramMap);
			logger.info("ChangeUrl[ From : "+paramMap.get("Target")+"  To : "+strReturnVal+"]");
			return strReturnVal;
		}
		catch(Exception ex)
		{
			logger.error(" e.getMessage() =======>" + ex.getMessage());
			return ex.getMessage();
		}
		
	}
	@RequestMapping(value = "/{changeUrl}")
	public @ResponseBody void redirectUrl(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> paramMap) {
		logger.info("Call Controller: redirectUrl");
		try 
		{
			paramMap.put("Target", request.getRequestURL().toString());
			String url = MainService.redirectUrl(request, response, paramMap);
			logger.info("RedirectUrl[ From : "+request.getRequestURL().toString()+"  To : "+url+"]");
	        if (url != null) {
	        	response.addHeader("Location", url);
	        	response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
	        } else {
	        	response.sendError(HttpServletResponse.SC_NOT_FOUND);
	        }
		}
		catch(Exception ex)
		{
			logger.error(" e.getMessage() =======>" + ex.getMessage());
		}
		
	}
	@RequestMapping(value = "/test/{changeUrl}")
	public @ResponseBody void testRedirectUrl(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> paramMap) {
		logger.info("Call Controller: redirectUrl");
		try 
		{
			String url = MainService.redirectUrl(request, response, paramMap);
			logger.info("RedirectUrl[ From : "+paramMap.get("Target")+"  To : "+url+"]");
	        if (url != null) {
	        	response.addHeader("Location", url);
	        	response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
	        } else {
	        	response.sendError(HttpServletResponse.SC_NOT_FOUND);
	        }
		}
		catch(Exception ex)
		{
			logger.error(" e.getMessage() =======>" + ex.getMessage());
		}
		
	}
}
