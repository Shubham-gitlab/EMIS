public class EmployeeService {
    private Map<Long, Employee> employees = new HashMap<>();
    private long currentId = 1;

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }

    public Employee getEmployeeById(Long id) {
        return employees.get(id);
    }

    public Employee addEmployee(Employee employee) {
        employee.setId(currentId++);
        employees.put(employee.getId(), employee);
        return employee;
    }

    public Employee updateEmployee(Employee employee) {
        if (employees.containsKey(employee.getId())) {
            employees.put(employee.getId(), employee);
            return employee;
        }
        return null; // Employee not found
    }

    public boolean deleteEmployee(Long id) {
        return employees.remove(id) != null;
    }
}

EmployeeController class (API endpoints):

javaCopy code
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService = new EmployeeService();

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        employee.setId(id);
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public boolean deleteEmployee(@PathVariable Long id) {
        return employeeService.deleteEmployee(id);
    }
}

