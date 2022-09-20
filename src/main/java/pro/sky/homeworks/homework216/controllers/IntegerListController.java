package pro.sky.homeworks.homework216.controllers;

import org.springframework.web.bind.annotation.*;
import pro.sky.homeworks.homework216.services.IntegerListService;

@RestController
@RequestMapping("/array")
public class IntegerListController {
private final IntegerListService integerList;

    public IntegerListController(IntegerListService integerList) {
        this.integerList = integerList;
    }

    @GetMapping(path = "/add")
    public Integer add(@RequestParam("item") Integer item){
        return integerList.add(item);
    }
    @GetMapping(path = "/add-by-index")
    public Integer addByIndex(@RequestParam("index")int index, @RequestParam("item") Integer item){
        return integerList.add(index,item);
    }


}
