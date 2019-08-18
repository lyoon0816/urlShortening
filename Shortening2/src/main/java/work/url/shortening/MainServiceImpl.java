package work.url.shortening;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

@Service("MainService")
public class MainServiceImpl implements MainService{
	
	@Resource(name="Storage")
	private Storage Storage;
	@Override
	public String changeUrl(HttpServletRequest request, HttpServletResponse response,Map<String, Object> paramMap) throws Exception {
		Storage storage = Storage.getInstange(request);
		return storage.setRelation(paramMap.get("Target").toString());
		
	}
	@Override
	public String redirectUrl(HttpServletRequest request, HttpServletResponse response, Map<String, Object> paramMap) throws Exception {
		Storage storage = Storage.getInstange(request);
		return storage.getRelation(paramMap.get("Target").toString());
		
	}
}
