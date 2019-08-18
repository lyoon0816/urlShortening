package work.url.shortening;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("Storage")
public class Storage {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	private static String standard;
	private static Storage storage;
	private static HashMap<String, String> storages;
	
	final static char[] digits = { 	'0', '1', '2', '3', '4', '5', '6', '7',
						            '8', '9', 'a', 'b', 'c', 'd', 'e', 'f',
						            'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
						            'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
						            'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D',
						            'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
						            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
						            'U', 'V', 'W', 'X', 'Y', 'Z', '-', ':'};
	private void Storage(){}
	
	public Storage getInstange(HttpServletRequest request)
	{
		if(storage==null)
		{
			standard = request.getScheme()+"://"+request.getServerName()
				+(request.getServerPort()==80?"":":"+request.getServerPort())+"/";
				
			storage = new Storage();
			storages = new HashMap<String, String>();
		}
		return storage;
	}
	/*
	 * setRelation(OriginalUrl)
	 * 1.Set OriginalUrl
	 * 2.get ChangeUrl(string)
	 */
	public String setRelation(String oriUrl) throws Exception 
	{
		String strReturnVal;
		String strChangeUrl;
		try
		{
			if(oriUrl.indexOf("http://")<0
				&&oriUrl.indexOf("https://")<0)
				throw new Exception("잘못된 url입니다.");
			
			if(!storages.containsKey(oriUrl))
			{
				strChangeUrl =  standard+makeShortUrl(oriUrl);
				storages.put(oriUrl, strChangeUrl);
			}
			strReturnVal= getRelation(oriUrl);
		}
		catch(Exception ex)
		{
			logger.error("setRelation[e.getMessage()]=======>" + ex.getMessage());
			throw ex;
		}
		return strReturnVal;
	}
	/*
	 * getRelation(strUrl)
	 * 1.standard contain Check
	 * 2.return String
	 * 2-1.getRelation_Type1=>(change=>original)
	 * 2-2.getRelation_Type2=>(original=>change)
	 */
	public String getRelation(String strUrl) throws Exception
	{
		String strReturnVal  ;
		try
		{
			if(strUrl.indexOf("http://")<0
				&&strUrl.indexOf("https://")<0)
				throw new Exception("잘못된 url입니다.");
			
			if(strUrl.contains(standard))
				strReturnVal = getRelation_Type1(strUrl);
			else
				strReturnVal = getRelation_Type2(strUrl);
		}
		catch(Exception ex)
		{
			logger.error("getRelation[e.getMessage()]=======>" + ex.getMessage());
			throw ex;
		}
		return strReturnVal;
	}
	//change => original
	public String getRelation_Type1(String changeUrl) throws Exception
	{
		String strResult=null;
		for(String strKey : storages.keySet())
		{
			if(storages.get(strKey).equals(changeUrl))
			{
				strResult = strKey;
				break;
			}
		}
		if(strResult==null)
			throw new Exception("등록된 url이 없습니다.");
		return strResult;
	}
	//original => change
	public String getRelation_Type2(String originUrl) throws Exception
	{
		String strResult=null;
		strResult = storages.get(originUrl);
		if(strResult==null)
			throw new Exception("등록된 url이 없습니다.");
		
		return strResult;
	
	}
	
	//Make UUID(char8)
	public String makeShortUrl(String url)
	{
		String result;
		long time =  System.nanoTime();
		if(time>Math.pow(64, 8))
			time=time/10;
		result = longToBase64(time); 
		System.out.println(time + " -> " + result);
		return result;
	}
	 public String longToBase64(long v) 
	 {	
		 int shift = 6;
		 char[] buf = new char[64];
		 int charPos = 64;
		 int radix = 1 << shift;
		 long mask = radix - 1;
		 long number = v;
		 do {
			 buf[--charPos] = digits[(int) (number & mask)];
			 number >>>= shift;
		 } while (number != 0);
		 return new String(buf, charPos, (64 - charPos));
    }
}
