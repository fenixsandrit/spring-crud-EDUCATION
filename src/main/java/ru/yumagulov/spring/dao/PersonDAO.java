package ru.yumagulov.spring.dao;

import org.springframework.stereotype.Component;
import ru.yumagulov.spring.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();
        people.add(new Person(++PEOPLE_COUNT, "Tom",20, "Tom@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Bob", 21, "Bob@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Mike", 22, "Mike@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Katy", 23, "Katy@gmail.com"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person person) {
        show(id).setName(person.getName());
        show(id).setAge(person.getAge());
        show(id).setEmail(person.getEmail());
    }

    public void delete(int id) {
        people.removeIf(p -> p.getId() == id);
        PEOPLE_COUNT--;
        for (int i = 0; i < people.size(); i++)
            if(people.get(i).getId()>id)people.get(i).setId(people.get(i).getId()-1);
    }
}
