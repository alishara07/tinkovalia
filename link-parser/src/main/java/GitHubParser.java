import java.util.regex.*;

class GitHubParser extends LinkParser {
    public String parse(String url) {
        Pattern pattern = Pattern.compile("https://github.com/(\\w+)/([\\w-]+)/?");
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            return "Имя пользователя: " + matcher.group(1) + " " +
                    "Репозиторий: " + matcher.group(2);
        } else {
            return super.parse(url);
        }
    }
}