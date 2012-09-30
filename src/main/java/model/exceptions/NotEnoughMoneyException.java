
package model.exceptions;

/**
 * Not Enough Money Exception throwing when a user tries to spend more money than has.
 * @author Tomasz Jankowski
 */
public class NotEnoughMoneyException extends Exception{
    
    private static final long serialVersionUID = -3365255286522199396L;

    public NotEnoughMoneyException(String message) {
        super(message);
    }

}
