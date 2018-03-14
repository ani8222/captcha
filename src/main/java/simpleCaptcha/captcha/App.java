package simpleCaptcha.captcha;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;


import model.BaseResponse;
import model.CaptchaModel;
import model.Response;
import nl.captcha.backgrounds.GradiatedBackgroundProducer;
import utility.ServiceUtility;
import utility.SimpleCaptcha;


/**
 * Hello world!
 *
 */
public class App 
{
    public static String getCaptchaId()
    {
    	Calendar c = Calendar.getInstance();
		c.add(Calendar.MINUTE, 15);
		try {
			CaptchaModel captcha = new CaptchaModel();

			String code = createList(6);
			captcha.setCode(code);
			captcha.setExpireDate(c.getTime().toString());
			captcha.setStatus("A");
			captcha.setResultCode("Succsess");

			SimpleCaptcha captchaImg = new SimpleCaptcha.Builder(200, 40).addText(code).addBackground(new GradiatedBackgroundProducer()).gimp().addNoise().addBorder().build();
			BufferedImage image = captchaImg.getImage();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ImageIO.write(image, "png", out);

			captcha.setImage(out.toByteArray());

			return ServiceUtility.toJson(captcha);
		}
		catch(Exception ex)
		{
			return ServiceUtility.toJson(getBaseResponse("100"));
		}

    }
    
	private static String createList(int size) {
	    // We are friendly towards Java and declare the *capacity* of the list here
	    // because we know already how many items it should contain.
	    List<Integer> result = new ArrayList<Integer>(size);
	    for (int i = 0; i < size; i++) {
	        result.add(i);
	    }
		 

	   Collections.shuffle(result); 
	   StringBuilder sb= new StringBuilder();
	   for (int i=0;i<result.size();i++)
		   sb.append(result.get(i));
	   
	   return sb.toString();
		
	}
	
	public static BaseResponse getBaseResponse(String errorCode) {
		return new BaseResponse(getResponse(errorCode));
	}
	
	public static Response getResponse(String errorCode) {
		return new Response(errorCode,"System error");
	}
	

		
		   public static void main( String[] args )
		    {
			   System.out.println(getCaptchaId());
		    }
		    
}
