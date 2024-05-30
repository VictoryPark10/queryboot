package com.vicsoft.queryboot.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;

@Component
public class QueryUtils {

    /**
     * Extracts the query from the Hibernate log.
     *
     * @param beforeData The Hibernate log containing the query.
     * @return The query string.
     */
    public String extractQueryFromLog(String beforeData) {
        Pattern pattern = Pattern.compile("Hibernate: (.*)");
        Matcher matcher = pattern.matcher(beforeData);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    /**
     * Parses the Hibernate log to extract parameters.
     *
     * @param beforeData The Hibernate log containing parameter values.
     * @return A list of parameter values.
     */
    public List<String> extractParametersFromLog(String beforeData) {
        List<String> params = new ArrayList<>();
        Pattern pattern = Pattern.compile("binding parameter \\[\\d+\\] as \\[(\\w+)\\] - \\[(.*?)\\]");
        Matcher matcher = pattern.matcher(beforeData);

        String type;
        String value;
        while (matcher.find()) {
            type = matcher.group(1);
            value = matcher.group(2);
            params.add(type + " - " + value);
        }

        return params;
    }

    /**
     * Replaces the placeholders in the query with the provided parameters.
     *
     * @param query The query with placeholders.
     * @param params The parameters to replace the placeholders.
     * @return The query with the placeholders replaced by the parameters.
     */
    public String replacePlaceholders(String query, List<String> params) {
        for (String param : params) {
            // Add quotes around VARCHAR and CHAR types, keep others as is
            if (param.startsWith("VARCHAR") || param.startsWith("CHAR")) {
                param = "'" + param.substring(param.indexOf('-') + 2) + "'";
            } else {
                param = param.substring(param.indexOf('-') + 2);
            }
            query = query.replaceFirst("\\?", param);
        }

        return query;
    }

}
