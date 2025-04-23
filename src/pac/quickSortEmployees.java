import java.util.Scanner;

public class quickSortEmployees {

    class EmployeeNode {
        Employee employee;
        EmployeeNode next;

        EmployeeNode(Employee employee) {
            this.employee = employee;
            this.next = null;
        }
    }

    EmployeeNode head;

    void addEmployee(Employee employee) {
        if(head == null) {
            head = new EmployeeNode(employee);
            return;
        }
        EmployeeNode currNode = head;
        while(currNode.next!=null) {
            currNode = currNode.next;
        }

        currNode.next = new EmployeeNode(employee);
    }

    void displayEmployees(EmployeeNode head) {
        EmployeeNode tempNode = head;
        System.out.println("\nId"+"\t"+"Name"+"\t"+"Age"+"\t"+"Salary");
        System.out.println("-----------------------------------------");
        while(tempNode!=null) {
            System.out.println(tempNode.employee.getEmpId()+"\t"+tempNode.employee.getName()+"\t"
            +tempNode.employee.getAge()+"\t"+tempNode.employee.getSalary());
            tempNode = tempNode.next;
        }
    }

    double compare(EmployeeNode a,EmployeeNode b) {
        if(a.employee.getSalary()!=b.employee.getSalary()) {
            return a.employee.getSalary()-b.employee.getSalary();
        }

        return b.employee.getAge()-a.employee.getAge();
    }

    EmployeeNode partitionList(EmployeeNode start,EmployeeNode end) {
        if(start == null || end == null || start == end) {
            return start;
        }
        EmployeeNode prev = start;
        EmployeeNode curr = start;
        EmployeeNode pivotNode = end;
       
        while(curr!=end) {
            if(compare(curr,pivotNode) > 0) {
                
                Employee temp = prev.employee;
                prev.employee = curr.employee;
                curr.employee = temp;

                prev = prev.next;
            }
            curr =  curr.next;
        }

        Employee temp = prev.employee;
        prev.employee = pivotNode.employee;
        end.employee = temp;

        return prev;
    }

    void sortEmployees(EmployeeNode start,EmployeeNode end) {
        if(start == null || start == end || start == end.next) {
            return;
        }
        EmployeeNode prevNode = partitionList(start, end);
        if(prevNode!=null && prevNode!=start) {
            EmployeeNode temp = start;
            while(temp.next!=prevNode) {
                temp = temp.next;
            }
            sortEmployees(start, temp);
        }
        
 
        if(prevNode!=null && prevNode.next!=null) {
            sortEmployees(prevNode.next, end);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Scanner sc1 = new Scanner(System.in);

        quickSortEmployees obj = new quickSortEmployees();
        System.out.println("Enter the number of employees -- ");
        int numberOfEmployees = sc.nextInt();

        //EmployeeNode headNode = new EmployeeNode(null);
        for(int index=0;index<numberOfEmployees;index++) {
            System.out.println("\nEnter the employee Id--");
            int empId = sc.nextInt();
            System.out.println("Enter the employee name--");
            String empName = sc1.nextLine();
            System.out.println("Enter the age of "+empName);
            int age = sc.nextInt();
            System.out.println("Enter the salary of "+empName);
            double empSalary = sc.nextDouble();

            Employee emp = new Employee(empId, empName, age, empSalary);

            obj.addEmployee(emp);
        }

        if(obj.head == null) {
            System.out.println("No employees !!");
            return;
        }
        EmployeeNode endNode = obj.head;

        while(endNode.next!=null) {
            endNode = endNode.next;
        }

        System.out.println("Employee list before sorting --- ");
        obj.displayEmployees(obj.head);

        obj.sortEmployees(obj.head, endNode);

        System.out.println("After sorting -- ");
        obj.displayEmployees(obj.head);

    }

    
}
