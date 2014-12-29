package cat.udl.eps.softarch.hello.controller;

import java.util.HashMap;
import java.util.Map;
import cat.udl.eps.softarch.hello.model.Greeting;
import cat.udl.eps.softarch.hello.repository.GreetingRepository;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by http://rhizomik.net/~roberto/
 */

@Controller
@RequestMapping(value = "/greetings")
public class WeatherController {
    final Logger logger = LoggerFactory.getLogger(WeatherController.class);

    @Autowired GreetingRepository greetingRepository;

    private static final Map<String, Integer> regions = new HashMap<String, Integer>(){
        {
            put("Alt Camp",1);
            put("Alt Emporda",2);
            put("Alt Penedes",3);
            put("Alt Urgell",4);
            put("Alta Ribagorça",5);
            put("Anoia",6);
            put("Bages",7);
            put("Baix Camp",8);
            put("Baix Ebre",9);
            put("Baix Emporda",10);
            put("Baix Llobregat",11);
            put("Baix Penedes",12);
            put("Barcelones",13);
            put("Bergueda",14);
            put("La Cerdanya",15);
            put("Conca de barbera",16);
            put("Garraf",17);
            put("Garrigues",18);
            put("Garrotxa",19);
            put("Girones",20);
            put("Maresme",21);
            put("Montsia",22);
            put("Noguera",23);
            put("Osona",24);
            put("Pallars Jussa",25);
            put("Pallars Sobira",26);
            put("Pla Estany",27);
            put("Pla Urgell",28);
            put("Priorat",29);
            put("Ribera Ebre",30);
            put("Ripolles",31);
            put("Segarra",32);
            put("Segria",33);
            put("Selva",34);
            put("Solsolnes",35);
            put("Tarragones",36);
            put("Terra Alta",37);
            put("Urgell",38);
            put("Vall Aran",39);
            put("Valles Occidental",40);
            put("Valles Oriental",41);
        }
    };


// LIST
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<String> list(@RequestParam(required=false, defaultValue="0") int page,
                                   @RequestParam(required=false, defaultValue="10") int size) {
        PageRequest request = new PageRequest(page, size);
        return regions.keySet();
    }
    @RequestMapping(method=RequestMethod.GET, produces="text/html")
    public ModelAndView listHTML(@RequestParam(required=false, defaultValue="0") int page,
                                 @RequestParam(required=false, defaultValue="10") int size) {
        return new ModelAndView("regions", "regions", list(page, size));
    }

// RETRIEVE
    @RequestMapping(value = "/{id}", method = RequestMethod.GET )
    @ResponseBody
    public Greeting retrieve(@PathVariable( "id" ) Long id) {
        logger.info("Retrieving greeting number {}", id);
        Preconditions.checkNotNull(greetingRepository.findOne(id), "Greeting with id %s not found", id);
        return greetingRepository.findOne(id);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView retrieveHTML(@PathVariable( "id" ) Long id) {
        return new ModelAndView("greeting", "greeting", retrieve(id));
    }


}
