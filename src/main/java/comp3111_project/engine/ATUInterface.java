package comp3111_project.engine;

import comp3111_project.Person;

import java.util.Optional;

/**
 * Interface for ATU Engine
 */
public interface ATUInterface {
    /**
     * Add person into a team
     * @param i team number
     * @param k which sample to pick a person from
     * @param target Only for person k = 3 to calculate the dot product
     * @return person to be put into a team
     */
    public Optional<Person> selectK(int i,int k,Integer...target);
}
