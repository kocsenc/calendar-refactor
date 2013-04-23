package com.std.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

/**
 * ObservableSet is a class that implements the functionality of both HashSet and Observable.<br/>
 * <br/>
 * This class extends Observable and will pass the following argument to its updates:
 * <ul>
 *  <li><b>Object</b>
 *   : when an element has been added or removed from the set, or has been otherwise changed<br/>
 *  </li>
 * </ul>
 * 
 * @author xxx
 *
 * @param <U> the type of elements maintained by this set
 */
public class ObservableSet<U extends Observable> extends Observable implements Set<U> {
	
	/**
	 * InternalSet is a modified HashSet that communicates 
	 * back to the enclosing ObservableSet via the private
	 * elementAdded and elementRemoved functions.  These
	 * are called when an element is added or removed
	 * respectively.
	 * 
	 * @author xxx
	 *
	 * @param <U> the type of elements maintained by this set
	 */
	private class InternalSet extends HashSet<U> {
		
	    /**
		 * unique ID for serialization
		 */
		private static final long serialVersionUID = 8227223722906614536L;

		/**
	     * Adds the specified element to this set if it is not already present
	     * (optional operation).  More formally, adds the specified element
	     * <tt>e</tt> to this set if the set contains no element <tt>e2</tt>
	     * such that
	     * <tt>(e==null&nbsp;?&nbsp;e2==null&nbsp;:&nbsp;e.equals(e2))</tt>.
	     * If this set already contains the element, the call leaves the set
	     * unchanged and returns <tt>false</tt>.  In combination with the
	     * restriction on constructors, this ensures that sets never contain
	     * duplicate elements.
	     *
	     * <p>The stipulation above does not imply that sets must accept all
	     * elements; sets may refuse to add any particular element, including
	     * <tt>null</tt>, and throw an exception, as described in the
	     * specification for {@link Collection#add Collection.add}.
	     * Individual set implementations should clearly document any
	     * restrictions on the elements that they may contain.
	     *
	     * @param e element to be added to this set
	     * @return <tt>true</tt> if this set did not already contain the specified
	     *         element
	     * @throws UnsupportedOperationException if the <tt>add</tt> operation
	     *         is not supported by this set
	     * @throws ClassCastException if the class of the specified element
	     *         prevents it from being added to this set
	     * @throws NullPointerException if the specified element is null and this
	     *         set does not permit null elements
	     * @throws IllegalArgumentException if some property of the specified element
	     *         prevents it from being added to this set
	     * @see <a href="http://java.sun.com/javase/6/docs/api/java/util/Set.html#add(E)">Set<E>.add(E)</a>
	     */
		public boolean add(U e) {
			boolean ret; // return value
			ret = super.add(e);
			if(ret)
				elementAdded(e);
			return ret;
		}

	    /**
	     * Adds all of the elements in the specified collection to this set if
	     * they're not already present (optional operation).  If the specified
	     * collection is also a set, the <tt>addAll</tt> operation effectively
	     * modifies this set so that its value is the <i>union</i> of the two
	     * sets.  The behavior of this operation is undefined if the specified
	     * collection is modified while the operation is in progress.
	     *
	     * @param  c collection containing elements to be added to this set
	     * @return <tt>true</tt> if this set changed as a result of the call
	     *
	     * @throws UnsupportedOperationException if the <tt>addAll</tt> operation
	     *         is not supported by this set
	     * @throws ClassCastException if the class of an element of the
	     *         specified collection prevents it from being added to this set
	     * @throws NullPointerException if the specified collection contains one
	     *         or more null elements and this set does not permit null
	     *         elements, or if the specified collection is null
	     * @throws IllegalArgumentException if some property of an element of the
	     *         specified collection prevents it from being added to this set
	     * @see <a href="http://java.sun.com/javase/6/docs/api/java/util/Set.html#addAll(E)">Set<E>.addAll(Collection<? extends U>)</a>
	     */
		public boolean addAll(Collection<? extends U> c) {
			if(c == null)
				throw new NullPointerException("c");
			boolean ret = false; // return value
			for(U e : c)
				ret |= add(e);
			return ret;
		}

	    /**
	     * Returns an iterator over the elements in this set.  The elements are
	     * returned in no particular order (unless this set is an instance of some
	     * class that provides a guarantee).
	     *
	     * @return an iterator over the elements in this set
	     * @see <a href="http://java.sun.com/javase/6/docs/api/java/util/Set.html#iterator()">Set<E>.iterator()</a>
	     */
		public Iterator<U> iterator() {
			return new InternalIterator(super.iterator());
		}

	    /**
	     * Removes the specified element from this set if it is present
	     * (optional operation).  More formally, removes an element <tt>e</tt>
	     * such that
	     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>, if
	     * this set contains such an element.  Returns <tt>true</tt> if this set
	     * contained the element (or equivalently, if this set changed as a
	     * result of the call).  (This set will not contain the element once the
	     * call returns.)
	     *
	     * @param o object to be removed from this set, if present
	     * @return <tt>true</tt> if this set contained the specified element
	     * @throws ClassCastException if the type of the specified element
	     *         is incompatible with this set (optional)
	     * @throws NullPointerException if the specified element is null and this
	     *         set does not permit null elements (optional)
	     * @throws UnsupportedOperationException if the <tt>remove</tt> operation
	     *         is not supported by this set
	     * @see <a href="http://java.sun.com/javase/6/docs/api/java/util/Set.html#remove(java.lang.Object)">Set<E>.remove(Object)</a>
	     */
		public boolean remove(Object o) {
			boolean ret; // return value
			ret = super.remove(o);
			if(ret)
				elementRemoved(o);
			return ret;
		}
	}

	/**
	 * InternalIterator is a wrapper around an existing
	 * Iterator that communicates back to the enclosing 
	 * ObservableSet via the private elementRemoved function.
	 * This is called whenever an element is removed.
	 * 
	 * @author xxx
	 */
	private class InternalIterator implements Iterator<U> {
		
		/**
		 * encapsulated iterator to forward function calls to
		 */
		private Iterator<U> iter;
		
		/**
		 * the last element returned by next
		 */
		private U last;

	    /**
	     * Returns <tt>true</tt> if the iteration has more elements. (In other
	     * words, returns <tt>true</tt> if <tt>next</tt> would return an element
	     * rather than throwing an exception.)
	     *
	     * @return <tt>true</tt> if the iterator has more elements.
	     * @see <a href="http://java.sun.com/javase/6/docs/api/java/util/Iterator.html#hasNext()">Iterator<E>.hasNext()</a>
	     */
		public boolean hasNext() {
			return iter.hasNext();
		}

	    /**
	     * Returns the next element in the iteration.
	     *
	     * @return the next element in the iteration.
	     * @exception NoSuchElementException iteration has no more elements.
	     * @see <a href="http://java.sun.com/javase/6/docs/api/java/util/Iterator.html#next()">Iterator<E>.next()</a>
	     */
		public U next() {
			last = iter.next();
			return last;
		}

	    /**
	     * Removes from the underlying collection the last element returned by the
	     * iterator (optional operation).  This method can be called only once per
	     * call to <tt>next</tt>.  The behavior of an iterator is unspecified if
	     * the underlying collection is modified while the iteration is in
	     * progress in any way other than by calling this method.
	     *
	     * @exception UnsupportedOperationException if the <tt>remove</tt>
	     *		  operation is not supported by this Iterator.
	     
	     * @exception IllegalStateException if the <tt>next</tt> method has not
	     *		  yet been called, or the <tt>remove</tt> method has already
	     *		  been called after the last call to the <tt>next</tt>
	     *		  method.
	     * @see <a href="http://java.sun.com/javase/6/docs/api/java/util/Iterator.html#remove()">Iterator<E>.remove()</a>
	     */
		public void remove() {
			synchronized(iter) {
				iter.remove();
				elementRemoved(last);
			}
		}
		
		/**
		 * creates a new InternalIterator encapsulated around the passed Iterator
		 * 
		 * @param iter Iterator to encapsulate and forward to
		 */
		public InternalIterator(Iterator<U> iter) {
			if(iter == null)
				throw new NullPointerException("iter");
			this.iter = iter;
		}
	}
	
	/**
	 * ElementObserver is an Observer that watches all the elements
	 * of the set and forwards their updates to the ObservableSet
	 * observers.
	 * 
	 * @author xxx
	 *
	 */
	private class ElementObserver implements Observer {

	    /**
	     * This method is called whenever the observed object is changed. An
	     * application calls an <tt>Observable</tt> object's
	     * <code>notifyObservers</code> method to have all the object's
	     * observers notified of the change.
	     *
	     * @param   o     the observable object.
	     * @param   arg   an argument passed to the <code>notifyObservers</code>
	     *                 method.
	     * @see <a href="http://java.sun.com/javase/6/docs/api/java/util/Observer.html#update(java.util.Observable,%20java.lang.Object)">Observer.update(Observable, Object)</a>
	     */
		public void update(Observable o, Object arg) {
			that.setChanged();
			that.notifyObservers(o);
		}
	}
	
	/**
	 * a pointer to this object, so that inner classes can reference the encapsulating ObservableSet
	 */
	private final ObservableSet<U> that;
	
	/**
	 * set that contains all the functionality of the Set<U> interface.  All the
	 * overrides for the Set<U> interface are forwarded to this object.
	 */
	private final Set<U> set;
	
	/**
	 * this is the instance of ElementObserver that
	 * observes all of the elements in the set.  One
	 * object is used instead of multiple, so that
	 * it can be removed from the element when the
	 * element is removed from the set.
	 */
	private final ElementObserver observer;
	
	/**
	 * Adds the observer to the new element, and
	 * notifies the ObservableSet observers that
	 * a change has been made.<br/>
	 * <br/>
	 * Should be called every time an element is added to the set.
	 * 
	 * @param e element that has been added to this set
	 */
	private void elementAdded(U e) {
		e.addObserver(observer);
		this.setChanged();
		this.notifyObservers(e);
	}

	/**
	 * Removes the observer from the element, and
	 * notifies the ObservableSet observers that
	 * a change has been made.<br/>
	 * <br/>
	 * Should be called every time an element is removed from the set.
	 * 
	 * @param e element that has been removed from this set
	 */
	private void elementRemoved(Object o) {
		Observable observable = (Observable)o;
		observable.deleteObserver(observer);
		this.setChanged();
		this.notifyObservers(observable);
	}

    /**
     * Adds the specified element to this set if it is not already present
     * (optional operation).  More formally, adds the specified element
     * <tt>e</tt> to this set if the set contains no element <tt>e2</tt>
     * such that
     * <tt>(e==null&nbsp;?&nbsp;e2==null&nbsp;:&nbsp;e.equals(e2))</tt>.
     * If this set already contains the element, the call leaves the set
     * unchanged and returns <tt>false</tt>.  In combination with the
     * restriction on constructors, this ensures that sets never contain
     * duplicate elements.
     *
     * <p>The stipulation above does not imply that sets must accept all
     * elements; sets may refuse to add any particular element, including
     * <tt>null</tt>, and throw an exception, as described in the
     * specification for {@link Collection#add Collection.add}.
     * Individual set implementations should clearly document any
     * restrictions on the elements that they may contain.
     *
     * @param e element to be added to this set
     * @return <tt>true</tt> if this set did not already contain the specified
     *         element
     * @throws UnsupportedOperationException if the <tt>add</tt> operation
     *         is not supported by this set
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this set
     * @throws NullPointerException if the specified element is null and this
     *         set does not permit null elements
     * @throws IllegalArgumentException if some property of the specified element
     *         prevents it from being added to this set
     * @see <a href="http://java.sun.com/javase/6/docs/api/java/util/Set.html#add(E)">Set<U>.add(U)</a>
     */
	public boolean add(U e) {
		return set.add(e);
	}

    /**
     * Adds all of the elements in the specified collection to this set if
     * they're not already present (optional operation).  If the specified
     * collection is also a set, the <tt>addAll</tt> operation effectively
     * modifies this set so that its value is the <i>union</i> of the two
     * sets.  The behavior of this operation is undefined if the specified
     * collection is modified while the operation is in progress.
     *
     * @param  c collection containing elements to be added to this set
     * @return <tt>true</tt> if this set changed as a result of the call
     *
     * @throws UnsupportedOperationException if the <tt>addAll</tt> operation
     *         is not supported by this set
     * @throws ClassCastException if the class of an element of the
     *         specified collection prevents it from being added to this set
     * @throws NullPointerException if the specified collection contains one
     *         or more null elements and this set does not permit null
     *         elements, or if the specified collection is null
     * @throws IllegalArgumentException if some property of an element of the
     *         specified collection prevents it from being added to this set
     * @see #add(Object)
     * @see <a href="http://java.sun.com/javase/6/docs/api/java/util/Set.html#addAll(java.util.Collection)">Set<U>.addAll(Collection<? extends U>)</a>
     */
	public boolean addAll(Collection<? extends U> c) {
		return set.addAll(c);
	}

    /**
     * Returns an iterator over the elements in this set.  The elements are
     * returned in no particular order (unless this set is an instance of some
     * class that provides a guarantee).
     *
     * @return an iterator over the elements in this set
     * @see <a href="http://java.sun.com/javase/6/docs/api/java/util/Set.html#iterator()">Set<U>.iterator()</a>
     */
	public Iterator<U> iterator() {
		return set.iterator();
	}

    /**
     * Removes the specified element from this set if it is present
     * (optional operation).  More formally, removes an element <tt>e</tt>
     * such that
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>, if
     * this set contains such an element.  Returns <tt>true</tt> if this set
     * contained the element (or equivalently, if this set changed as a
     * result of the call).  (This set will not contain the element once the
     * call returns.)
     *
     * @param o object to be removed from this set, if present
     * @return <tt>true</tt> if this set contained the specified element
     * @throws ClassCastException if the type of the specified element
     *         is incompatible with this set (optional)
     * @throws NullPointerException if the specified element is null and this
     *         set does not permit null elements (optional)
     * @throws UnsupportedOperationException if the <tt>remove</tt> operation
     *         is not supported by this set
     * @see <a href="http://java.sun.com/javase/6/docs/api/java/util/Set.html#remove(java.lang.Object)">Set<U>.remove(Object)</a>
     */
	public boolean remove(Object o) {
		return set.remove(o);
	}

    /**
     * Removes all of the elements from this set (optional operation).
     * The set will be empty after this call returns.
     *
     * @throws UnsupportedOperationException if the <tt>clear</tt> method
     *         is not supported by this set
     * @see <a href="http://java.sun.com/javase/6/docs/api/java/util/Set.html#clear()">Set<U>.clear()</a>
     */
	public void clear() {
		set.clear();
	}

    /**
     * Returns <tt>true</tt> if this set contains the specified element.
     * More formally, returns <tt>true</tt> if and only if this set
     * contains an element <tt>e</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
     *
     * @param o element whose presence in this set is to be tested
     * @return <tt>true</tt> if this set contains the specified element
     * @throws ClassCastException if the type of the specified element
     *         is incompatible with this set (optional)
     * @throws NullPointerException if the specified element is null and this
     *         set does not permit null elements (optional)
     * @see <a href="http://java.sun.com/javase/6/docs/api/java/util/Set.html#contains(java.lang.Object)">Set<U>.contains(Object)</a>
     */
	public boolean contains(Object o) {
		return set.contains(o);
	}

    /**
     * Returns <tt>true</tt> if this set contains all of the elements of the
     * specified collection.  If the specified collection is also a set, this
     * method returns <tt>true</tt> if it is a <i>subset</i> of this set.
     *
     * @param  c collection to be checked for containment in this set
     * @return <tt>true</tt> if this set contains all of the elements of the
     * 	       specified collection
     * @throws ClassCastException if the types of one or more elements
     *         in the specified collection are incompatible with this
     *         set (optional)
     * @throws NullPointerException if the specified collection contains one
     *         or more null elements and this set does not permit null
     *         elements (optional), or if the specified collection is null
     * @see    #contains(Object)
     * @see <a href="http://java.sun.com/javase/6/docs/api/java/util/Set.html#containsAll(java.util.Collection)">Set<U>.containsAll(Collection<?>)</a>
     */
	public boolean containsAll(Collection<?> c) {
		return set.containsAll(c);
	}

    /**
     * Returns <tt>true</tt> if this set contains no elements.
     *
     * @return <tt>true</tt> if this set contains no elements
     * @see <a href="http://java.sun.com/javase/6/docs/api/java/util/Set.html#isEmpty()">Set<U>.isEmpty()</a>
     */
	public boolean isEmpty() {
		return set.isEmpty();
	}

    /**
     * Removes from this set all of its elements that are contained in the
     * specified collection (optional operation).  If the specified
     * collection is also a set, this operation effectively modifies this
     * set so that its value is the <i>asymmetric set difference</i> of
     * the two sets.
     *
     * @param  c collection containing elements to be removed from this set
     * @return <tt>true</tt> if this set changed as a result of the call
     * @throws UnsupportedOperationException if the <tt>removeAll</tt> operation
     *         is not supported by this set
     * @throws ClassCastException if the class of an element of this set
     *         is incompatible with the specified collection (optional)
     * @throws NullPointerException if this set contains a null element and the
     *         specified collection does not permit null elements (optional),
     *         or if the specified collection is null
     * @see #remove(Object)
     * @see #contains(Object)
     * @see <a href="http://java.sun.com/javase/6/docs/api/java/util/Set.html#removeAll(java.util.Collection)">Set<U>.removeAll(Collection<?>)</a>
     */
	public boolean removeAll(Collection<?> c) {
		return set.removeAll(c);
	}

    /**
     * Retains only the elements in this set that are contained in the
     * specified collection (optional operation).  In other words, removes
     * from this set all of its elements that are not contained in the
     * specified collection.  If the specified collection is also a set, this
     * operation effectively modifies this set so that its value is the
     * <i>intersection</i> of the two sets.
     *
     * @param  c collection containing elements to be retained in this set
     * @return <tt>true</tt> if this set changed as a result of the call
     * @throws UnsupportedOperationException if the <tt>retainAll</tt> operation
     *         is not supported by this set
     * @throws ClassCastException if the class of an element of this set
     *         is incompatible with the specified collection (optional)
     * @throws NullPointerException if this set contains a null element and the
     *         specified collection does not permit null elements (optional),
     *         or if the specified collection is null
     * @see #remove(Object)
     * @see <a href="http://java.sun.com/javase/6/docs/api/java/util/Set.html#retainAll(java.util.Collection)">Set<U>.retainAll(Collection<?>)</a>
     */
	public boolean retainAll(Collection<?> c) {
		return set.retainAll(c);
	}

    /**
     * Returns the number of elements in this set (its cardinality).  If this
     * set contains more than <tt>Integer.MAX_VALUE</tt> elements, returns
     * <tt>Integer.MAX_VALUE</tt>.
     *
     * @return the number of elements in this set (its cardinality)
     * @see <a href="http://java.sun.com/javase/6/docs/api/java/util/Set.html#size()">Set<U>.size()</a>
     */
	public int size() {
		return set.size();
	}

    /**
     * Returns an array containing all of the elements in this set.
     * If this set makes any guarantees as to what order its elements
     * are returned by its iterator, this method must return the
     * elements in the same order.
     *
     * <p>The returned array will be "safe" in that no references to it
     * are maintained by this set.  (In other words, this method must
     * allocate a new array even if this set is backed by an array).
     * The caller is thus free to modify the returned array.
     *
     * <p>This method acts as bridge between array-based and collection-based
     * APIs.
     *
     * @return an array containing all the elements in this set
     * @see <a href="http://java.sun.com/javase/6/docs/api/java/util/Set.html#toArray()">Set<U>.toArray()</a>
     */
	public Object[] toArray() {
		return set.toArray();
	}
	
    /**
     * Returns an array containing all of the elements in this set; the
     * runtime type of the returned array is that of the specified array.
     * If the set fits in the specified array, it is returned therein.
     * Otherwise, a new array is allocated with the runtime type of the
     * specified array and the size of this set.
     *
     * <p>If this set fits in the specified array with room to spare
     * (i.e., the array has more elements than this set), the element in
     * the array immediately following the end of the set is set to
     * <tt>null</tt>.  (This is useful in determining the length of this
     * set <i>only</i> if the caller knows that this set does not contain
     * any null elements.)
     *
     * <p>If this set makes any guarantees as to what order its elements
     * are returned by its iterator, this method must return the elements
     * in the same order.
     *
     * <p>Like the {@link #toArray()} method, this method acts as bridge between
     * array-based and collection-based APIs.  Further, this method allows
     * precise control over the runtime type of the output array, and may,
     * under certain circumstances, be used to save allocation costs.
     *
     * <p>Suppose <tt>x</tt> is a set known to contain only strings.
     * The following code can be used to dump the set into a newly allocated
     * array of <tt>String</tt>:
     *
     * <pre>
     *     String[] y = x.toArray(new String[0]);</pre>
     *
     * Note that <tt>toArray(new Object[0])</tt> is identical in function to
     * <tt>toArray()</tt>.
     *
     * @param a the array into which the elements of this set are to be
     *        stored, if it is big enough; otherwise, a new array of the same
     *        runtime type is allocated for this purpose.
     * @return an array containing all the elements in this set
     * @throws ArrayStoreException if the runtime type of the specified array
     *         is not a supertype of the runtime type of every element in this
     *         set
     * @throws NullPointerException if the specified array is null
     * @see <a href="http://java.sun.com/javase/6/docs/api/java/util/Set.html#toArray(T[])">Set<U>.toArray(T[])</a>
     */
	public <T> T[] toArray(T[] a) {
		return set.toArray(a);
	}

    /**
     * Constructs a new, empty ObservableSet with no observers
     */
	public ObservableSet() {
		super();
		that = this;
		set = new InternalSet();
		observer = new ElementObserver();
	}
}
