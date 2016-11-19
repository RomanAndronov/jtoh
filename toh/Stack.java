package toh;

/*
  By Roman Andronov
 */

import java.util.LinkedList;

public class Stack
{
	public void
	push( Object o )
	{
		ll.addFirst( o );
	}

	public Object
	pop()
	{
		return ll.removeFirst();
	}

	public Object
	top()
	{
		return ll.getFirst();
	}

	public int
	count()
	{
		return ll.size();
	}

	private LinkedList		ll = new LinkedList();
}
