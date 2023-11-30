package javadevkit06;

/**
В качестве задачи предлагаю вам реализовать код для демонстрации парадокса Монти Холла 
* (Парадокс Монти Холла — Википедия ) и наглядно убедиться в верности парадокса 
* (запустить игру в цикле на 1000 и вывести итоговый счет).
* Необходимо:
* Создать свой Java Maven или Gradle проект;
* Самостоятельно реализовать прикладную задачу;
* Сохранить результат в HashMap<шаг теста, результат>
* Вывести на экран статистику по победам и поражениям
*/

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MontyHallParadox {
    public static void main(String[] args) {
        int totalTests = 1000 // количество тестов
        int winsSwitch = 0; // количество побед при смене выбора
        int winsStay = 0; // количество побед при оставлении выбора
        Map<Integer, String> results = new HashMap<>(); // Результаты тестов

        Random random = new Random();

        for (int i = 1; i <= totalTests; i++) {
            // создаем массив с 3 дверями
            boolean[] doors = new boolean[3];
            // за одной находится приз
            doors[random.nextInt(3)] = true;

            // игрок делает выбор
            int playerChoice = random.nextInt(3);

            // аедущий открывает одну дверей без приза
            int revealedDoor;
            do {
                revealedDoor = random.nextInt(3);
            } while (doors[revealedDoor] || revealedDoor == playerChoice);

            // игрок решает менять или нет
            int finalChoice = playerChoice;
            // если раскомментировать - то игрок всегда меняет
            // finalChoice = Arrays.stream(doors).boxed().indexOf(false);

            // выиграл ли игрок
            if (doors[finalChoice]) {
                winsSwitch++;
                results.put(i, "Победа при смене выбора");
            } else {
                winsStay++;
                results.put(i, "Победа при оставлении выбора");
            }
        }

        // статистика
        System.out.println("Победы при смене выбора: " + winsSwitch);
        System.out.println("Победы при оставлении выбора: " + winsStay);
        System.out.println("Процент побед при смене выбора: " + (double) winsSwitch / totalTests * 100 + "%");
        System.out.println("Процент побед при оставлении выбора: " + (double) winsStay / totalTests * 100 + "%");

        // результаты каждого теста
        for (Map.Entry<Integer, String> entry : results.entrySet()) {
            System.out.println("Тест " + entry.getKey() + ": " + entry.getValue());
        }
    }
}