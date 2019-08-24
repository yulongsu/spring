# hash code
```
List<String> list1 = Arrays.asList("1","2","3");
List<String> list2 = Arrays.asList("1","2","3");
```
- 这两个list地址不一样，但hashcode是一样的
- Returns the hash code value for this list.  The hash code of a list
 is defined to be the result of the following calculation:
````  
int hashCode = 1;
for (E e : list)
  hashCode = 31*hashCode + (e==null ? 0 : e.hashCode());
````

