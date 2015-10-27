package linkedList;
public interface IndexedList<T>
{
	public void add(int index, T obj);

	public T get(int index);

	public boolean isEmpty();

	public boolean isFull();

	public T remove(int index);

	public T set(int index,T obj);

	public int size();

	public String toString();
}