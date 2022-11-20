package comp3111_project.utils;

import comp3111_project.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Util class for doing Merge sort, useful for Process
 */
public class MergeSort {

    private static List<Person> merge(List<Person> left, List<Person> right, int k) {
        int i = 0, j = 0, leftLength = left.size(), rightLength = right.size();
        List<Person> newPerson = new ArrayList<>();
        while (i < leftLength && j < rightLength) {
            if (k == 1) {
                if (left.get(i).getK1energy() <= right.get(j).getK1energy()) {
                    newPerson.add(right.get(j++));
                } else {
                    newPerson.add(left.get(i++));
                }
            } else {
                if (left.get(i).getK2energy() >= right.get(j).getK2energy()) {
                    newPerson.add(right.get(j++));
                } else {
                    newPerson.add(left.get(i++));
                }
            }
        }
        while (i < leftLength) {
            newPerson.add(left.get(i++));
        }
        while (j < rightLength) {
            newPerson.add(right.get(j++));
        }
        return newPerson;
    }

    /**
     * Sorts person objects in descending if k=1 and ascending if k=1
     * @param person
     * @param k
     * @return
     */
    public static List<Person> sort(List<Person> person, int k) {
        int l = 0, r = person.size() - 1;
        if (l == r)
            return person;
        int m = (l + r) / 2;
        List<Person> left = sort(person.subList(l, m + 1), k);
        List<Person> right = sort(person.subList(m + 1, r + 1), k);
        return merge(left, right, k);
    }
}
