package cat.udl.eps.softarch.hello.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import cat.udl.eps.softarch.hello.model.Greeting;
import cat.udl.eps.softarch.hello.repository.GreetingRepository;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created by http://rhizomik.net/~roberto/
 */

@Controller
@RequestMapping(value = "/greetings")
public class GreetingController {
    final Logger logger = LoggerFactory.getLogger(GreetingController.class);

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
    public Iterable<Greeting> list(@RequestParam(required=false, defaultValue="0") int page,
                                   @RequestParam(required=false, defaultValue="10") int size) {
        PageRequest request = new PageRequest(page, size);
        return greetingRepository.findAll(request).getContent();
    }
    @RequestMapping(method=RequestMethod.GET, produces="text/html")
    public ModelAndView listHTML(@RequestParam(required=false, defaultValue="0") int page,
                                 @RequestParam(required=false, defaultValue="10") int size) {
        return new ModelAndView("greetings", "regions", list(page, size));
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

// CREATE
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Greeting create(@Valid @RequestBody Greeting greeting, HttpServletResponse response) {
        logger.info("Creating greeting with content'{}'", greeting.getContent());
        response.setHeader("Location", "/greetings/" + greetingRepository.save(greeting).getId());
        return greeting;
    }
    @RequestMapping(method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces="text/html")
    public String createHTML(@Valid @ModelAttribute("greeting") Greeting greeting, BindingResult binding, HttpServletResponse response) {
        if (binding.hasErrors()) {
            logger.info("Validation error: {}", binding);
            return "form";
        }
        return "redirect:/greetings/"+create(greeting, response).getId();
    }
    // Create form
    @RequestMapping(value = "/form", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView createForm() {
        logger.info("Generating form for greeting creation");
        Greeting emptyGreeting = new Greeting();
        emptyGreeting.setDate(new Date());
        return new ModelAndView("form", "greeting", emptyGreeting);
    }

// UPDATE
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Greeting update(@PathVariable("id") Long id, @Valid @RequestBody Greeting greeting) {
        logger.info("Updating greeting {}, new content is '{}'", id, greeting.getContent());
        Preconditions.checkNotNull(greetingRepository.findOne(id), "Greeting with id %s not found", id);
        Greeting updateGreeting = greetingRepository.findOne(id);
        updateGreeting.setContent(greeting.getContent());
        updateGreeting.setEmail(greeting.getEmail());
        updateGreeting.setDate(greeting.getDate());
        return greetingRepository.save(updateGreeting);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/x-www-form-urlencoded")
    @ResponseStatus(HttpStatus.OK)
    public String updateHTML(@PathVariable("id") Long id, @Valid @ModelAttribute("greeting") Greeting greeting,
                         BindingResult binding) {
        if (binding.hasErrors()) {
            logger.info("Validation error: {}", binding);
            return "form";
        }
        return "redirect:/greetings/"+update(id, greeting).getId();
    }
    // Update form
    @RequestMapping(value = "/{id}/form", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView updateForm(@PathVariable("id") Long id) {
        logger.info("Generating form for updating greeting number {}", id);
        Preconditions.checkNotNull(greetingRepository.findOne(id), "Greeting with id %s not found", id);
        return new ModelAndView("form", "greeting", greetingRepository.findOne(id));
    }

// DELETE
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        logger.info("Deleting greeting number {}", id);
        Preconditions.checkNotNull(greetingRepository.findOne(id), "Greeting with id %s not found", id);
        greetingRepository.delete(id);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    @ResponseStatus(HttpStatus.OK)
    public String deleteHTML(@PathVariable("id") Long id) {
        delete(id);
        return "redirect:/greetings";
    }
}
