/**
 * File Name:    LinkedListTests.java
 * Author:       Azeem Gbolahan
 * Description:  This class is used to test the LinkedList implementation. It includes multiple
 *               test cases to verify the correctness of core methods like add, remove, get,
 *               clear, contains, equals, isEmpty, and iteration. These tests rely on Java
 *               assertions and print statements to confirm behavior.
 *
 * How to Run:   Compile using `javac LinkedListTests.java` and run using `java -ea LinkedListTests`
 *               Ensure assertions are enabled with the `-ea` flag.
 */

 public class LinkedListTests {

    public static void main(String[] args) {
        // case 1: testing LinkedList()
        {
            // setup: create a new empty LinkedList
            LinkedList<Integer> ll = new LinkedList<Integer>();

            // verify: print out confirmation
            System.out.println(ll + " != null");

            // test: assert that the list is not null
            assert ll != null : "Error in LinkedList::LinkedList()";
        }

        // case 2: testing add(T item)
        {
            LinkedList<Integer> ll = new LinkedList<Integer>();
            for (int i = 0; i < 5; i++) {
                ll.add(i); // add elements 0 to 4
            }

            System.out.println(ll.size() + " == 5");
            assert ll.size() == 5 : "Error in LinkedList::add(T item) or LinkedList::size()";
        }

        // case 3: testing add(int index, T item)
        {
            LinkedList<Integer> ll = new LinkedList<Integer>();
            ll.add(0, 1); // [1]
            ll.add(1, 2); // [1, 2]
            ll.add(1, 3); // [1, 3, 2]
            ll.add(0, 4); // [4, 1, 3, 2]
            ll.add(4, 5); // [4, 1, 3, 2, 5]
            ll.add(3, 6); // [4, 1, 3, 6, 2, 5]

            System.out.println(ll.size() + " == 6");
            assert ll.size() == 6 : "Error in LinkedList::add(int index, T item) or LinkedList::size()";
        }

        // case 4: testing clear()
        {
            LinkedList<Integer> ll = new LinkedList<Integer>();
            for (int i : new int[] { 1, 2, 3 }) {
                ll.add(i); // add 1, 2, 3
            }
            ll.clear(); // clear all elements

            System.out.println(ll.size() + " == 0");
            assert ll.size() == 0 : "Error in LinkedList::clear()";
        }

        // case 5: testing contains()
        {
            LinkedList<Integer> ll = new LinkedList<Integer>();
            for (int i = 0; i < 3; i++) {
                ll.add(2 * i); // add 0, 2, 4
            }

            System.out.println(ll.contains(0) + " == true");
            System.out.println(ll.contains(4) + " == true");
            System.out.println(ll.contains(3) + " == false");

            assert ll.contains(0) : "Error in LinkedList::contains()";
            assert ll.contains(4) : "Error in LinkedList::contains()";
            assert !ll.contains(3) : "Error in LinkedList::contains()";
        }

        // case 6: testing equals()
        {
            LinkedList<Integer> list1 = new LinkedList<Integer>();
            LinkedList<Integer> list2 = new LinkedList<Integer>();
            LinkedList<Integer> list3 = new LinkedList<Integer>();
            LinkedList<Integer> list4 = new LinkedList<Integer>();
            for (int i = 0; i < 3; i++) {
                list1.add(i);
                list2.add(i);
                list3.add(i);
                list4.add(i);
            }
            list3.add(4);
            list4.add(5);

            System.out.println(list1.equals(list2) + " == true");
            System.out.println(list2.equals(list3) + " == false");
            System.out.println(list3.equals(list4) + " == false");
            System.out.println(list4.equals("Hello") + " == false");

            assert list1.equals(list2) : "Error in LinkedList::equals()";
            assert !list2.equals(list3) : "Error in LinkedList::equals()";
            assert !list3.equals(list4) : "Error in LinkedList::equals()";
            assert !list4.equals("Hello") : "Error in LinkedList::equals()";
        }

        // case 7: testing get()
        {
            LinkedList<Integer> ll = new LinkedList<Integer>();
            for (int i = 0; i < 5; i++) {
                ll.add(4-i); // adds 4, 3, 2, 1, 0
            }

            System.out.println(ll.get(0) + " == 0");
            System.out.println(ll.get(3) + " == 3");
            System.out.println(ll.get(4) + " == 4");

            assert ll.get(0) == 0 : "Error in LinkedList::get()";
            assert ll.get(3) == 3 : "Error in LinkedList::get()";
            assert ll.get(4) == 4 : "Error in LinkedList::get()";
        }

        // case 8: testing isEmpty()
        {
            LinkedList<Integer> list1 = new LinkedList<Integer>();
            LinkedList<Integer> list2 = new LinkedList<Integer>();
            list2.add(5); // list2 has one item

            System.out.println(list1.isEmpty() + " == true");
            System.out.println(list2.isEmpty() + " == false");

            assert list1.isEmpty() : "Error in LinkedList::isEmpty()";
            assert !list2.isEmpty() : "Error in LinkedList::isEmpty()";
        }

        // case 9: testing remove()
        {
            LinkedList<Integer> ll = new LinkedList<Integer>();
            for (int i = 0; i < 5; i++) {
                ll.add(4-i); // 4,3,2,1,0 -> added in reverse to result in 0,1,2,3,4
            }

            int remove0 = ll.remove(); // remove 0
            int remove1 = ll.remove(); // remove 1

            System.out.println(remove0 + " == 0");
            System.out.println(remove1 + " == 1");

            assert remove0 == 0 : "Error in LinkedList::remove()";
            assert remove1 == 1 : "Error in LinkedList::remove()";
        }

        // case 10: testing remove(int index)
        {
            LinkedList<Integer> ll = new LinkedList<Integer>();
            for (int i = 0; i < 8; i++) {
                ll.add(7-i); // add in reverse to result in 0 to 7 in order
            }
            int remove0 = ll.remove(0);
            int remove3 = ll.remove(3);
            int remove5 = ll.remove(5);

            System.out.println(remove0 + " == 0");
            System.out.println(remove3 + " == 4");
            System.out.println(remove5 + " == 7");

            assert remove0 == 0 : "Error in LinkedList::remove()";
            assert remove3 == 4 : "Error in LinkedList::remove()";
            assert remove5 == 7 : "Error in LinkedList::remove()";
        }

        // case 11: testing add(int index, T item) and iterator()
        {
            LinkedList<Integer> ll = new LinkedList<Integer>();
            ll.add(0, 1);
            ll.add(1, 4);
            ll.add(1, 2);
            ll.add(0, 0);
            ll.add(4, 5);
            ll.add(3, 3);

            int counter = 0;
            for (int val : ll) {
                System.out.println(val + " == " + counter); // print value and expected
                counter++;
            }

            counter = 0;
            for (int val : ll) {
                assert val == counter : "Error in LinkedList::add(int index, T item) or LinkedList::iterator()";
                counter++;
            }
        }
        System.out.println("Done testing LinkedList!");
    }
}
