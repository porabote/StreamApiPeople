import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");

        Collection<Person> persons = new ArrayList<>();

        for (int i = 0; i < 10_000; i++) {
            persons.add(new Person(
                names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(names.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)]
            ));
        }

        Stream<Person> streamByAge = persons.stream();
        long minorPersonsCount = streamByAge
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних: " + minorPersonsCount);

        Stream<Person> streamByArmySuitable = persons.stream();
        List<String> armySuitablePersonsList = streamByArmySuitable
                .filter(person -> person.getAge() >= 18 && person.getAge() < 27)
                .filter(person -> person.getSex().equals(Sex.MAN))
                .map(person -> person.getFamily())
                .collect(Collectors.toList());
        System.out.println("Список пригодных к военной службе: " + armySuitablePersonsList);

        Stream<Person> streamByEducation = persons.stream();
        List<String> armyEducationPersonsList = streamByEducation
                .filter(person -> person.getEducation().equals(Education.HIGHER))
                .map(person -> person.getFamily())
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        System.out.println("Список пригодных к военной службе: " + armyEducationPersonsList);

    }
}