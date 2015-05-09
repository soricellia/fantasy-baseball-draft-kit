/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbbdk.data;

import java.util.Objects;

/**
 *
 * @author Tony
 * @param <O> the object you wish to store
 * @param <V> the value of the object, must be comparable
 */
public class ObjectValuePair<O, V extends Comparable> implements Comparable {

    O o;
    V v;

    public ObjectValuePair(O obj, V value) {
        o = obj;
        v = value;
    }

    public O getObject() {
        return o;
    }

    public V getValue() {
        return v;
    }

    @Override
    public boolean equals(Object o) {
         if (o.getClass() != this.getClass()) {
            return false;
        }
        ObjectValuePair ovp = (ObjectValuePair) o;
        if (ovp.o.equals(this.o)) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.o);
        hash = 89 * hash + Objects.hashCode(this.v);
        return hash;
    }

    /**
     *
     * @param o
     * @return -1 if less than, -1 if equal and 1 if greater than
     */
    @Override
    public int compareTo(Object o) {
        if (o.getClass() != this.getClass()) {
            return -1;
        }
        ObjectValuePair ovp = (ObjectValuePair) o;

        if (v.compareTo(ovp.v) == 0) {
            return ovp.hashCode();
        }

        return v.compareTo(ovp.v);
    }

}
