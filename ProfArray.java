package dataScience;



public class ProfArray {
	private final int START_SIZE = 10;
	
	//Fields
	private Professor[] Prof;
	private int size;
	
	ProfArray () {
		size = 0;
		Prof = new Professor[START_SIZE];
	}
	
	
	public int findProf(String name) {
		//return index
		int index = -1;
		for ( int i = 0; i < size; i++ ) {
			if (name == Prof[i].getName()) {
				index = i;
			}
		}
		return index;
	}
	//returns item at index
	public Professor getAt(int index) {
		return Prof[index];
	}
	
	//appends/adds an integer to list, extending array and updating size
	public void append(Professor toAdd) {
		checkAndGrow();
		Prof[size] = toAdd;
		size++;
	}
	
	public void removeAt(int index) {
		shiftLeft(index);
		size--;
	}
	
	//helper for removal adjustment toward 'left'
	private void shiftLeft(int index) {
		for (int i = index + 1; i< size; i++) {
			Prof[i - 1] = Prof[i];
		}
	}
	
	private void shiftRight(int index) {
		checkAndGrow();
		for (int i = size + 1; i> index; i--) {
			Prof[i] = Prof[i - 1];
		}
	}
	
	public void insertAt(Professor toAdd, int index) {
		shiftRight(index);
		Prof[index] = toAdd;
	}
	
	public int size() {
		return size;
	}
	
	
	private void checkAndGrow () {
		if (size < Prof.length) {
			return;
		}
		//now we know array is at max capacity --> grow array w/ newItems
		Professor[] newItems = new Professor[size + START_SIZE];
		
		//Copy the items from the old array into the new one
		for (int i = 0; i< Prof.length; i++) {
			newItems[i] = Prof[i];
		}
		//Update Reference
		Prof = newItems;
	}
	public static void main() {
		//toast a toaster
	}
}
