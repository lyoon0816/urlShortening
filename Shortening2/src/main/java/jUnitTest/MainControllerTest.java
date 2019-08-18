package jUnitTest;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import work.url.shortening.MainController;


@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations={ "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
						,"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})

public class MainControllerTest {
	/** MainService */
	@Resource(name = "MainController")
	private MainController MainController;
	MockHttpServletRequest request = new MockHttpServletRequest();
	MockHttpServletResponse response = new MockHttpServletResponse();
	Map<String, Object> paramMap = new HashMap<String, Object>();
	String strOriginUrl = "https://en.wikipedia.org/wiki/URL_shortening =";
	@Test
	public void testHome() {
		
		MainController.home(request, response);
	}

	@Test
	public void testChangeUrl() {
		paramMap.put("Target", strOriginUrl);
		MainController.changeUrl(request, response, paramMap);
	}
	
	@Test
	public void testRedirectUrl() {
		
		paramMap.put("Target", strOriginUrl);
		System.out.println(request.getRequestURL());
		String changeUrl = MainController.changeUrl(request, response, paramMap);
		paramMap.put("Target", changeUrl);
		
		MainController.testRedirectUrl(request, response, paramMap);
		
	}

}
