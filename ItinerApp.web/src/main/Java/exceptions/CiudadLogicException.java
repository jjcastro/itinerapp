/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

public class CiudadLogicException extends  Exception{

    private static final long serialVersionUID = 1L;

    public CiudadLogicException() {
	}

    public CiudadLogicException(String message) {
		super(message);
	}

    public CiudadLogicException(Throwable cause) {
		super(cause);
	}

    public CiudadLogicException(String message, Throwable cause) {
		super(message, cause);
	}
}
