package gameObjects.action.message;

public class UsertextMessageAction extends UserMessage{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8533629930145589362L;
	public final String message;
	public UsertextMessageAction(int source, int sourcePlayer, int destinationPlayer, String message) {
		super(source, sourcePlayer, destinationPlayer);
		this.message = message;
	}

}