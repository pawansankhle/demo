package com.petCart.rest.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;

import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petCart.dao.ICartDAO;
import com.petCart.model.Cart;
import com.petCart.service.ICartService;

@Service("CartRestImpl")
@Path("/cart")
@Produces(MediaType.APPLICATION_JSON)
public class CartRestImpl {

private Logger logger = LoggerFactory.getLogger(CartRestImpl.class);
	
	@Context
	private SearchContext context;
	
	@Autowired
	private ICartService cartService;
	
	

	
	@POST
	@Path("update")
	public Cart updateCart(Cart cart){
		logger.info("inside @class CartRestImpl @method updateCart entry..");
		Message message = PhaseInterceptorChain.getCurrentMessage();
	    HttpServletRequest request = (HttpServletRequest)message.get(AbstractHTTPDestination.HTTP_REQUEST);
	    HttpSession  session = request.getSession(true);
	    return null;
	    //return cartService.updateCart(session,cart);
	 }
	
	
	@POST
	@Path("addToCart/{id}")
	public Cart addToCart(@PathParam("id") Long pid){
		Message message = PhaseInterceptorChain.getCurrentMessage();
	    HttpServletRequest request = (HttpServletRequest)message.get(AbstractHTTPDestination.HTTP_REQUEST);
	    HttpSession  session = request.getSession(true);
	    
		logger.info("inside @class CartRestImpl @method addToCart entry...id is: "+pid);
		return cartService.addToCart(session,pid); 
	  }
	

   @GET
   public Cart getCartData(){
	   logger.info("inside @class CartRestImpl @method getCartData entry...id is: ");
	   Message message = PhaseInterceptorChain.getCurrentMessage();
	   HttpServletRequest request = (HttpServletRequest)message.get(AbstractHTTPDestination.HTTP_REQUEST);
	   HttpSession  session = request.getSession(true);
	    
	   Cart cart = (Cart) session.getAttribute("cart");
	   if(cart != null){
	       cart = cartService.getCartByName(cart.getName());
	       if(cart != null)
	    	   return cart;
	       else
	    	   return cartService.createCart(session);
	     }else
		   return cartService.createCart(session);
	   
   }
	
}
	
	
