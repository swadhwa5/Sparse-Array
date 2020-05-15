# Discussion

### When would a `SparseArray` be useful and why?

Sparse arrays can be useful when we have an extremely long array and only a few elements ever change value. 
By doing so, we are saving memory space as we virtually never take up any memory for the elements that have the 
default value. This allows us to work efficiently with large collections of data. For example, there is a 
genetic sequence which has similar components except for some rare components that might be different and 
a Sparse array might be useful in this case for storing the elements that have changed.


### Why is it that `SparseArray` must implement `iterator()`? Also, why is it beneficial for `SparseArray` 
to have an iterator?
  
Sparse array is not a pre defined data structure so an iterator has not been defined for it internally. 
Hence we need to implement an iterator for it. An iterator is required because that is the best way we can go
over all the elements in the array(including default and SparseList), which is necessary for operations 
like printing the elements, etc.
Basically, any case in which we need to go over all the elements of the array sequentially without 
caring about the internal structure of our Sparse array, an iterator would be necessary.

