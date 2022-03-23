package ru.yumagulov.spring.dao;

import org.springframework.stereotype.Component;
import ru.yumagulov.spring.models.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT, "Tom"));
        people.add(new Person(++PEOPLE_COUNT, "Bob"));
        people.add(new Person(++PEOPLE_COUNT, "Mike"));
        people.add(new Person(++PEOPLE_COUNT, "Katy"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        Person person = null;
        for (int i = 0; i < people.size(); i++)
            if(id == people.get(i).getId())
            {
                person = people.get(i);
                break;
            }

        return person;
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person person) {
        show(id).setName(person.getName());
    }

    public void delete(int id) {
        people.removeIf(p -> p.getId() == id);
        PEOPLE_COUNT--;
        for (int i = 0; i < people.size(); i++)
            if(people.get(i).getId()>id)people.get(i).setId(people.get(i).getId()-1);
    }
}
