
public class Position<T> {

	private int id;
	private T element;
	
	public Position() {
		// Intentionally left empty
	}
	
	public Position(int id, T element) {
		this.id = id;
		this.element = element;
	}
	
	public Position(Position<T> positionToCopy) {
		id = positionToCopy.id;
		element = positionToCopy.element;
	}
	
	public void setElement(T element) {
		this.element = element;
	}
	
	public T getElement() {
		return element;
	}
	
	public int getId() {
		return id;
	}
	
	public boolean equals(Object otherObject) {
		if (otherObject == null)
			return false;
		else if (getClass() != otherObject.getClass())
			return false;
		else {
			Position otherPosition = (Position)otherObject;
			return (id == otherPosition.id && element.equals(otherPosition));
		}
	}
	
	public String toString() {
		return "ID: " + id + ", Element: " + element;
	}
}
