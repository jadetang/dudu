### DuDu
DuDu is a collection of some data structure implementation

- Disjoint Set  
WeightedDisjointSet implements an disjoint set, the details are based on the book [Algorithm](http://algs4.cs.princeton.edu/) by Robert Sedgewick and Kevin Wayne. It take O(N) to construct and take lg(N) to find a element and union two element respectively. The difference between my implementation and the book's is mine is a generic data structure, so it's quiet handy for common use. Under the hood, I use a HashMap as an invert index.

- Trie  
Trie is very common but powerful  data structure,[wiki](https://en.wikipedia.org/wiki/Trie).There are two implementations  of trie in DuDu, one is based on array and the other is based on hash, both of them are generic data structure.  
The one based on array is called TrieMap and it is fully complied with the java.util.Map interface, and your can iterate it's entryset, keyset and valueset in place like any other standard Map implementation in java, and when you remove a entry during an iteration it will throw a ConcurrentModificationException

