public class Student{

    //public int ID = 0;
    private String name;
    private String surname;
    private int age;

    public Student(String name, String surname, int age){
        this.name = name;
        this.surname = surname;
        this.age = age;
    }
    
    public String getName(){
        return name;
    }

    public String getSurname(){
        return surname;
    }

    public int getAge(){
        return age;
    }

    //public int getID(){
    //    return ID;
    //}

    public String toString(){
        return "Name: " + this.name + " Surname: " + this.surname + " age: " + this.age;
    }

}