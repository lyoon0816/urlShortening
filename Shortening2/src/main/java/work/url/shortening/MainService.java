package work.url.shortening;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;

public interface MainService {
	String changeUrl(HttpServletRequest request ,HttpServletResponse response,Map<String, Object> paramMap) throws Exception;
	String redirectUrl(HttpServletRequest request ,HttpServletResponse response, Map<String, Object> paramMap) throws Exception;
}
