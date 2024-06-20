## 💡 Comparison

### ✅ ArrayList vs LinkedList

Both ArrayList and LinkedList are implementations of the List interface in Java, but they have different performance characteristics and are suited for different use cases. 

|  | ArrayList | LinkedList |
| --- | --- | --- |
| 1. | This class uses a dynamic array to store the elements in it. With the introduction of generics, this class supports the storage of all types of objects. | This class uses a double linked list to store the elements in it. Similar to the ArrayList, this class also supports the storage of all types of objects. |
| 2. | Manipulating ArrayList takes more time due to the internal implementation. Whenever we remove an element, internally, the array is traversed and the memory bits are shifted. | Manipulating LinkedList takes less time compared to ArrayList because, in a doubly-linked list, there is no concept of shifting the memory bits. The list is traversed and the reference link is changed. |
| 3. | Inefficient memory utilization. | Good memory utilization. |
| 4. | It can be one, two or multi-dimensional. | It can either be single, double or circular LinkedList. |
| 5. | Insertion operation is slow. | Insertion operation is fast. |
| 6. | This class implements a List interface. Therefore, this acts as a list. | This class implements both the List interface and the Deque Interface. Therefore, it can act as a list and a deque. |
| 7. | This class works better when the application demands storing the data and accessing it. | This class works better when the application demands manipulation of the stored data. |
| 8. | Data access and storage is very efficient as it stores the elements according to the indexes. | Data access and storage is slow in LinkedList. |
| 9. | Deletion operation is not very efficient. | Deletion operation is very efficient. |
| 10. | It is used to store only similar types of data. | It is used to store any types of data. |
| 11. | Less memory is used. | More memory is used. |
| 12. | This is known as static memory allocation. | This is known as dynamic memory allocation. |

**When should we use?**

- Use ArrayList when you need fast random access to elements, and insertions/deletions are infrequent or occur mostly at the end of the list
- Use LinkedList when your application involves frequent insertions and deletions, particularly at the beginning or end of the list, and you don’t need fast random access.

### ✅ HashSet vs TreeSet vs LinkedHashSet

HashSet, LinkedHashSet and TreeSet are all implementations of Set interface.

- **HashSet** is a collection of items where every item is unique and it is found in the java.util package
- **Treeset** provides an implementation of the Set interface that uses a tree for storage. Objects are stored in a sorted and ascending order
- **LinkedHashSet** class is a Hashtable and Linked list implementation of the Set interface. It inherits the HashSet class and implements the Set interface. LinkedHashSet class contains unique elements only like HashSet. But, keeping the insertion order in the LinkedHashset has some additional costs, both in terms of extra memory and extra CPU cycles. Therefore, if it is not required to maintain the insertion order, go for the lighter-weight HashSet instead.

**Comparison:**

|  | HashSet | LinkedHashSet | TreeSet |
| --- | --- | --- | --- |
| How they work internally? | HashSet uses HashMap internally to store it’s elements. | LinkedHashSet uses LinkedHashMap internally to store it’s elements. | TreeSet uses TreeMap internally to store it’s elements. |
| Order Of Elements | HashSet doesn’t maintain any order of elements. | LinkedHashSet maintains insertion order of elements. i.e elements are placed as they are inserted. | TreeSet orders the elements according to supplied Comparator. If no comparator is supplied, elements will be placed in their natural ascending order. |
| Null elements | HashSet allows maximum one null element. | LinkedHashSet also allows maximum one null element. | TreeSet doesn’t allow even a single null element. If you try to insert null element into TreeSet, it throws NullPointerException. |
| Performance | HashSet gives better performance than the LinkedHashSet and TreeSet. | The performance of LinkedHashSet is between HashSet and TreeSet. It’s performance is almost similar to HashSet. But slightly in the slower side as it also maintains LinkedList internally to maintain the insertion order of elements. | TreeSet gives less performance than the HashSet and LinkedHashSet as it has to sort the elements after each insertion and removal operations. |
| Synchronized | No | No | No |
| Fail-Fast Iterators or Fail-Safe | Fail-fast in nature. i.e You will get ConcurrentModificationException if they are modified after the creation of Iterator object. | Fail-fast in nature | Fail-fast in nature |
| Memory Occupation | HashSet requires less memory than LinkedHashSet and TreeSet as it uses only HashMap internally to store its elements. | LinkedHashSet requires more memory than HashSet as it also maintains LinkedList along with HashMap to store its elements. | TreeSet also requires more memory than HashSet as it also maintains Comparator to sort the elements along with the TreeMap. |
| When To Use? | Use HashSet if you don’t want to maintain any order of elements. | Use LinkedHashSet if you want to maintain insertion order of elements. | Use TreeSet if you want to sort the elements according to some Comparator. |