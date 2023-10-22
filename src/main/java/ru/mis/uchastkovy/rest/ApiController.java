package ru.mis.uchastkovy.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mis.uchastkovy.repository.PatientsRepo;

@RestController
public class ApiController {

//    @Autowired
//    private Rep repo;

//    @Autowired
//    @Qualifier(value = "serializeObjectMapper")
//    private ObjectMapper objectMapper;

    @Autowired
    private PatientsRepo patRepo;

//    @GetMapping("/api")
//    public List<Uch> get() throws JsonProcessingException {
////        String a = "{\"content\": [{\"id\":1,\"fio\":\"Иванов А.Ф\",\"date\":\"2023-05-01\",\"disp\":true}, \n" +
////                " {\"id\":2,\"fio\":\"Петров П.П.\",\"date\":\"2024-10-01\",\"disp\":false}]}";
////
//
////        repo.findNsiDepartKind(1,"",true);
//
//        List<Uch> list = Arrays.asList(
//                new Uch(1L, "Иванов А.Ф", new Date(), true),
//                new Uch(7L, "Иванов А.Ф", new Date(), true),
//                new Uch(2L, "Петров П.П.", new Date(), true),
//                new Uch(3L, "Иванов А.Ж", new Date(), true),
//                new Uch(4L, "Петров П.Ф.", new Date(), true),
//                new Uch(5L, "Иванов А.А", new Date(), true)
//        );
//
//
//        return list;
//    }

//    @GetMapping(path = "/jj", params = {"distrId", "page", "size"})
//    public List<PatientDto> findPaginated(@RequestParam("distrId") int distrId,
//                                          @RequestParam("page") int page,
//                                          @RequestParam("size") int size) {
//
//        Pageable pagin = PageRequest.of(page, size);
//        Page<PatientDto> resultPage = patRepo.findAllPatientsFromDistr(distrId, pagin);
//
//        return resultPage.getContent();
//    }


    @GetMapping("/j")
    public String j() throws JsonProcessingException {

//        List<PatientDto> p = repo.findPatsFromDistr(136);
//
//
//        String j = objectMapper.writeValueAsString(p);

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
//        return j;
    }


}
