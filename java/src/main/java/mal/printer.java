package mal;

import com.google.common.base.Joiner;
import mal.types.MalList;
import mal.types.MalVal;
import org.apache.commons.lang3.StringEscapeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class printer {

    public static String join(List<MalVal> value,
                              String delim, Boolean print_readably) {
        ArrayList<String> strs = new ArrayList<>();
        for (MalVal mv : value) {
            strs.add(mv.toString(print_readably));
        }
        return Joiner.on(delim).join(strs);
    }

    public static String join(Map<String,MalVal> value,
                              String delim, Boolean print_readably) {
        ArrayList<String> strs = new ArrayList<>();
        for (Map.Entry<String, MalVal> entry : value.entrySet()) {
            if (entry.getKey().length() > 0 &&
                entry.getKey().charAt(0) == '\u029e') {
                strs.add(":" + entry.getKey().substring(1));
            } else if (print_readably) {
                strs.add("\"" + entry.getKey() + "\"");
            } else {
                strs.add(entry.getKey());
            }
            strs.add(entry.getValue().toString(print_readably));
        }
        return Joiner.on(delim).join(strs);
    }

    public static String _pr_str(MalVal mv,
                                 Boolean print_readably) {
        return mv.toString(print_readably);
    }

    public static String _pr_str_args(MalList args,
                                      String sep, Boolean print_readably) {
        return join(args.getList(), sep, print_readably);
    }

    public static String escapeString(String value) {
        return StringEscapeUtils.escapeJson(value);
    }
}
