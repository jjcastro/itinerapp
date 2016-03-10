/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author johnycsc
 */
public class ItinerarioException extends  Exception{
    
    private static final long serialVersionUID = 1L;
            
    public ItinerarioException() {
	}
    
    public ItinerarioException(String message) {
		super(message);
	}
    
    public ItinerarioException(Throwable cause) {
		super(cause);
	}
    
    public ItinerarioException(String message, Throwable cause) {
		super(message, cause);
	}
}
