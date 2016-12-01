package soa.web;

import org.apache.camel.Consumer;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


@Controller
public class TweetController {

    @Autowired
    private ProducerTemplate producerTemplate;

    @RequestMapping("/twitear")
    public String index() {
        return "tweet";
    }


    @RequestMapping(value="/tweet")
    @ResponseBody
    public Object tweet(@RequestParam("q") String q) {
        return producerTemplate.requestBody("direct:tweet", q);
    }
}