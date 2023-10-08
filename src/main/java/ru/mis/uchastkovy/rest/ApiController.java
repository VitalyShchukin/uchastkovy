package ru.mis.uchastkovy.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mis.uchastkovy.model.Uch;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

@RestController
public class ApiController {

    @GetMapping("/api")
    public List<Uch> get() {
//        String a = "{\"content\": [{\"id\":1,\"fio\":\"Иванов А.Ф\",\"date\":\"2023-05-01\",\"disp\":true}, \n" +
//                " {\"id\":2,\"fio\":\"Петров П.П.\",\"date\":\"2024-10-01\",\"disp\":false}]}";
//

        List<Uch> list = Arrays.asList(
                new Uch(1L, "Иванов А.Ф", new Date(), true),
                new Uch(7L, "Иванов А.Ф", new Date(), true),
                new Uch(2L, "Петров П.П.", new Date(), true),
                new Uch(3L, "Иванов А.Ж", new Date(), true),
                new Uch(4L, "Петров П.Ф.", new Date(), true),
                new Uch(5L, "Иванов А.А", new Date(), true)
        );

        return list;
    }

    @GetMapping("/j")
    public String j(){
        return "[\n" +
                "  {\n" +
                "    \"name\": \"January\",\n" +
                "    \"income\": 4000,\n" +
                "    \"expenses\": 2400\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"February\",\n" +
                "    \"income\": 3000,\n" +
                "    \"expenses\": 1398\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"March\",\n" +
                "    \"income\": 2000,\n" +
                "    \"expenses\": 9800\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"April\",\n" +
                "    \"income\": 2780,\n" +
                "    \"expenses\": 3908\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"May\",\n" +
                "    \"income\": 1890,\n" +
                "    \"expenses\": 4800\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"June\",\n" +
                "    \"income\": 2390,\n" +
                "    \"expenses\": 3800\n" +
                "  }\n" +
                "]";
    }
}
