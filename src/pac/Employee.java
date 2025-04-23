public class Employee {
    private int empId;
    private String name;
    private int age;
    private double salary;

    public Employee(int empId,String name,int age,double salary) {
        this.empId = empId;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public int getEmpId() {
        return this.empId;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public double getSalary() {
        return this.salary;
    }
}
