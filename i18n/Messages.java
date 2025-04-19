package i18n;

import java.util.ResourceBundle;

public class Messages {
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("messages");
    public static String greetingHello() {
        return BUNDLE.getString("welcome");
    }

}
