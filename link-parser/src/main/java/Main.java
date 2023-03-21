import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LinkParser parser = new GitHubParser();
        parser.setNext(new StackOverflowParser());

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Введите ссылку: ");
            String url = scanner.nextLine();

            if (url.isEmpty()) {
                break;
            }

            String result = parser.parse(url);
            if (result != null) {
                System.out.println("Результат: " + result);
            } else {
                System.out.println("Ссылка не соответствует формату");
            }
        }
    }
}