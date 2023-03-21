package lesson1.task1;

public class Main {
    public static void main(String[] args) {

        Person person = new Person.PersonBuilder().setFirstName("Petr").setLastName("Petrov")
                .setMiddleName("Petrovich").setCountry("ABC").setAddress("address").setPhone("123")
                .setAge(33).setGender("m").build();

        person.showInfo();

    }
}
