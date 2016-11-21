package Reflaction;


public class User {

    private String name;
    private int age;
    private final int SALE = 100;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void printInfo(User user) {
        System.out.println("User info" + user);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
