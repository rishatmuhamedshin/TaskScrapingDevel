public enum Grade {

    GOOD("good"), BAD("bad"), NEUTRAL("neutral");

    private String name;

    Grade(String s) {
        name = s;
    }

    public String getName() {
        return name;
    }
}
