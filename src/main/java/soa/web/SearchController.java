package soa.web;

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
public class SearchController {

	@Autowired
	  private ProducerTemplate producerTemplate;

	@RequestMapping("/")
    public String index() {
        return "index";
    }


    @RequestMapping(value="/search")
    @ResponseBody
    public Object search(@RequestParam("q") String q) {
        Map<String, Object> map = new HashMap<String, Object>();
        String lastWord = q.substring(q.lastIndexOf(" ")+1);
        if (lastWord.matches("max:\\d+")) {
            String firstWords = q.substring(0, q.lastIndexOf(" "));
            String number = lastWord.replaceAll("\\D+","");
            map.put("CamelTwitterKeywords", firstWords);
            map.put("CamelTwitterCount", number);
            return producerTemplate.requestBodyAndHeaders("direct:search", "", map);

        }
        else return producerTemplate.requestBodyAndHeader("direct:search", "", "CamelTwitterKeywords", q);
    }
}