import java.util.Scanner;

public class Menu {

    private boolean exit = false;
    private Store store = new Store();

    public void showMenu() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (!exit) {
                System.out.println("""
                                               
                        1. Список игрушек
                        2. Добавить игрушку
                        3. Изменить игрушку
                        4. Удалить игрушку
                        5. Произвести розыгрыш
                        6. Показать призы
                        7. Выдать приз
                        8. Выход
                        Введите опцию:\s""");

                String choice = scanner.nextLine();
                if (choice.matches("1"))
                    store.showToys();
                if (choice.matches("2"))
                    store.addToy();
                if (choice.matches("3"))
                    store.changeToy();
                if (choice.matches("4"))
                    store.deleteToy();
                if (choice.matches("5"))
                    store.drawToy();
                if (choice.matches("6"))
                    store.showPrize();
                if (choice.matches("7"))
                    store.filePrize();
                if (choice.matches("8"))
                    exit = true;

            }
        } catch(Exception e){
                System.out.println(e.getMessage());
        }
    }
}
