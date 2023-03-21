

class LinkParser {
    protected LinkParser next = null;

    public void setNext(LinkParser parser) {
        this.next = parser;
    }

    public String parse(String url) {
        if (this.next != null) {
            return this.next.parse(url);
        }
        return null;
    }
}