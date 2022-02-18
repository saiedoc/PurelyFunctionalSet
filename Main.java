package com.fp;

import javax.lang.model.type.NullType;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class Main {

    //Basic Functions On Set
	
    public static<T>PurelyFunctionalSet<T> empty() {
	    PurelyFunctionalSet<T> emptySet = (T element) -> false;
        return emptySet;
    }

    public static <T>PurelyFunctionalSet<T> singletonSet(T val){
        PurelyFunctionalSet<T> singletonSet = (T element) -> element == val;
        return singletonSet;
    }

    public static <T>PurelyFunctionalSet<T> union(PurelyFunctionalSet<T> s , PurelyFunctionalSet<T> t){
        PurelyFunctionalSet<T> unionSet = (T element) -> s.contains(element) || t.contains(element);
        return unionSet;
    }

    public static <T>PurelyFunctionalSet<T> intersect(PurelyFunctionalSet<T> s , PurelyFunctionalSet<T> t) {
        PurelyFunctionalSet<T> intersectSet = (T element) -> s.contains(element) && t.contains(element);
        return intersectSet;
    }

    public static <T>PurelyFunctionalSet<T> diff(PurelyFunctionalSet<T> s,PurelyFunctionalSet<T> t){
        PurelyFunctionalSet<T> differenceSet = (T element) -> s.contains(element) && !t.contains(element);
        return differenceSet;
    }

    public static <T> PurelyFunctionalSet<T> filter(PurelyFunctionalSet<T> s, Predicate<T> p) {
        PurelyFunctionalSet<T> filteredSet = (T element) -> s.contains(element) && p.test(element);
        return filteredSet;
    }
    
   //Queries and Transformations on Sets
	
    public static boolean forallImplement(PurelyFunctionalSet<Integer> s, Predicate<Integer> p , Integer element){
        if (element == 1001)
            return true;

        if (s.contains(element))
            if (p.test(element))
                return forallImplement(s,p,element + 1);
            else
                return false;
        else
            return forallImplement(s,p,element + 1);

    }

    public static boolean forall(PurelyFunctionalSet<Integer> s, Predicate<Integer> p) {
        return forallImplement(s,p,-1000);
    }

    public static boolean exists(PurelyFunctionalSet<Integer> s, Predicate<Integer> p) {
        Predicate<Integer> np = (Integer element) -> !p.test(element);
        return !forall(s,np);
    }

    public static <R> PurelyFunctionalSet <R> map( PurelyFunctionalSet<Integer> s,
                                  Function<Integer, R> p) {


        PurelyFunctionalSet<R> mappedSet = (R element) -> {
            Predicate<R> predicate = (R val) -> s.contains((Integer) p.apply((Integer) element));
            return forall(s, (Predicate<Integer>) predicate);
        };
        return mappedSet;

    }






    public static void main(String[] args) {

        PurelyFunctionalSet<Integer> emptySet = empty();
        PurelyFunctionalSet<Integer> singletonSet1 = singletonSet(4);
        PurelyFunctionalSet<Integer> singletonSet2 = singletonSet(9);
        PurelyFunctionalSet<Integer> singletonSet3 = singletonSet(16);
        PurelyFunctionalSet<Integer> unionSet1 = union(singletonSet1,singletonSet2);
        PurelyFunctionalSet<Integer> unionSet2 = union(unionSet1,singletonSet3);
        Function<Integer, Integer> function = (element) -> Math.toIntExact(Math.round(Math.pow(element, 2))); ;
        PurelyFunctionalSet<Integer> mappedSet = map(unionSet2,function);

    }
}
