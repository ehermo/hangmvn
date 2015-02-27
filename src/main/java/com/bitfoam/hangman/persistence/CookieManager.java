package com.bitfoam.hangman.persistence;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

import javax.ws.rs.core.NewCookie;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.bitfoam.hangman.model.HangmanBean;
import com.bitfoam.hangman.model.Status;

/**
 * This class handles the cookies and game info held by them.
 * @author ehermo
 *
 */
public class CookieManager
{
	public final static String NAME = "hangman";
	public final static int TWO_WEEKS = 2 * 7 * 24 * 3600;
	public final static String PATH = "/";
	public final static String DOMAIN = "";
	public final static String COMMENT = "hangman cookie";
	public final static int VERSION = 1;
	public final static boolean SECURE = false;
	public final static boolean HTTPONLY = false;
	
	/**
	 * This method restore a game instance bean from a cookie
	 * @param hgCookie value of the cookie
	 * @return game instance bean
	 */
	public  synchronized static HangmanBean getHangmanBean(String hgCookie)
	{
		HangmanBean hb = null;
		String decodedString = decodeString(hgCookie);
		hb = parse(decodedString);
		
		return hb;		
	}
	
	/**
	 * This method builds a cookie from a game instance bean
	 * @param hb game instance bean
	 * @return new cookie
	 */
	public synchronized static NewCookie getHangmanCookie(HangmanBean hb)
	{
		NewCookie ck = null;
		
		int age = (hb.getStatus() == Status.PLAYING.toString()) ?TWO_WEEKS:0; 
		
		Date date = new Date();
		date.setTime(date.getTime() + age * 1000);
		
		try
		{
			String encodedValue = encodeString(toString(hb));
			ck = new NewCookie(
					NAME, 
					encodedValue, 
					PATH, 
					DOMAIN,
					VERSION,
					COMMENT,
					age,
					date,
					SECURE,
					HTTPONLY);
			
		} 
		catch (JAXBException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ck;
	}
	
	private synchronized static String encodeString(String s)
	{
		String encodedString = s;
		try
		{        
			encodedString = URLEncoder.encode(s,"UTF-8");
		}
		catch(UnsupportedEncodingException e)
		{}
		return encodedString;
	}
	
	private synchronized static String decodeString(String s) 
	{
		String decodedString = s;
		try
		{        
			decodedString = URLDecoder.decode(s,"UTF-8");
		}
		catch(UnsupportedEncodingException e)
		{}
		return decodedString;
	}

	private synchronized static HangmanBean parse(String hgCookie)
	{
		HangmanBean hb = null;
		hb = JAXB.unmarshal(new StringReader(hgCookie), HangmanBean.class);
		return hb;
	}
	
	private synchronized static String toString(HangmanBean hb) throws JAXBException, IOException 
	  { 
	      JAXBContext context = JAXBContext.newInstance(HangmanBean.class);
	      Marshaller marshaller = context.createMarshaller();
	      marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	      
	      OutputStream output = new OutputStream()
	      {
	          private StringBuilder string = new StringBuilder();
	          @Override
	          public void write(int b) throws IOException 
	          {
	              this.string.append((char) b );
	          }

	          
	          public String toString()
	          {
	              return this.string.toString();
	          }
	      };
	      marshaller.marshal(hb,output);
	      return output.toString();
	  }
}
