package com.bitfoam.hangman.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBException;

import com.bitfoam.hangman.model.HangmanBean;
import com.bitfoam.hangman.model.HangmanGame;
import com.bitfoam.hangman.model.Status;
import com.bitfoam.hangman.persistence.CookieManager;
import com.bitfoam.hangman.persistence.HangmanDAO;

@Path("/game")
public class HangmansResource 
{
	
  @Context
  UriInfo uriInfo;
  @Context
  Request request;

  @GET
  @Path("management")
  @Produces({ /*MediaType.APPLICATION_XML,*/ MediaType.APPLICATION_JSON, /*MediaType.TEXT_HTML*/ })
  public List<HangmanBean> getHangmans() 
  {

    List<HangmanGame> hgs = new ArrayList<HangmanGame>();
    List<HangmanBean> hgbeans = new ArrayList<HangmanBean>();    
    hgs.addAll(HangmanDAO.instance.getModel().values());
    for(HangmanGame hg: hgs)
    {
    	hgbeans.add(new HangmanBean(hg));
    }
    return hgbeans;
  }
  
  @GET
  @Path("start")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getGame(@CookieParam(CookieManager.NAME) String hgCookie) 
		  throws JAXBException, IOException 
  {
	  
	HangmanGame hg = getHangmanGame(hgCookie);
	HangmanBean hb = new HangmanBean(hg);  
    GenericEntity<HangmanBean> entity = new GenericEntity<HangmanBean>(hb){};
    return Response.ok(entity)
            .cookie(CookieManager.getHangmanCookie(hb))
            .build();
  }

   
  @GET
  @Path("letter")
  @Produces(MediaType.APPLICATION_JSON )
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public Response playLetter(
      @QueryParam("id") String id,
      @QueryParam("letter") String letter,
      @CookieParam(CookieManager.NAME) String hgCookie,
      @Context HttpServletResponse servletResponse) throws IOException 
  {
  
	HangmanGame hg = getHangmanGame(hgCookie);
    hg.playLetter(letter);
    if(hg.getStatus() != Status.PLAYING)
    {    	
    	removeHangmanGame(hg);
    }
    
    HangmanBean hb = new HangmanBean(hg);  
    GenericEntity<HangmanBean> entity = new GenericEntity<HangmanBean>(hb){};
    return Response.ok(entity).cookie(CookieManager.getHangmanCookie(hb))
            .build();    
  }

  private static void removeHangmanGame(HangmanGame hg)
  {
	  HangmanDAO.instance.getModel().remove(hg.getId().toString(),hg);
  }
  
  private static HangmanGame getHangmanGame(String hgCookie)
  { 
	  HangmanGame hg = null;
	  if(hgCookie == null)
		{
			hg = new HangmanGame();
			HangmanDAO.instance.getModel().put(hg.getId().toString(), hg);
		}
		else
		{
			HangmanBean hb = CookieManager.getHangmanBean(hgCookie);
			hg = HangmanDAO.instance.getModel().get(hb.getId());
			if (hg == null)
			{
				hg = new HangmanGame(hb);
				HangmanDAO.instance.getModel().put(hg.getId().toString(), hg);
			}
		}
	    //System.out.println(hg);
	    return hg;	    
  }
  
} 
