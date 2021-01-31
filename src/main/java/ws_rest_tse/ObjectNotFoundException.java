package ws_rest_tse;

public class ObjectNotFoundException extends RuntimeException {

	/**
	 * Il fallait créer ça pour eclipse
	 * 
	 * TODO Comprendre pourquoi
	 */
	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException(Long id, Object objet) {
		super("Could not find " + objet + " " + id);
	 }
}
