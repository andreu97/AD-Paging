package app;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class App {
	private static final int page_size=12;
	public static <Employee> void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Elegix una opcio:");
            System.out.println("<S> next (Següent)");
            System.out.println("<A> previous (Anterior)");
            System.out.println("<G n> Go to n");
            System.out.println("<Q> exit (Ixir)");

            String choice = scanner.nextLine().toUpperCase();

            if ("Q".equals(choice)) {
                break;
            }

            int currentPage = 1;

            if (choice.startsWith("G")) {
                try {
                    currentPage = Integer.parseInt(choice.substring(2));
                } catch (NumberFormatException e) {
                    System.out.println("Numero de pagina incorrecta");
                    continue;
                }
            }

            try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
                Transaction transaction = session.beginTransaction();

                int totalRecords = getTotalRecords(session);
                int totalPages = (int) Math.ceil((double) totalRecords / page_size);

                if (currentPage < 1 || currentPage > totalPages) {
                    System.out.println("Numero de pagina incorrecta");
                    continue;
                }

                List<Employee> employees = getEmployees(session, currentPage);

                transaction.commit();

                displayEmployees(employees, currentPage, totalPages);
            }
        }

        
        scanner.close();
    }

    private static int getTotalRecords(Session session) {
        
        return 0;
    }

    private static <Employee> List<Employee> getEmployees(Session session, int currentPage) {
       
        return null;
    }

    private static <Employee> void displayEmployees(List<Employee> employees, int currentPage, int totalPages) {
        System.out.println("Page " + currentPage + " of " + totalPages);
        for (Employee employee : employees) {
            System.out.println(employee);
        }
	}

}
