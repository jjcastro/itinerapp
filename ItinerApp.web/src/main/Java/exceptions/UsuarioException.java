package exceptions;

/**
 * @author jc.martha10
 */
public class UsuarioException extends  Exception{
    
    private static final long serialVersionUID = 1L;
            
    public UsuarioException() {
	}
    
    public UsuarioException(String message) {
		super(message);
	}
    
    public UsuarioException(Throwable cause) {
		super(cause);
	}
    
    public UsuarioException(String message, Throwable cause) {
		super(message, cause);
	}
}
