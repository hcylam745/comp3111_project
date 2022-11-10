package comp3111_project.engine;

import comp3111_project.Person;

import java.util.Optional;

public interface ATUInterface {
    public Optional<Person> selectK(int i,int k,Integer...target);
}
