package service.Validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidate {
    private static Pattern pattern;
    private Matcher matcher;

    private static final String PASSWORD_REGEX = ".{6,32}";
    public PasswordValidate(){
        pattern = Pattern.compile(PASSWORD_REGEX);
    }

    public boolean validate(String regex){
        matcher = pattern.matcher(regex);
        return matcher.matches();
    }
}
