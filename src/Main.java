import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import org.json.*;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Пожалуйста, введи широту в формате хх.хх..");
        String lat = scanner.nextLine();

        System.out.println("Пожалуйста, введите долготу в формате хх.хх..");
        String lon = scanner.nextLine();

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.weather.yandex.ru/v2/forecast?lat=" + lat + "&lon=" + lon))
                .GET().headers("X-Yandex-Weather-Key","e90f3f26-3f53-439c-a1f2-03a12062a1aa").build();
try {
    HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

    System.out.println("Код овтета: " + response.statusCode());

    String jsonString = response.body();




    int temp = new JSONObject(jsonString).getJSONObject("fact").getInt("temp");
    JSONArray forecaststemp_avg = new JSONObject(jsonString).getJSONArray("forecasts");
    int temp_avg = 0;
    int counter = 0;
    for (int i = 0; i < forecaststemp_avg.length(); i++) {
        int tempsum = forecaststemp_avg.getJSONObject(i).getJSONObject("parts").getJSONObject("day").getInt("temp_avg");
        temp_avg += tempsum;
        counter++;
    }

    //Выведем все данные
    System.out.println(jsonString);

    //Температура
    System.out.println("Температура: " + temp);

    //Средняя температура по прогнозу
    System.out.println("Суммарная температура: " + temp_avg + "\n"
            + "Количество дней: " + counter + "\n"
            + "Средняя температура: " + temp_avg / counter);

} catch (Exception e) {
    e.printStackTrace();
}
    }
}