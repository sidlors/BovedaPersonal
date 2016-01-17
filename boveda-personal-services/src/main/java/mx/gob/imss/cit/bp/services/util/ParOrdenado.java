package mx.gob.imss.cit.bp.services.util;

public class ParOrdenado<T, W> {

	private final T propiedad; 

	private final W valor;

	public ParOrdenado(T propiedad, W valor){
		this.propiedad = propiedad;
		this.valor = valor;
	}

	public T getPropiedad() {
		return propiedad;
	}

	public W getValor() {
		return valor;
	}
}
