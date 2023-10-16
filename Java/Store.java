import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Store {
    private List <Toy> toys = new ArrayList<>();
    private List <Toy> prizeToys = new ArrayList<>();
    private final Random random = new Random();
    private String filename = "Java/toys.txt";
    private int id;


    public void showToys(){
        if (toys.isEmpty()){
            System.out.println("Список пуст");
        }
        else {
            System.out.println("Список игрушек: ");
            for (Toy toy : toys) {
                System.out.println(toy.printToy());
            }
        }
    }


    public void addToy(){
        try {
            System.out.println("Введите через пробел: Название - Вероятность выпадения - Количество");
            Scanner scan = new Scanner(System.in);
            String[] input = scan.nextLine().split(" ");
            Toy toy = new Toy(++id, input[0], Double.parseDouble(input[1]), Integer.parseInt(input[2]));
            toys.add(toy);
        }catch (Exception e) {
            System.out.println("Ошибка ввода");
        }
    }

    public void changeToy(){
        if (toys.isEmpty()){
            System.out.println("Список пуст");
        }
        else {
            try {
                Scanner scan = new Scanner(System.in);
                System.out.println("Введите ID: ");
                int id = scan.nextInt();
                for (Toy toy : toys) {
                    if (toy.getId() == id) {
                        System.out.println("Введите название: ");
                        scan.nextLine();
                        String name = scan.nextLine();
                        System.out.println("Введите вероятность выпадения: ");
                        double weight = scan.nextDouble();
                        System.out.println("Введите количество: ");
                        int quantity = scan.nextInt();
                        toy.setName(name);
                        toy.setWeight(weight);
                        toy.setQuantity(quantity);
                        break;
                    } else System.out.println("Не найдено");
                }
            }catch (Exception e) {
                System.out.println("Ошибка ввода");
            }
        }
    }


    public void deleteToy(){
        if (toys.isEmpty()){
            System.out.println("Список пуст");
        }
        else{
            try {
                Scanner scan = new Scanner(System.in);
                System.out.println("Введите ID: ");
                int id = scan.nextInt();
                for (Toy toy: toys) {
                    if(toy.getId() == id) {
                        toys.remove(toy);
                        break;
                    }
                    else System.out.println("Не найдено");
                }
            } catch (Exception e){
                System.out.println("Ошибка ввода");
            }
        }
    }


    public void drawToy(){
        if (toys.isEmpty()){
            System.out.println("Список пуст");
        }
        else {
            System.out.println("Розыгрыш успешен");
            double totalWeight = 0;
            for (Toy toy : toys) {
                totalWeight += toy.getWeight();
            }
            double randomNumber = random.nextDouble() * totalWeight;
            double currentWeight = 0;

            for (Toy toy : toys) {
                currentWeight += toy.getWeight();
                boolean toyExist = false;
                if (randomNumber <= currentWeight) {
                    Toy prizeToy = new Toy(toy.getId(), toy.getName(), toy.getWeight(), 1);

                    if(toy.getQuantity() == 1){
                        toys.remove(toy);
                    }
                    else {
                        toy.setQuantity(toy.getQuantity() - 1);
                    }

                    if(prizeToys.isEmpty()){
                        prizeToys.add(prizeToy);
                    }
                    else {
                        for (Toy prize: prizeToys){
                            if(prize.getId() == prizeToy.getId()) {
                                prize.setQuantity(toy.getQuantity() + 1);
                                toyExist = true;
                            }
                        }
                        if (!toyExist)
                            prizeToys.add(prizeToy);
                    }
                    break;
                }
            }
        }
    }


    public void showPrize(){
        if (prizeToys.isEmpty()){
            System.out.println("Список пуст");
        }
        else {
            System.out.println("Список призов: ");
            for(Toy toy: prizeToys){
                System.out.println(toy.printToy());
            }
        }
    }


    public void filePrize(){
        try(FileWriter writer = new FileWriter(filename, true))
        {
            Toy data = prizeToys.get(0);
            writer.write(data.printToy());
            writer.append('\n');
            writer.flush();
            prizeToys.remove(0);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

