package comp3111_project.engine;

import comp3111_project.Person;
import javafx.collections.ObservableList;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Logic for teaming up
 */
public class ATUEngine {
    /**
     * Team up the student
     * @param dataset all students
     * @return teams
     */
    public static List<List<Person>> run(ObservableList<Person> dataset) {
        final int size = dataset.size();

        List<Person> k1 = dataset.subList(0,size / 3);
        List<Person> remain = dataset.subList(size / 3, size);

        double k1Hat = dataset.stream().mapToInt(Person::getK1energy).average().orElse(-1);
        double k2Hat = dataset.stream().mapToInt(Person::getK2energy).average().orElse(-1);
        Pair<Double,Double> hat = new Pair<>(k1Hat,k2Hat);

        List<List<Person>> teams = new ArrayList<>();

        for(int i = 0 ; i < size / 3 ; ++i) {
            List<Person> team = new ArrayList<>();
            team.add(k1.get(i));
            teams.add(team);
        }

        for(int i = 0 ; i < size / 3 ; ++i) {
            List<Person> team = teams.get(i);
            int targetIndex = select(remain,team,hat);
            team.add(remain.get(targetIndex));
            remain.remove(targetIndex);
        }

        for(int i = 0 ; i < size / 3 ; ++i) {
            List<Person> team = teams.get(i);
            int targetIndex = select(remain,team,hat);
            team.add(remain.get(targetIndex));
            remain.remove(targetIndex);
        }

        for(int i = 0 ; i < remain.size() ; ++i) {
            Person p = remain.get(i);
            int targetIndex = select(teams,p,hat);
            teams.get(targetIndex).add(p);
            remain.remove(p);
        }

        teams.sort(new Comparator<List<Person>>() {
            @Override
            public int compare(List<Person> o1, List<Person> o2) {
                return Double.compare(o2.stream().mapToInt(Person::getK1energy).average().orElse(-1),
                        o1.stream().mapToInt(Person::getK1energy).average().orElse(-1));
            }
        });
        return teams;
    }

    /**
     * Select person from remain and put it in the team
     * @param from list of person to choose from
     * @param to team to be put into
     * @param mean total mean of k1 and k2
     * @return index of person in from list
     */
    private static int select(List<Person> from, List<Person> to, Pair<Double,Double> mean) {
        double min = ms(combine(to,from.get(0)),mean);
        int minIndex = 0;
        for(int i = 1; i < from.size() ; ++i) {
            Pair<Double,Double> vector = combine(to,from.get(i));
            double dist = ms(vector,mean);
            if(dist < min) {
                min = dist;
                minIndex = i;
            }
        }
        return minIndex;
    }

    /**
     * Algorithm to allocate the last person
     * @param team list of teams person can be allocated
     * @param last last person
     * @param mean mean of k1 + k2
     * @return index of allocated teams
     */
    private static int select(List<List<Person>> team,Person last,Pair<Double,Double> mean) {
        List<Person> firstTeam = team.get(0);
        double min = ms(combine(firstTeam,last),mean) - ms(getTeamAverage(firstTeam),mean);
        int minIndex = 0;
        for(int i = 1 ; i < team.size() ; ++i) {
            List<Person> t = team.get(i);
            double original = ms(getTeamAverage(t),mean);
            double newDist = ms(combine(t,last),mean);
            if(newDist < min) {
                min = newDist;
                minIndex = i;
            }
        }

        return minIndex;
    }

    /**
     * Average Energy Team
     * @param team input team
     * @return average vector for k1,k2
     */
    private static Pair<Double, Double> getTeamAverage(List<Person> team) {
        return new Pair<>(team.stream().mapToInt(Person::getK1energy).average().orElse(-1),
                team.stream().mapToInt(Person::getK2energy).average().orElse(-1));
    }

    /**
     * Mean Square Error
     * @param p1 point 1
     * @param p2 point 2
     * @return    mean square error of two points
     */
    private static double ms(Pair<Double,Double> p1, Pair<Double,Double> p2) {
        return Math.pow(p1.getKey() - p2.getValue(),2) + Math.pow(p1.getValue() - p2.getValue(),2);
    }

    /**
     * Average vector of k1 and k2 when person p is being added to the team
     * @param team Team selected
     * @param p person to add
     * @return average vector for k1 and k2 when p is being combined
     */
    private static Pair<Double,Double> combine(List<Person> team, Person p) {
        return new Pair<Double,Double>((double) (team.stream().mapToInt(Person::getK1energy).sum() + p.getK1energy()) / (team.size() + 1),
                (double) (team.stream().mapToInt(Person::getK2energy).sum() + p.getK2energy()) / (team.size() + 1));
    }
}
