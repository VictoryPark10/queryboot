package com.vicsoft.queryboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/home", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView goHome(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();

        List<String> resultList = new ArrayList<>();
        resultList.add("AAA");
        resultList.add("BBB");
        resultList.add("CCC");
        resultList.add("DDD");
        resultList.add("EEE");
        resultList.add("FFF");

        mav.addObject("resultList", resultList);
        mav.setViewName("content/home");

        return mav;
    }

    @RequestMapping(value = "/combine", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> combineQuery(Model model, HttpServletRequest request, @RequestBody Map<String, Object> combineParams) {
        Map<String, Object> responseMap = new HashMap<>();

        try {
            String[] dummy = ((String) combineParams.get("beforeData")).split("Preparing:");
            String[] dummy2 = dummy[1].split("Parameters:");
            String[] dummy3 = dummy2[0].split("\\[2");

            String beforeQuery = dummy3[0].trim();
            String beforeParams = dummy2[1].trim();

//            String beforeQuery = (String) combineParams.get("beforeQuery");
//            String beforeParams = (String) combineParams.get("beforeParams");
            beforeParams = beforeParams.replaceAll(System.getProperty("line.separator"), "");
            beforeParams = beforeParams.replaceAll("\\(String\\), ", "(String)^")
                    .replaceAll("\\(Integer\\), ", "(Integer)^")
                    .replaceAll("\\(Long\\), ", "(Long)^")
                    .replaceAll("null, ", "null^");

            String[] params = beforeParams.split("\\^");
            StringBuilder sb = new StringBuilder();
            for (String param : params) {
                if (param.endsWith("(String)")) {
                    param = param.replace("(String)", "");
                    sb.append("'").append(param).append("'");
                }
                if (param.endsWith("(Integer)")) {
                    param = param.replace("(Integer)", "");
                    sb.append(param);
                }
                if (param.endsWith("(Long)")) {
                    param = param.replace("(Long)", "");
                    sb.append(param);
                }
                beforeQuery = beforeQuery.replaceFirst("\\?", sb.toString());
                sb.setLength(0);
            }

            logger.info("\nComplete Query : [{}]\n", beforeQuery);

            responseMap.put("result", true);
            responseMap.put("completeQuery", beforeQuery);
        } catch (Exception e) {
            responseMap.put("result", false);
        }

        return responseMap;
    }

}
