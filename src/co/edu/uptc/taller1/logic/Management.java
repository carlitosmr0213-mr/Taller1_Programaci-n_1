package co.edu.uptc.taller1.logic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import co.edu.uptc.taller1.enums.ContractTypeEnum;
import co.edu.uptc.taller1.enums.IdentificationTypeEnum;
import co.edu.uptc.taller1.model.Company;
import co.edu.uptc.taller1.model.Contract;
import co.edu.uptc.taller1.model.Employee;
import co.edu.uptc.taller1.model.Project;
 
/**métodos genéricos
 * (insertar, buscar, actualizar, eliminar) funcionen con cualquier entidad.
 * en este se aplican los CRUDS para cada entidad
 */
public class Management<T extends BaseClass> {
 
    private ArrayList<T> listObject = new ArrayList<>();
    private static Management<Employee> employees;	
    private static Management<Company>  companies;
    private static Management<Project>  projects;
    private static Management<Contract> contracts;
 
    private static Scanner sc;
 
    public Management() {}
 
    /** Inserta solo si el ID no existe ya en la lista */
    public boolean insertObject(T object) {
        if (findObjectById(object.getId()) == null) {
            listObject.add(object);
            return true;
        }
        return false;
    }
 
    /** Busca por ID usando Stream. Retorna null si no existe. */
    public T findObjectById(String id) {
        return listObject.stream().filter(t -> t.getId().equals(id))
                .findAny()
                .orElse(null);
    }
 
    /** Retorna el índice del elemento con ese ID o -1 si no existe. */
    public int findIndexObjectById(String id) {
        for (int i = 0; i < listObject.size(); i++) {
            if (listObject.get(i).getId().equals(id)) return i;
        }
        return -1;
    }
 
    /** Reemplaza el registro existente con el nuevo (mismo ID). */
    public boolean updateObject(T newRecord) {
        int index = findIndexObjectById(newRecord.getId());
        if (index == -1) return false;
        listObject.set(index, newRecord);
        return true;
    }
 
    /** Elimina por ID. Retorna true si existía. */
    public boolean deleteObject(String id) {
        int index = findIndexObjectById(id);
        if (index != -1) {
            listObject.remove(index);
            return true;
        }
        return false;
    }
 
    /** Retorna la lista completa. */
    public ArrayList<T> getListObject() {
        return listObject;
    }
 
    /**Metodo que se llama en el main*/
    public static void execute(Scanner scanner) {
        sc        = scanner;
        employees = new Management<>();
        companies = new Management<>();
        projects  = new Management<>();
        contracts = new Management<>();
 
        int opcion;
        do {
            System.out.println("\n   ╔══════════════════════════════════════╗");
            System.out.println("   ║    SISTEMA DE GESTIÓN DE EMPLEADOS   ║");
            System.out.println("   ║══════════════════════════════════════╣");
            System.out.println("   ║  1. Gestionar Empresas               ║");
            System.out.println("   ║  2. Gestionar Empleados              ║");
            System.out.println("   ║  3. Gestionar Proyectos              ║");
            System.out.println("   ║  4. Gestionar Contratos              ║");
            System.out.println("   ║  0. Salir                            ║");
            System.out.println("   ╚══════════════════════════════════════╝");
            System.out.print("    Seleccione una opción: ");
            opcion = leerInt();
 
            switch (opcion) {
                case 1: menuCompany();
                		break;
                case 2: menuEmployee();
                		break;
                case 3: menuProject();
                		break;
                case 4: menuContract();
                		break;
                case 0:System.out.println("\n  saliendo..");
                		break;
                default: System.out.println("  Opción no válida.");
                		break;
            }
        } while (opcion != 0);
    }

    /*Menu empleado*/
    private static void menuEmployee() {
        int op;
        do {
            System.out.println("\n  ┌─────────────────────────────┐");
            System.out.println("  │      GESTIÓN EMPLEADOS      │");
            System.out.println("  ├─────────────────────────────┤");
            System.out.println("  │  1. Crear empleado          │");
            System.out.println("  │  2. Consultar por ID        │");
            System.out.println("  │  3. Actualizar empleado     │");
            System.out.println("  │  4. Eliminar empleado       │");
            System.out.println("  │  5. Listar todos            │");
            System.out.println("  │  6. Asignar proyecto        │");
            System.out.println("  │  7. Asignar contrato        │");
            System.out.println("  │  0. Volver                  │");
            System.out.println("  └─────────────────────────────┘");
            System.out.print("  Opción: ");
            op = leerInt();
 
            switch (op) {
                case 1: insertEmployee();
                		break;
                case 2: findEmployee();
                		break;
                case 3: updateEmployee();
        				break;
                case 4: deleteEmployee();
        				break;
                case 5: listEmployees();
                		break;
                case 6: assingProject();
                		break;
                case 7: assingContract();
                		break;
                case 0: break;
                default: System.out.println("  Opción no válida.");
            }
        } while (op != 0);
    }
 
    private static void insertEmployee() {
        System.out.println("\n  >> CREAR EMPLEADO");
        System.out.print("  Nombre completo     : ");
        String nombre  = sc.nextLine();
        System.out.print("  Número de documento : ");
        int numDoc     = leerInt();
        System.out.print("  Teléfono            : ");
        int telefono   = leerInt();
        System.out.print("  Email               : ");
        String email   = sc.nextLine();
        System.out.println("  Tipo de documento   : [1] CC  [2] TI  [3] CE");
        System.out.print("  Opción              : ");
        int tipoDoc = leerInt();
        IdentificationTypeEnum idType;
        switch (tipoDoc) {
            case 2: idType = IdentificationTypeEnum.TI;
            		break;
            case 3: idType = IdentificationTypeEnum.CE;
            		break;
            default: idType = IdentificationTypeEnum.CC;
        }
        System.out.print("  ID Empleado         : ");
        String empID  = sc.nextLine();
        System.out.print("  Salario             : ");
        double sal    = leerDouble();
        System.out.print("  Área de trabajo     : ");
        String area   = sc.nextLine();
        System.out.print("  Cargo               : ");
        String cargo  = sc.nextLine();
        if (companies.getListObject().isEmpty()) {
            System.out.println("  No hay empresas registradas. Cree una empresa primero.");
            return;
        }
        System.out.println("Empresas disponibles:");
        listCompanies();
        System.out.print("  ID de la empresa    : ");
        String idEmp = sc.nextLine();
        Company empresa = companies.findObjectById(idEmp);
        if (empresa == null) { 
        	System.out.println("  Empresa no encontrada.");
        	return; }
 
        Employee e = new Employee(nombre, numDoc, telefono, email,idType, empID, sal, area, new Date(), cargo, empresa);
        if (employees.insertObject(e)){
        	System.out.println("Empleado creado: " + e);
        }else{
        	System.out.println("Ya existe un empleado con ese ID.");
        }
    }
 
    private static void findEmployee() {
        System.out.println("\n  >> CONSULTAR EMPLEADO");
        System.out.print("  ID del empleado: ");
        String id = sc.nextLine();
        Employee e = employees.findObjectById(id);
        if (e == null) { System.out.println("Empleado no encontrado.");
        return; 
        }
        System.out.println("\n ==== Datos del empleado =================");
        System.out.println("  Nombre      : " + e.getFullName());
        System.out.println("  Documento   : " + e.getIdentificationType() + " " + e.getIdentificationNumber());
        System.out.println("  Teléfono    : " + e.getPhone());
        System.out.println("  Email       : " + e.getEmail());
        System.out.println("  Cargo       : " + e.getCharge());
        System.out.println("  Salario     : $" + e.getSalary());
        System.out.println("  Área        : " + e.getWorkArea());
        /*se usan operador ternarios para la empresa y el contrato*/
        System.out.println("  Empresa     : " + (e.getCompany() != null ? e.getCompany().getCompanyName() : "N/A"));
        System.out.println("  Contrato    : " + (e.getContract() != null ? e.getContract() : "Sin contrato"));
        if (e.getProjects().isEmpty()) {
            System.out.println("  Proyectos   : Ninguno asignado");
        } else {
            System.out.println("  Proyectos   :");
            for (Project p : e.getProjects()) System.out.println(p);
        }
        System.out.println("============================================");
    }
 
    private static void updateEmployee() {
        System.out.println("\nACTUALIZAR EMPLEADO");
        System.out.print("  ID del empleado: ");
        String id = sc.nextLine();
        Employee e = employees.findObjectById(id);
        if (e == null) { System.out.println("Empleado no encontrado.");
        return; 
        }
        System.out.println("  (Deje en blanco para no cambiar)");
        System.out.print("  Nuevo cargo   (actual: " + e.getCharge()   + "): ");
        String cargo = sc.nextLine();
        System.out.print("  Nuevo salario (actual: " + e.getSalary()   + ", 0=no cambiar): ");
        double sal = leerDouble();
        System.out.print("  Nueva área    (actual: " + e.getWorkArea() + "): ");
        String area  = sc.nextLine();
 
        if (!cargo.isEmpty()) e.setCharge(cargo);
        if (sal > 0)          e.setSalary(sal);
        if (!area.isEmpty())  e.setWorkArea(area);
        employees.updateObject(e);
        System.out.println("Empleado actualizado: " + e);
    }
 
    private static void assingProject() {
        System.out.println("\nASIGNAR PROYECTO A EMPLEADO");
        System.out.print("  ID del empleado : ");
        String idEmp = sc.nextLine();
        Employee e = employees.findObjectById(idEmp);
        if (e == null) { 
        	System.out.println("Empleado no encontrado.");
        return; 
        }
        if (projects.getListObject().isEmpty()) {
        	System.out.println("No hay proyectos registrados.");
        return; 
        }
        System.out.println("Proyectos disponibles:");
        listProjects();
        System.out.print("  ID del proyecto : ");
        String idPro = sc.nextLine();
        Project p = projects.findObjectById(idPro);
        if (p == null) { 
        	System.out.println("Proyecto no encontrado.");
        return; 
        }
        e.addProject(p);
        System.out.println("Proyecto '" + p.getProjectName() + "' asignado a " + e.getFullName());
    }
 
    private static void assingContract() {
        System.out.println("\n  >> ASIGNAR CONTRATO A EMPLEADO");
        System.out.print("  ID del empleado  : ");
        String idEmp = sc.nextLine();
        Employee e = employees.findObjectById(idEmp);
        if (e == null) { System.out.println("Empleado no encontrado.");
        return; 
        }
        if (contracts.getListObject().isEmpty()) { System.out.println("No hay contratos registrados.");
        return; 
        }
        System.out.println("Contratos disponibles:");
        listContracts();
        System.out.print("  ID del contrato  : ");
        String idCon = sc.nextLine();
        Contract c = contracts.findObjectById(idCon);
        if (c == null) { 
        	System.out.println("Contrato no encontrado.");
        	return; 
        }
        e.setContract(c);
        System.out.println("Contrato '" + c.getId() + "' asignado a " + e.getFullName());
    }
    private static void deleteEmployee() {
        System.out.println("\nELIMINAR EMPLEADO");
        System.out.print("  ID del empleado: "); String id = sc.nextLine();
        if (employees.deleteObject(id)){
        	System.out.println("Empleado eliminado.");
        }else{
        	System.out.println("Empleado no encontrado.");
        }
    }
 
    private static void listEmployees() {
        System.out.println("\n  Empleados registrados:");
        if (employees.getListObject().isEmpty()) {
            System.out.println(" (lista vacía)");
            return;
        }
        for (Employee e : employees.getListObject())
            System.out.println(e);
    }
    
    /**Menu empresa*/
    private static void menuCompany() {
        int op;
        do {
            System.out.println("\n  ┌─────────────────────────────┐");
            System.out.println("  │      GESTIÓN EMPRESAS       │");
            System.out.println("  ├─────────────────────────────┤");
            System.out.println("  │  1. Crear empresa           │");
            System.out.println("  │  2. Consultar por ID        │");
            System.out.println("  │  3. Actualizar empresa      │");
            System.out.println("  │  4. Eliminar empresa        │");
            System.out.println("  │  5. Listar todas            │");
            System.out.println("  │  0. Volver                  │");
            System.out.println("  └─────────────────────────────┘");
            System.out.print("  Opción: ");
            op = leerInt();
 
            switch (op) {
                case 1: insertCompany();
                		break;
                case 2: findCompany();
                		break;
                case 3: updateCompany();
                		break;
                case 4: deleteCompany();;
                		break;
                case 5: listCompanies();
                		break;
                case 0: break;
                default:System.out.println("Opción no válida.");
            }
        } while (op != 0);
    }
 
    private static void insertCompany() {
        System.out.println("\n  >> CREAR EMPRESA");
        System.out.print("  ID empresa  : ");
        String id  = sc.nextLine();
        System.out.print("  Nombre      : ");
        String nom = sc.nextLine();
        System.out.print("  Dirección   : ");
        String dir = sc.nextLine();
        System.out.print("  Teléfono    : ");
        String tel = sc.nextLine();
        System.out.print("  NIT         : ");
        String nit = sc.nextLine();
        Company c = new Company(id, nom, dir, tel, nit);
        if (companies.insertObject(c)){
        	System.out.println("Empresa creada: " + c);
        }else{
        	System.out.println("Ya existe una empresa con ese ID.");
        }
    }
 
    private static void findCompany() {
        System.out.println("\nCONSULTAR EMPRESA");
        System.out.print("  ID de la empresa: ");
        String id = sc.nextLine();
        Company c = companies.findObjectById(id);
        if (c == null) { System.out.println("Empresa no encontrada."); return; }
 
        System.out.println("\n ==== Datos de la empresa ===============");
        System.out.println("  ID       : " + c.getId());
        System.out.println("  Nombre   : " + c.getCompanyName());
        System.out.println("  Direc.   : " + c.getAddress());
        System.out.println("  Teléfono : " + c.getPhone());
        System.out.println("  NIT      : " + c.getNit());
        System.out.println(" ==========================================");
    }
 
    private static void updateCompany() {
        System.out.println("\nACTUALIZAR EMPRESA");
        System.out.print("  ID de la empresa: ");
        String id = sc.nextLine();
        Company c = companies.findObjectById(id);
        if (c == null) { 
        	System.out.println("Empresa no encontrada.");
        	return; 
        }
        System.out.println("  (Deje en blanco para no cambiar)");
        System.out.print("  Nuevo nombre    (actual: " + c.getCompanyName() + "): ");
        String nom = sc.nextLine();
        System.out.print("  Nueva dirección (actual: " + c.getAddress()     + "): ");
        String dir = sc.nextLine();
        System.out.print("  Nuevo teléfono  (actual: " + c.getPhone()       + "): ");
        String tel = sc.nextLine();
        if (!nom.isEmpty()) c.setCompanyName(nom);
        if (!dir.isEmpty()) c.setAddress(dir);
        if (!tel.isEmpty()) c.setPhone(tel);
        companies.updateObject(c);
        System.out.println("Empresa actualizada: " + c);
    }
 
    private static void deleteCompany() {
        System.out.println("\nELIMINAR EMPRESA");
        System.out.print("  ID de la empresa: ");
        String id = sc.nextLine();
        if (companies.deleteObject(id)){
        	System.out.println("Empresa eliminada.");
        }else {
        	System.out.println("Empresa no encontrada.");
        }
    }
 
    private static void listCompanies() {
        System.out.println("\n  Empresas registradas:");
        if (companies.getListObject().isEmpty()) {
            System.out.println("  (lista vacía)");
            return;
        }
        for (Company c : companies.getListObject())
            System.out.println(c);
    }
    /**Menu proyecto*/
    private static void menuProject() {
        int op;
        do {
            System.out.println("\n  ┌─────────────────────────────┐");
            System.out.println("  │      GESTIÓN PROYECTOS      │");
            System.out.println("  ├─────────────────────────────┤");
            System.out.println("  │  1. Crear proyecto          │");
            System.out.println("  │  2. Consultar por ID        │");
            System.out.println("  │  3. Actualizar proyecto     │");
            System.out.println("  │  4. Eliminar proyecto       │");
            System.out.println("  │  5. Listar todos            │");
            System.out.println("  │  0. Volver                  │");
            System.out.println("  └─────────────────────────────┘");
            System.out.print("  Opción: ");
            op = leerInt();
 
            switch (op) {
                case 1: insertProject();
                		break;
                case 2: findProject();
                		break;
                case 3: updateProject();
                		break;
                case 4: deleteProject();
                		break;
                case 5: listProjects();
                		break;
                case 0: break;
                default: System.out.println("Opción no válida.");
            }
        } while (op != 0);
    }
 
    private static void insertProject() {
        System.out.println("\nCREAR PROYECTO");
        System.out.print("  ID proyecto : ");
        String id  = sc.nextLine();
        System.out.print("  Nombre      : ");
        String nom = sc.nextLine();
        System.out.println("Ingrese la fecha de inicio del proyecto (dd/mm/yyyy)");
		String startDate = sc.nextLine();
		String fechaArray [] = startDate.split("/");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Integer.parseInt(fechaArray[2]), 
				Integer.parseInt(fechaArray[1]) - 1, 
				Integer.parseInt(fechaArray[0]));
		System.out.println("Ingrese la fecha de finalización del proyecto (dd/mm/yyyy)");
		String endDate = sc.nextLine();
		String fechaArray2 [] = endDate.split("/");
		Calendar calendar2 = Calendar.getInstance();
		calendar2.set(Integer.parseInt(fechaArray2[2]), 
				Integer.parseInt(fechaArray2[1]) - 1, 
				Integer.parseInt(fechaArray2[0]));
        System.out.print("  Presupuesto : ");
        double bud = leerDouble();
 
        Project p = new Project(id, nom, calendar.getTime(), calendar2.getTime(), bud);
        if (projects.insertObject(p)){
        	System.out.println("Proyecto creado: " + p);
        }else {
            System.out.println("Ya existe un proyecto con ese ID.");
        }
    }
 
    private static void findProject() {
        System.out.println("\nCONSULTAR PROYECTO");
        System.out.print("  ID del proyecto: ");
        String id = sc.nextLine();
        Project p = projects.findObjectById(id);
        if (p == null) { 
        	System.out.println("Proyecto no encontrado.");
        	return; 
        }
 
        System.out.println("\n === Datos del proyecto ================");
        System.out.println("  ID          : " + p.getId());
        System.out.println("  Nombre      : " + p.getProjectName());
        System.out.println("  Presupuesto : $" + p.getBudget());
        System.out.println("  Inicio      : " + p.getStartDate());
        System.out.println(" =========================================");
    }
 
    private static void updateProject() {
        System.out.println("\nACTUALIZAR PROYECTO");
        System.out.print("  ID del proyecto: ");
        String id = sc.nextLine();
        Project p = projects.findObjectById(id);
        if (p == null) { 
        	System.out.println("Proyecto no encontrado.");
        	return; 
        }
        System.out.println("  (Deje en blanco para no cambiar)");
        System.out.print("  Nuevo nombre      (actual: " + p.getProjectName() + "): ");
        String nom = sc.nextLine();
        System.out.print("  Nuevo presupuesto (actual: " + p.getBudget() + ", 0=no cambiar): ");
        double bud = leerDouble();
 
        if (!nom.isEmpty()) p.setProjectName(nom);
        if (bud > 0)        p.setBudget(bud);
        projects.updateObject(p);
        System.out.println(" Proyecto actualizado: " + p);
    }
    private static void deleteProject() {
        System.out.println("\nELIMINAR PROYECTO");
        System.out.print("  ID del proyecto: ");
        String id = sc.nextLine();
        if (projects.deleteObject(id)) {
            System.out.println("Proyecto eliminado.");
        }else { 
        	System.out.println("Proyecto no encontrado.");
        }
    }
 
    private static void listProjects() {
        System.out.println("\n  Proyectos registrados:");
        if (projects.getListObject().isEmpty()) {
            System.out.println("  (lista vacía)");
            return;
        }
        for (Project p : projects.getListObject()) {
        	System.out.println(p);
        }
    }
    
    /**Menu contrato*/
    private static void menuContract() {
        int op;
        do {
            System.out.println("\n  ┌─────────────────────────────┐");
            System.out.println("  │      GESTIÓN CONTRATOS      │");
            System.out.println("  ├─────────────────────────────┤");
            System.out.println("  │  1. Crear contrato          │");
            System.out.println("  │  2. Consultar por ID        │");
            System.out.println("  │  3. Actualizar contrato     │");
            System.out.println("  │  4. Eliminar contrato       │");
            System.out.println("  │  5. Listar todos            │");
            System.out.println("  │  0. Volver                  │");
            System.out.println("  └─────────────────────────────┘");
            System.out.print("  Opción: ");
            op = leerInt();
 
            switch (op) {
                case 1: insertContract();
                		break;
                case 2: findContract();
                		break;
                case 3: updateContract();
                		break;
                case 4: deleteContract();
                		break;
                case 5: listContracts();;
                		break;
                case 0: break;
                default: System.out.println("Opción no válida.");
            }
        } while (op != 0);
    }
 
    private static void insertContract() {
        System.out.println("\nCREAR CONTRATO");
        System.out.print("  ID contrato    : ");
        String id = sc.nextLine();
        System.out.println("Ingrese la fecha de inicio del contrato (dd/mm/yyyy)");
		String startDate = sc.nextLine();
		String fechaArray [] = startDate.split("/");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Integer.parseInt(fechaArray[2]), 
				Integer.parseInt(fechaArray[1]) - 1, 
				Integer.parseInt(fechaArray[0]));
		System.out.println("Ingrese la fecha de finalización del contrato (dd/mm/yyyy)");
		String endDate = sc.nextLine();
		String fechaArray2 [] = endDate.split("/");
		Calendar calendar2 = Calendar.getInstance();
		calendar2.set(Integer.parseInt(fechaArray2[2]), 
				Integer.parseInt(fechaArray2[1]) - 1, 
				Integer.parseInt(fechaArray2[0]));
		
        System.out.println("  Tipo           : [1] INDEFINIDO  [2] FIJO  [3] PRESTACION_SERVICIOS");
        System.out.print("  Opción         : ");
        int tipoCon = leerInt();
        ContractTypeEnum contractType;
        switch (tipoCon) {
            case 2: contractType = ContractTypeEnum.FIJO;
            		break;
            case 3: contractType = ContractTypeEnum.PRESTACION_SERVICIOS;
            		break;
            default: contractType = ContractTypeEnum.INDEFINIDO;
        }
        System.out.print("  Salario pactado: "); double sal = leerDouble();
 
        Contract c = new Contract(id, calendar.getTime(), calendar2.getTime(), contractType, sal);
        if (contracts.insertObject(c))
            System.out.println("Contrato creado: " + c);
        else
            System.out.println("Ya existe un contrato con ese ID.");
    }
 
    private static void findContract() {
        System.out.println("\nCONSULTAR CONTRATO");
        System.out.print("  ID del contrato: "); String id = sc.nextLine();
        Contract c = contracts.findObjectById(id);
        if (c == null) { System.out.println("Contrato no encontrado."); return; }
 
        System.out.println("\n  ==== Datos del contrato =============");
        System.out.println("  ID      : " + c.getId());
        System.out.println("  Tipo    : " + c.contractType());
        System.out.println("  Salario : $" + c.getSalary());
        System.out.println("  Inicio  : " + c.getStartDate());
        System.out.println("  ========================================");
    }
 
    private static void updateContract() {
        System.out.println("\nACTUALIZAR CONTRATO");
        System.out.print("  ID del contrato: ");
        String id = sc.nextLine();
        Contract c = contracts.findObjectById(id);
        if (c == null) { 
        	System.out.println("Contrato no encontrado.");
        	return; 
        }
        System.out.println("  Tipo actual: " + c.contractType());
        System.out.println("  Nuevo tipo : [1] INDEFINIDO  [2] FIJO  [3] PRESTACION_SERVICIOS  [0] No cambiar");
        System.out.print("  Opción     : ");
        int tipoOp = leerInt();
        if (tipoOp == 1) c.setContractType(ContractTypeEnum.INDEFINIDO);
        if (tipoOp == 2) c.setContractType(ContractTypeEnum.FIJO);
        if (tipoOp == 3) c.setContractType(ContractTypeEnum.PRESTACION_SERVICIOS);
 
        System.out.print("  Nuevo salario (actual: " + c.getSalary() + ", 0=no cambiar): ");
        double sal = leerDouble();
        if (sal > 0) c.setSalary(sal);
        contracts.updateObject(c);
        System.out.println("Contrato actualizado: " + c);
    }
    
    private static void deleteContract() {
        System.out.println("\nELIMINAR CONTRATO");
        System.out.print("  ID del contrato: ");
        String id = sc.nextLine();
        if (contracts.deleteObject(id)) {
            System.out.println("Contrato eliminado.");
        }else {
            System.out.println("Contrato no encontrado.");
        }
    }
 
    private static void listContracts() {
        System.out.println("\n  Contratos registrados:");
        if (contracts.getListObject().isEmpty()) {
            System.out.println(" (lista vacía)");
            return;
        }
        for (Contract c : contracts.getListObject()) {
            System.out.println( c);
        }
    }
    
    /*Metodos de conversion de NextInt a NextLine*/
    private static int leerInt() {
        try { return Integer.parseInt(sc.nextLine().trim()); }
        catch (NumberFormatException e) { 
        	System.out.println("  Entrada inválida. Se usará 0.");
        	return 0;
        }
    }
 
    private static double leerDouble() {
        try { return Double.parseDouble(sc.nextLine().trim()); }
        catch (NumberFormatException e) { 
        	System.out.println("  Entrada inválida. Se usará 0.");
        	return 0; 
        }
    }
    
}
